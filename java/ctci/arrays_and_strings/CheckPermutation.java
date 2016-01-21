package ctci.arrays_and_strings;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 1.2 Given two strings, write a method to decide if one is a permutation of the other.
 */
public class CheckPermutation {
	/**
	 * Checks if two given strings are permutations of each other.
	 *
	 * @param str1 first string to check
	 * @param str2 second string to check
	 * @return true if both strings are permutation of each other, false otherwise
	 */
	public static boolean checkPermutation(String str1, String str2) {
		if (str1.length() != str2.length()) {
			return false;
		}

		long count1 = str1.chars().sum();
		long count2 = str2.chars().sum();

		return count1 == count2;
	}

	/**
	 * Executes test cases.
	 *
	 * @param args ignored
	 */
	public static void main(String[] args) {
		assertFalse(checkPermutation("abc", "def"));
		assertTrue(checkPermutation("abc", "cba"));
	}
}
