package ctci.hard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ShortestSuperSequenceTest {
    @Test
    public void testSuperSequence() {
        int[] big = {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
        int[] subset = {1, 5, 9};

        ShortestSuperSequence shortestSuperSequence = new ShortestSuperSequence(big, subset);
        ShortestSuperSequence.Range superSequence = shortestSuperSequence.findSuperSequence();
        assertEquals(new ShortestSuperSequence.Range(7, 10), superSequence);
    }

    @Test
    public void testSuperSequenceWithOnlySubSetElements() {
        int[] big = {1, 3, 2, 1, 2, 3, 2, 1, 2, 1, 2, 3, 1, 2, 3};
        int[] subset = {1, 2, 3};

        ShortestSuperSequence shortestSuperSequence = new ShortestSuperSequence(big, subset);
        ShortestSuperSequence.Range superSequence = shortestSuperSequence.findSuperSequence();
        assertEquals(new ShortestSuperSequence.Range(0, 2), superSequence);
    }
}
