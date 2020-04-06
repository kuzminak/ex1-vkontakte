import java.util.ArrayList;
import java.util.List;


public class OldPhonePad
{
    private final List<char[]> padChars;

    private final List<String> outChars = new ArrayList<>();

    {
        padChars = new ArrayList<>(10);
        padChars.add(0, null);
        padChars.add(1, null);
        padChars.add(2, new char[] { 'a', 'b', 'c' });
        padChars.add(3, new char[] { 'd', 'e', 'f', 'g' });
        padChars.add(4, new char[] { 'h', 'i', 'j' });
        padChars.add(5, new char[] { 'k', 'l', 'm', 'n' });
        padChars.add(6, new char[] { 'o', 'p', 'q' });
        padChars.add(7, new char[] { 'r', 's', 't' });
        padChars.add(8, new char[] { 'u', 'v', 'w' });
        padChars.add(9, new char[] { 'x', 'y', 'z' });
    }

    public List<String> combinations(int[] numKeys)
    {
        recursive(numKeys, "");
        return outChars;
    }


    private void recursive(int[] numKeys, String leftSideString)
    {
        if (numKeys.length > 0)
        {
            int[] numKeysShifted = new int[numKeys.length - 1];

            char[] chars = padChars.get(numKeys[0]);
            System.arraycopy(numKeys, 1, numKeysShifted, 0, numKeys.length - 1);
            if (chars != null)
            {
                for (char _char : chars)
                {
                    recursive(numKeysShifted, leftSideString + _char);
                }
            }
            else
            {
                recursive(numKeysShifted, leftSideString);
            }
        }
        else
        {
            outChars.add(leftSideString);
        }
    }


    public List<String> getOutChars()
    {
        return outChars;
    }
}
