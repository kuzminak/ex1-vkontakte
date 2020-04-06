import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;


public class SplitIntoSms
{
    private int maxSmsLength = 140;

    private double suffixOverhead = 1.08;

    public void run(String textFile)
    {
        printSmsWithLength(split(loadText(textFile)));
    }


    public List<String> split(String text) throws IllegalArgumentException
    {
        if (text.isBlank())
        {
            throw new IllegalArgumentException("Input string not should be Blank");
        }

        int smsIndex = 1;

        List<String> smsList = new ArrayList<>();
        StringBuilder smsString = new StringBuilder();

        int inputLength = text.length();
        int numberOfSms = (int) Math.ceil((double) inputLength * suffixOverhead / (double) maxSmsLength);
        int maxSuffixLength = (numberOfSms + "/" + numberOfSms).length();

        String[] words = text.split(" ");

        String smsSuffix = "1/" + numberOfSms;

        for (String word : words)
        {
            if (smsString.length() + word.length() + 1 > maxSmsLength - maxSuffixLength)
            {
                smsString.append(smsSuffix);
                smsList.add(smsString.toString());
                smsString = new StringBuilder();
                smsIndex++;
                smsSuffix = smsIndex + "/" + numberOfSms;
            }

            smsString.append(word);
            smsString.append(" ");
        }

        smsString.append(smsSuffix);
        smsList.add(smsString.toString());

        return smsList;
    }


    public void printSmsWithLength(List<String> smsList)
    {
        smsList.stream().forEach(sms -> System.out.println(MessageFormat.format("({0}) {1}", sms.length(), sms)));
    }


    public String loadText(String path)
    {
        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(path))
        {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8).trim();
        }
        catch (NullPointerException e)
        {
            System.out.println(MessageFormat.format("File {0} not found", path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
