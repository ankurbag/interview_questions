package ctci.linkedLists;

import ctci.utils.StreamUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 *
 */
public class ListUtils {
	private ListUtils() {
	}

	public static LinkedListNode createLinkedList(int... listData) {
		LinkedListNode head = new LinkedListNode(listData[0]);
		LinkedListNode[] last = {head};
		Arrays.stream(listData).skip(1).forEach(d -> {
			LinkedListNode newNode = new LinkedListNode(d);
			last[0].setNext(newNode);
			last[0] = newNode;
		});

		return head;
	}

	public static List<Integer> extractData(LinkedListNode head) {
		return StreamUtil.takeWhile(head, LinkedListNode::next, Objects::nonNull)
				.map(LinkedListNode::getData).collect(toList());
	}
}
