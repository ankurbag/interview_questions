package ctci.hard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 17.15 Longest Word: Given a list of words, write a program to find the longest word made of other words in the list.
 */
public class LongestWord {
    private final Set<String> dictionary;

    public LongestWord(String[] dictionary) {
        this.dictionary = Arrays.stream(dictionary).collect(Collectors.toSet());
    }

    public Optional<String> findLongestWords() {
        Map<String, Boolean> compositeWords = new HashMap<>();
        return dictionary.stream()
                .sorted(Comparator.comparing(String::length).reversed())
                .filter(str -> isCompositeWord(str, compositeWords))
                .findFirst();
    }

    private boolean isCompositeWord(String word, Map<String, Boolean> memory) {
        if (memory.containsKey(word)) {
            return memory.get(word);
        }

        boolean isCompositeWord = false;

        for (int i = 1; i < word.length(); i++) {
            String left = word.substring(0, i);
            String right = word.substring(i, word.length());

            boolean isLeft = dictionary.contains(left);
            boolean isRight = dictionary.contains(right) || isCompositeWord(right, memory);

            if (isLeft && isRight) {
                isCompositeWord = true;
                break;
            }
        }

        memory.put(word, isCompositeWord);
        return isCompositeWord;
    }
}
