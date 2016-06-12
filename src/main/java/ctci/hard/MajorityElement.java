package ctci.hard;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 17.10 Majority Element: A majority element is an element that makes up more than half of the items in an array. Given
 * a positive integers array, find the majority element. If there is no majority element, return -1. Do this in O(N)
 * time and O(1) space.
 */
@SuppressWarnings("WeakerAccess")
public class MajorityElement {
    public static OptionalInt findMajorityElement(int[] array) {
        if (array == null || array.length == 0) {
            return OptionalInt.empty();
        }

        int majority = 0;
        int count = 0;

        for (int i : array) {
            if (count == 0) {
                majority = i;
            }

            if (majority == i) {
                count++;
            } else {
                count--;
            }
        }

        return validate(array, majority);
    }

    private static OptionalInt validate(int[] array, int majority) {
        AtomicInteger count = new AtomicInteger(0);
        return Arrays.stream(array)
                .filter(i -> majority == i)
                .map(i -> count.incrementAndGet())
                .peek(System.out::print)
                .filter(c -> c > array.length / 2)
                .findFirst().isPresent() ? OptionalInt.of(majority) : OptionalInt.empty();
    }
}
