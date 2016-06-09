package ctci.arraysAndStrings;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckPermutationsTest {
    @Test
    public void testCheckPermutations() {
        assertFalse(CheckPermutation.checkPermutation("abc", "def"));
        assertTrue(CheckPermutation.checkPermutation("abc", "cba"));
    }
}
