package ctci.arraysAndStrings;

import static org.junit.Assert.assertEquals;

/**
 * 1.6 Implement a method to perform basic string compression using the counts of repeated characters. For example, the
 * string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than the original
 * string, your method should return the original string. You can assume the string has only uppercase and lowercase
 * letters (a-z).
 */
public class StringCompression {
	/**
	 * Compresses the given string.
	 *
	 * @param strToCompress the string to compress
	 *
	 * @return compressed string
	 */
	public static String compress(String strToCompress) {
		String compressed = strToCompress.chars()
				.collect(CompressedStringContainer::new, CompressedStringContainer::append,
						CompressedStringContainer::combine).toString();

		return compressed.length() <= strToCompress.length() ? compressed : strToCompress;
	}

	/**
	 * Executes test cases.
	 *
	 * @param args ignored
	 */
	public static void main(String[] args) {
		assertEquals("a5b1c5a3", compress("aaaaabcccccaaa"));
		assertEquals("abc", compress("abc"));
		assertEquals("", compress(""));
	}

	private static class CompressedStringContainer {
		private StringBuilder stringBuilder = new StringBuilder();
		private int count = -1;
		private char currentChar;

		private CompressedStringContainer append(int ch) {
			if (count == -1) {
				reset((char) ch);
			} else if (currentChar != ch) {
				append();
				reset((char) ch);
			} else {
				count++;
			}

			return this;
		}

		private void reset(char ch) {
			currentChar = ch;
			count = 1;
		}

		private CompressedStringContainer combine(CompressedStringContainer compressedStringContainer) {
			this.stringBuilder.append(compressedStringContainer.stringBuilder);
			return this;
		}

		private void append() {
			if (count != -1) {
				stringBuilder.append(currentChar);
				stringBuilder.append(count);
			}
		}

		@Override
		public String toString() {
			append();
			return stringBuilder.toString();
		}
	}
}
