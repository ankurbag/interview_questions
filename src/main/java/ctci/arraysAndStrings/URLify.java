package ctci.arraysAndStrings;

import static org.junit.Assert.assertEquals;

/**
 * 1.3 Write a method to replace all spaces in a string with'%20'. You may assume that the string has sufficient space
 * at the end of the string to hold the additional characters, and that you are given the "true" length of the string.
 * (Note: if implementing in Java, please use a character array so that you can perform this operation in place.)
 * EXAMPLE
 * Input: "Mr John Smith      ", 13
 * Output: "Mr%20John%20Smith"
 */
public class URLify {
	/**
	 * Replaces all spaces with %20.
	 *
	 * @param urlString the string to update.
	 */
	public static String updateURL(String urlString) {
		return urlString.chars().mapToObj(ch -> ch == ' ' ? "%20" : String.valueOf((char)ch))
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
				.toString();
	}

	/**
	 * Executes test cases.
	 *
	 * @param args ignored
	 */
	public static void main(String[] args) {
		assertEquals("Mr%20John%20Doe", updateURL("Mr John Doe"));
		assertEquals("Mr%20John%20Smith", updateURL("Mr John Smith"));
	}
}
