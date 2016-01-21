package arrays_and_strings;

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
	public static void rotate(int[][] matrix) {
		int size = matrix.length;

		IntStream.range(0, size / 2).forEach(
				layer -> IntStream.range(layer, size - layer - 1)
						.forEach(i -> updateMatrix(layer, i, matrix)));
	}

	/**
	 * Executes test cases.
	 *
	 * @param args ignored
	 */
	public static void main(String[] args) {
		int[][] matrix = new int[][]{
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{9, 10, 11, 12},
				{13, 14, 15, 16}};
		rotate(matrix);
	}

	private static void updateMatrix(int first, int i, int[][] matrix) {
		int offset = i - first;
		int last = matrix.length - 1 - first;
		int top = matrix[first][i];

		matrix[first][i] = matrix[last - offset][first];
		matrix[last][last - offset] = matrix[i][last];
		matrix[i][last] = top;
	}
}
