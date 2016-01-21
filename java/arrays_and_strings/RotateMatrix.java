package arrays_and_strings;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 1.7 Given an image represented by an N*N matrix, where each pixel in the image is 4 bytes, write a method to rotate
 * the image by 90 degrees. Can you do this in place?
 */
public class RotateMatrix {
	/**
	 * Performs in-place rotation of the matrix.
	 *
	 * @param matrix the matrix to rotate
	 */
	public static void rotate(int[][] matrix, int size) {
		IntStream.range(0, size / 2).forEach(
				i -> IntStream.range(i, size - i - 1).forEach(
						j -> updateMatrix(i, size - i - 1, j - i, matrix)));
	}

	/**
	 * Executes test cases.
	 *
	 * @param args ignored
	 */
	public static void main(String[] args) {
		int[][] matrix = new int[][]{
				{1, 2, 3, 4, 5, 6},
				{7, 8, 9, 10, 11, 12},
				{13, 14, 15, 16, 17, 18},
				{19, 20, 21, 22, 23, 24},
				{25, 26, 27, 28, 29, 30},
				{31, 32, 33, 34, 35, 36},
		};

		rotate(matrix, matrix.length);
		IntStream.range(0, matrix.length).forEach(i -> System.out.println(Arrays.toString(matrix[i])));
	}

	private static void updateMatrix(int first, int last, int i, int[][] matrix) {
		int top = matrix[first][first + i];
		int right = matrix[first + i][last];
		int bottom = matrix[last][last - i];
		int left = matrix[last - i][first];

		matrix[first][first + i] = left;
		matrix[first + i][last] = top;
		matrix[last][last - i] = right;
		matrix[last - i][first] = bottom;
	}
}
