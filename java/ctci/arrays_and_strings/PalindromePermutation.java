package ctci.arrays_and_strings;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 1.4 Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or
 * phrase that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome does not
 * need to be limited to just dictionary words.
 */
public class PalindromePermutation {
	public static boolean isPermutationOfPalindrome(String str) {
		PermutationCounter counter = new PermutationCounter();
		str.chars().map(Character::toLowerCase).forEach(counter::update);
		return counter.isOddCount();
	}

	/**
	 * Executes test cases.
	 *
	 * @param args ignored
	 */
	public static void main(String[] args) {
		assertTrue(isPermutationOfPalindrome("abccba"));
		assertTrue(isPermutationOfPalindrome("a1bccba1"));
		assertFalse(isPermutationOfPalindrome("a1bccba2"));
		assertFalse(isPermutationOfPalindrome("abcdef"));
	}

	/**
	 * Maintains the state of the counter. Thread-unsafe.
	 */
	private static class PermutationCounter {
		private int[] charCount = new int[128];
		private int oddCount = 0;

		private void update(int ch) {
			charCount[ch]++;
			oddCount = charCount[ch] % 2 == 1 ? oddCount + 1 : oddCount - 1;
		}

		private boolean isOddCount() {
			return oddCount <= 1;
		}
	}
}
