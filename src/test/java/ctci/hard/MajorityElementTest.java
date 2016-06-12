package ctci.hard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MajorityElementTest {
    @Test
    public void testMajorityElement() {
        assertEquals(1, MajorityElement.findMajorityElement(new int[] {1, 1, 1, 3, 4, 6, 1, 1}).getAsInt());
        assertEquals(7, MajorityElement.findMajorityElement(new int[] {7, 2, 7, 4, 7, 6, 7}).getAsInt());
        assertFalse(MajorityElement.findMajorityElement(new int[] {1, 2, 3, 4, 5, 6, 7}).isPresent());
    }
}
