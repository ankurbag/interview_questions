package ctci.hard;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.OptionalLong;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WordsDistanceTest {
    @Test
    public void testFindDistance() throws IOException {
        URL resource = ClassLoader.getSystemResource("ctci/hard/words_distance.txt");
        Objects.requireNonNull(resource);

        Path filePath = Paths.get(resource.getPath());
        WordsDistance wordsDistance = WordsDistance.slurpWords(filePath);

        OptionalLong dist1 = wordsDistance.findDistance("the", "of");
        assertTrue(dist1.isPresent());
        assertEquals(17, dist1.getAsLong());

        OptionalLong dist2 = wordsDistance.findDistance("charm", "busy");
        assertTrue(dist2.isPresent());
        assertEquals(6, dist2.getAsLong());

        OptionalLong dist3 = wordsDistance.findDistance("in", "this");
        assertTrue(dist3.isPresent());
        assertEquals(1, dist3.getAsLong());

        OptionalLong dist4 = wordsDistance.findDistance("the", "the");
        assertTrue(dist4.isPresent());
        assertEquals(0, dist4.getAsLong());

        OptionalLong dist5 = wordsDistance.findDistance("same", "same");
        assertFalse(dist5.isPresent());
    }
}
