package ctci.hard;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LettersAndNumbersTest {
    @Test
    public void testFindLongestSubArray() {
        LettersAndNumbers lettersAndNumbers = new LettersAndNumbers();
        char[] longestSubArray = lettersAndNumbers.findLongestSubArray(new char[]{'a', 'a', '1', '2', '1', 'a'});
        assertNotNull(longestSubArray);
        assertTrue(Arrays.equals(new char[] {'a', 'a', '1', '2', '1', 'a'}, longestSubArray));
    }

    @Test
    public void testFindLongestSubArrayWithMoreLetters() {
        LettersAndNumbers lettersAndNumbers = new LettersAndNumbers();
        char[] longestSubArray = lettersAndNumbers.findLongestSubArray(new char[]{'a', 'a', '1', '2', '1', 'a', 'b', 'c', 'a'});
        assertNotNull(longestSubArray);
        assertTrue(Arrays.equals(new char[] {'a', 'a', '1', '2', '1', 'a'}, longestSubArray));
    }

    @Test
    public void testFindLongestSubArrayWithMoreDigits() {
        LettersAndNumbers lettersAndNumbers = new LettersAndNumbers();
        char[] longestSubArray = lettersAndNumbers.findLongestSubArray(new char[]{'a', 'a', '1', '2', '1', 'a', '4', '3', '2'});
        assertNotNull(longestSubArray);
        assertTrue(Arrays.equals(new char[] {'a', 'a', '1', '2', '1', 'a'}, longestSubArray));
    }

    @Test
    public void testFindLongestSubArrayWithSimpleUseCase() {
        LettersAndNumbers lettersAndNumbers = new LettersAndNumbers();
        char[] longestSubArray = lettersAndNumbers.findLongestSubArray(new char[]{'a', '1'});
        assertNotNull(longestSubArray);
        assertTrue(Arrays.equals(new char[] {'a', '1'}, longestSubArray));
    }

    @Test
    public void testFindLongestSubArrayWithNoSubArray() {
        LettersAndNumbers lettersAndNumbers = new LettersAndNumbers();
        char[] longestSubArray = lettersAndNumbers.findLongestSubArray(new char[]{'a', 'a'});
        assertNotNull(longestSubArray);
        assertTrue(Arrays.equals(new char[0], longestSubArray));
    }
}
