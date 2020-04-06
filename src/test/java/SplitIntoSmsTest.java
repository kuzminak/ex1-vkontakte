
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class SplitIntoSmsTest
{
    private SplitIntoSms splitIntoSms;

    private int maxSmsLength = 140;

    @Before
    public void init()
    {
        splitIntoSms = new SplitIntoSms();
    }


    @Test
    public void test1()
    {
        String text = loadText("text1.txt");
        List<String> smsList = splitIntoSms.split(text);

        assertEquals(6, smsList.size());

        smsList.forEach(sms -> assertEquals(sms.length() <= maxSmsLength, true));
    }


    @Test
    public void test2()
    {
        String text = loadText("text2.txt");
        List<String> smsList = splitIntoSms.split(text);

        assertEquals(6, smsList.size());

        smsList.forEach(sms -> assertEquals(sms.length() <= maxSmsLength, true));
    }


    @Test
    public void test3()
    {
        String text = loadText("text3.txt");
        List<String> smsList = splitIntoSms.split(text);

        assertEquals(28, smsList.size());

        smsList.forEach(sms -> assertEquals(sms.length() <= maxSmsLength, true));
    }


    String loadText(String path)
    {
        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream("splitSms/" + path))
        {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8).trim();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
