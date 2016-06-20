package ctci.hard;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class LongestWordTest {
    @Test
    public void testFindLongestWords() {
        String[] dictionary = new String[]{"abcdefghi", "abcghi", "abc", "def", "ghi"};
        LongestWord longestWord = new LongestWord(dictionary);
        Optional<String> optLongestWord = longestWord.findLongestWords();
        assertTrue(optLongestWord.isPresent());
        assertEquals("abcdefghi", optLongestWord.get());
    }

    @Test
    public void testFindLongestWordsWhenOneDoesNotExist() {
        String[] dictionary = new String[]{"abdefghi", "abc", "def", "ghi"};
        LongestWord longestWord = new LongestWord(dictionary);
        Optional<String> optLongestWord = longestWord.findLongestWords();
        assertFalse(optLongestWord.isPresent());
    }
}
