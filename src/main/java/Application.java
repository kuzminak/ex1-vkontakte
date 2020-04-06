public class Application
{
    public static void main(String[] args)
    {
        System.out.println("============= TASK 1 ==============");
        new SplitIntoSms().run("text3.txt");


        System.out.println();
        System.out.println();


        System.out.println("============= TASK 2 ==============");
        int[] input = {2,3,9,7};
        OldPhonePad oldPhonePad = new OldPhonePad();
        oldPhonePad.combinations(input);
        oldPhonePad.getOutChars().stream().forEach(System.out::println);
    }
}
