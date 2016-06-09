package ctci.arraysAndStrings;

/**
 * 1.2 Given two strings, write a method to decide if one is a permutation of the other.
 */
@SuppressWarnings("WeakerAccess")
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
}
