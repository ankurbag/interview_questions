package ctci.hard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 17.11 You have a large text file containing words. Given any two words, find the shortest distance (in terms of
 * number of words) between them in the file. If the operation will be repeated many times for the same file (but
 * different pairs of words), can you optimize your solution?
 */
@SuppressWarnings("WeakerAccess")
public class WordsDistance {
    private Map<String, List<Integer>> wordsIndicesMap = new HashMap<>();

    public static WordsDistance slurpWords(Path filePath) throws IOException {
        WordsDistance wordsDistance = new WordsDistance();
        AtomicInteger counter = new AtomicInteger(0);

        Files.lines(filePath)
                .flatMap(line -> Arrays.stream(line.split("[\\s\\.,;\\?]+")))
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(w -> !w.isEmpty())
                .forEach(w -> wordsDistance.addWordIndex(counter, w));

        return wordsDistance;
    }

    public OptionalLong findDistance(String w1, String w2) {
        Objects.requireNonNull(w1);
        Objects.requireNonNull(w2);

        List<Integer> leftIndices = wordsIndicesMap.get(w1);
        List<Integer> rightIndices = wordsIndicesMap.get(w2);

        if (leftIndices == null || rightIndices == null) {
            return OptionalLong.empty();
        }

        if (w1.equals(w2)) {
            return OptionalLong.of(0);
        }

        long minDistance = Long.MAX_VALUE;

        for (int left = 0, right = 0; left < leftIndices.size() && right < rightIndices.size(); ) {
            int leftValue = leftIndices.get(left);
            int rightValue = rightIndices.get(right);
            int absDiff = Math.abs(leftValue - rightValue);

            if (absDiff < minDistance) {
                minDistance = absDiff;
            }

            if (minDistance == 1) {
                return OptionalLong.of(minDistance);
            }

            if (leftValue < rightValue) {
                left++;
            } else {
                right++;
            }
        }

        return OptionalLong.of(minDistance);
    }

    private void addWordIndex(final AtomicInteger counter, final String word) {
        wordsIndicesMap.computeIfAbsent(word, k -> new ArrayList<>()).add(counter.incrementAndGet());
    }
}
