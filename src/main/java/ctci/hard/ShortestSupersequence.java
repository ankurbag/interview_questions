package ctci.hard;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 17.18 Shortest Super-Sequence: You are given two arrays, one shorter (with all distinct elements) and one longer.
 * Find the shortest sub-array in the longer array that contains all the elements in the shorter array. The items can
 * appear in any order.
 * <p>
 * EXAMPLE: Input: {1, 5, 9} {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7}
 * <p>
 * Output: 7, 10
 */
public class ShortestSuperSequence {
    private final int[] big;
    private final Set<Integer> subSet;

    public ShortestSuperSequence(int[] big, int[] subSet) {
        this.big = big;
        this.subSet = Arrays.stream(subSet).boxed().collect(Collectors.toSet());
    }

    public Range findSuperSequence() {
        Map<Integer, LinkedList<SubSetElement>> subSequenceMap = findSubSetMap();
        Range bestRange = null;

        outerLoop:
        while (true) {
            SubSetElement minElement = null;
            SubSetElement maxElement = null;

            for (Map.Entry<Integer, LinkedList<SubSetElement>> e : subSequenceMap.entrySet()) {
                if (e.getValue().size() == 0) {
                    break outerLoop;
                }

                SubSetElement currElement = e.getValue().peek();

                if (minElement == null || currElement.index < minElement.index) {
                    minElement = currElement;
                }

                if (maxElement == null || currElement.index > maxElement.index) {
                    maxElement = currElement;
                }

            }

            subSequenceMap.get(minElement.value).removeFirst();
            bestRange = Range.minRange(bestRange, new Range(minElement.index, maxElement.index));
        }

        return bestRange;
    }

    private Map<Integer, LinkedList<SubSetElement>> findSubSetMap() {
        Map<Integer, LinkedList<SubSetElement>> subSetMap = new HashMap<>();
        IntStream.range(0, big.length)
                .filter(i -> subSet.contains(big[i]))
                .mapToObj(i -> new SubSetElement(i, big[i]))
                .forEach(element -> subSetMap.computeIfAbsent(element.value, k -> new LinkedList<>()).addLast(element));

        return subSetMap;
    }

    public static class SubSetElement {
        private final int index;
        private final int value;

        public SubSetElement(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static class Range {
        private final int min;
        private final int max;

        public Range(int min, int max) {
            this.max = max;
            this.min = min;
        }

        public static Range minRange(Range range1, Range range2) {
            if (range1 == null) {
                return range2;
            }

            if (range2 == null) {
                return range1;
            }

            return range1.max - range1.min > range2.max - range2.min ? range2 : range1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Range range = (Range) o;
            return min == range.min &&
                    max == range.max;
        }

        @Override
        public int hashCode() {
            return Objects.hash(min, max);
        }

        @Override
        public String toString() {
            return "Range {" + min + ", " + max + '}';
        }
    }
}
