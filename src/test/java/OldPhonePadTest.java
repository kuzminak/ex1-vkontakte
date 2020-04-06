
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class OldPhonePadTest
{
    private OldPhonePad oldPhonePad;

    @Before
    public void init()
    {
        oldPhonePad = new OldPhonePad();
    }


    @Test
    public void test3x_1()
    {
        int[] keys = {2,3,9};
        List<String> expected = loadCombinations("keys-2-3-9.txt");
        oldPhonePad = new OldPhonePad();
        oldPhonePad.combinations(keys);
        List<String> actual = oldPhonePad.getOutChars();

                assertEquals(expected,actual);
    }

    @Test
    public void test3x_2()
    {
        int[] keys = {5,6,8};
        List<String> expected = loadCombinations("keys-5-6-8.txt");
        oldPhonePad = new OldPhonePad();
        oldPhonePad.combinations(keys);
        List<String> actual = oldPhonePad.getOutChars();

        assertEquals(expected,actual);
    }

    @Test
    public void test3x_null()
    {
        int[] keys = {1,0,8};
        List<String> expected = loadCombinations("keys-1-0-8.txt");
        oldPhonePad = new OldPhonePad();
        oldPhonePad.combinations(keys);
        List<String> actual = oldPhonePad.getOutChars();

        assertEquals(expected,actual);
    }

    @Test
    public void test4x_2()
    {
        int[] keys = {2,3,9,7};
        List<String> expected = loadCombinations("keys-2-3-9-7.txt");
        oldPhonePad = new OldPhonePad();
        oldPhonePad.combinations(keys);
        List<String> actual = oldPhonePad.getOutChars();

        assertEquals(expected,actual);
    }


    List<String> loadCombinations(String path)
    {
        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream("oldPhonePad/" + path))
        {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8).trim().lines()
                    .collect(Collectors.toList());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
