package ctci.arraysAndStrings;

import org.junit.Test;

import static ctci.arraysAndStrings.IsUnique.isUnique;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsUniqueTest {
    @Test
    public void testIsUnique() {
        assertTrue(IsUnique.isUnique("abcdefg"));
        assertFalse(IsUnique.isUnique("aabbbaa"));
    }
}
