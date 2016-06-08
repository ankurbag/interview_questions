package ctci.hard;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;

/**
 * <b>17.5 Letters and Numbers:</b> Given an array filled with letters and numbers, find the longest sub-array with an
 * equal number of letters and numbers.
 */
@SuppressWarnings("WeakerAccess")
public class LettersAndNumbers {
    public char[] findLongestSubArray(char[] array) {
        AtomicInteger delta = new AtomicInteger(0);
        List<Integer> deltas = IntStream.range(0, array.length)
                .map(i -> array[i])
                .mapToObj(ch -> Character.isLetter((char) ch) ? delta.incrementAndGet() : delta.decrementAndGet())
                .collect(Collectors.toList());

        Map<Integer, Integer> longestMatchMap = new HashMap<>();
        longestMatchMap.put(0, -1);
        Optional<Range> maxRange = IntStream.range(0, deltas.size())
                .mapToObj(i -> createRange(i, deltas, longestMatchMap))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .max(comparing(k -> k.end - k.start));

        return maxRange.isPresent() ?
                Arrays.copyOfRange(array, maxRange.get().start, maxRange.get().end + 1) : new char[0];
    }

    private Optional<Range> createRange(int index, List<Integer> deltas, Map<Integer, Integer> longestMatchMap) {
        Integer prevValue = longestMatchMap.putIfAbsent(deltas.get(index), index);
        return prevValue == null ? Optional.empty() : Optional.of(new Range(prevValue + 1, index));
    }

    public static class Range {
        private final int start;
        private final int end;

        private Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
