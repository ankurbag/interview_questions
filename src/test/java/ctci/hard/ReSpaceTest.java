package ctci.hard;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ReSpaceTest {

    @Test
    public void testBestSplit() {
        Set<String> dictionary = new HashSet<>(Arrays.asList("looked", "just", "like", "her", "brother"));
        ReSpace reSpace = new ReSpace(dictionary);

        String sentence = "jesslookedjustliketimherbrother";
        String reSpacedSentence = reSpace.bestSplit(sentence);
        assertEquals("jess looked just like tim her brother", reSpacedSentence);
    }

    @Test
    public void testBestSplitWithMoreWords() {
        Set<String> dictionary = new HashSet<>(Arrays.asList("looked", "just", "like", "her", "brother", "rot", "tim"));
        ReSpace reSpace = new ReSpace(dictionary);

        String sentence = "jesslookedjustliketimherbrother";
        String reSpacedSentence = reSpace.bestSplit(sentence);
        assertEquals("jess looked just like tim her brother", reSpacedSentence);
    }

    @Test
    public void testBestSplitWithNoWords() {
        Set<String> dictionary = new HashSet<>();
        ReSpace reSpace = new ReSpace(dictionary);

        String sentence = "jesslookedjustliketimherbrother";
        String reSpacedSentence = reSpace.bestSplit(sentence);
        assertEquals("jesslookedjustliketimherbrother", reSpacedSentence);
    }
}
