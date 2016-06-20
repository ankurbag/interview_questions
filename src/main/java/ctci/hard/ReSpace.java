package ctci.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 17.13 Re-Space: Oh, no! You have accidentally removed all spaces, punctuation and capitalization in a lengthy
 * document. A sentence like "I reset the computer. It still didn't boot!" became "iresetthecomputeritsstilldidntboot".
 * You'll deal with the punctuation and capitalization later, right now you need to re-insert the spaces. Most of the
 * words are in a dictionary but a few are not. Given a dictionary (a list of strings) and the document (a string),
 * design an algorithm to un-concatenate the document in a way that minimizes the number of unrecognized characters.
 * EXAMPLE
 * Input: jesslookedjustliketimherbrother
 * Output: jess looked just like tim her brother
 */
public class ReSpace {
    private final Set<String> dictionary;

    public ReSpace(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public String bestSplit(String sentence) {
        ParseResult split = split(sentence, new HashMap<>());
        return split.sentence;
    }

    private ParseResult split(String sentence, Map<String, ParseResult> memory) {
        if (memory.containsKey(sentence)) {
            return memory.get(sentence);
        }

        if (dictionary.contains(sentence)) {
            ParseResult parseResult = new ParseResult(sentence.length(), sentence);
            memory.put(sentence, parseResult);
            return parseResult;
        }

        ParseResult best = new ParseResult(0, sentence);

        for (int i = 1; i < sentence.length(); i++) {
            String left = sentence.substring(0, i);
            String right = sentence.substring(i, sentence.length());

            ParseResult result = split(right, memory);
            ParseResult current;

            if (dictionary.contains(left)) {
                current = new ParseResult(result.validLength + left.length(), left + " " + result.sentence);
            } else {
                current = new ParseResult(result.validLength, left + " " + result.sentence);
            }

            best = ParseResult.findBest(best, current);

            if (best.validLength == sentence.length()) {
                break;
            }
        }

        memory.put(sentence, best);
        return best;
    }

    private static class ParseResult {
        private final int validLength;
        private final int totalLength;
        private final String sentence;

        private ParseResult(int validLength, String sentence) {
            this.validLength = validLength;
            this.sentence = sentence;
            this.totalLength = sentence == null ? 0 : sentence.length();
        }

        public static ParseResult findBest(ParseResult result1, ParseResult result2) {
            if (result1 == null) {
                return result2;
            }

            if (result2 == null) {
                return result1;
            }

            ParseResult best;

            if (result1.validLength < result2.validLength) {
                best = result2;
            } else if (result1.validLength > result2.validLength) {
                best = result1;
            } else {
                best = result1.totalLength < result2.totalLength ? result1 : result2;
            }

            return best;
        }

        @Override
        public String toString() {
            return "ParseResult {" + "validLength=" + validLength + ", sentence='" + sentence + '\'' + '}';
        }
    }
}
