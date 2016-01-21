package ctci.arrays_and_strings;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 1.1 Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional
 * data structures?
 */
public class IsUnique {
	/**
	 * Returns true if the given string has all unique characters. This implementation uses boolean array, one boolean
	 * for each character.
	 *
	 * @param str the string to check and UTF-8 is assumed.
	 *
	 * @return true if the string has all unique chars, false otherwise.
	 */
	public static boolean isUnique(CharSequence str) {
		if (str.length() > 128) {
			return false;
		}

		List<Boolean> charExists = Stream.generate(() -> Boolean.FALSE).limit(128).collect(toList());
		return !str.chars().mapToObj(ch -> charExists.set(ch, true)).anyMatch(Boolean::booleanValue);
	}

	/**
	 * Executes test cases.
	 *
	 * @param args ignored
	 */
	public static void main(String[] args) {
		assertTrue(isUnique("abcdefg"));
		assertFalse(isUnique("aabbbaa"));
	}
}
