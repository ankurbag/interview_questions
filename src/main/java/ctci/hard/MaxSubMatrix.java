package ctci.hard;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 17.24 Max Submatrix: Given an NxN matrix of positive and negative integers, write code to find the submatrix with the
 * largest possible sum.
 */
public class MaxSubMatrix {
    private final int[][] matrix;
    private final int width;
    private final int length;

    public MaxSubMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.width = matrix[0].length;
        this.length = matrix.length;
    }

    public Optional<SubMatrix> findMaxSubMatrix() {
        return IntStream.range(0, length)
                .boxed()
                .flatMap(startRow -> createSubMatrix(startRow))
                .max(Comparator.comparing(key -> key.getSum()));
    }

    private Stream<SubMatrix> createSubMatrix(int startRow) {
        int[] mergedSum = new int[width];
        return IntStream.range(startRow, length)
                .peek(endRow -> IntStream.range(0, width).forEach(col -> mergedSum[col] += matrix[endRow][col]))
                .mapToObj(endRow -> maxSubArray(startRow, endRow, mergedSum));
    }

    private SubMatrix maxSubArray(int startRow, int endRow, int[] mergedSum) {
        SubMatrix best = null;
        int startCol = 0;
        int sum = 0;

        for (int i = 0; i < mergedSum.length; i++) {
            sum += mergedSum[i];

            if (best == null || sum > best.sum) {
                best = new SubMatrix(startRow, endRow, startCol, i, sum);
            }

            if (sum < 0) {
                startCol = i + 1;
                sum = 0;
            }
        }

        return best;
    }

    private class SubMatrix {
        private final int startCol;
        private final int endCol;
        private final int sum;
        private final int startRow;
        private final int endRow;

        public SubMatrix(int startRow, int endRow, int startCol, int endCol, int sum) {
            this.startCol = startCol;
            this.endCol = endCol;
            this.startRow = startRow;
            this.endRow = endRow;
            this.sum = sum;
        }

        public int getSum() {
            return sum;
        }
    }
}
