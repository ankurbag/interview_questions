package ctci.linkedLists;

import ctci.utils.StreamUtil;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * 2.1 Write code to remove duplicates from an unsorted linked list.
 * FOLLOW UP:
 * How would you solve this problem if a temporary buffer is not allowed?
 */
public class RemoveDuplicates {
	/**
	 * Executes test cases.
	 *
	 * @param args ignored
	 */
	public static void main(String[] args) {
		LinkedListNode updateList = removeDuplicates(ListUtils.createLinkedList(1, 2, 3, 4, 5, 5, 2, 1));
		assertEquals(Arrays.asList(1, 2, 3, 4, 5), ListUtils.extractData(updateList));

		updateList = removeDuplicates(ListUtils.createLinkedList(1, 1, 1, 1));
		assertEquals(Collections.singletonList(1), ListUtils.extractData(updateList));

		updateList = removeDuplicates(ListUtils.createLinkedList(1, 2, 3, 4));
		assertEquals(Arrays.asList(1, 2, 3, 4), ListUtils.extractData(updateList));

		updateList = removeDuplicates(ListUtils.createLinkedList(1));
		assertEquals(Collections.singletonList(1), ListUtils.extractData(updateList));

//		updateList = removeDuplicatesWithOutBuffer(ListUtils.createLinkedList(1, 2, 3, 4, 5, 5, 2, 1));
//		assertEquals(Arrays.asList(1, 2, 3, 4, 5), ListUtils.extractData(updateList));
//
//		updateList = removeDuplicatesWithOutBuffer(ListUtils.createLinkedList(1, 1, 1, 1));
//		assertEquals(Collections.singletonList(1), ListUtils.extractData(updateList));
//
//		updateList = removeDuplicatesWithOutBuffer(ListUtils.createLinkedList(1, 2, 3, 4));
//		assertEquals(Arrays.asList(1, 2, 3, 4), ListUtils.extractData(updateList));
//
//		updateList = removeDuplicatesWithOutBuffer(ListUtils.createLinkedList(1));
//		assertEquals(Collections.singletonList(1), ListUtils.extractData(updateList));
	}

	/**
	 * Removes duplicate nodes by using a temporary buffer.
	 *
	 * @param head the head node of the linked list. Assumed non-null.
	 *
	 * @return the head node of the updated list
	 */
	public static LinkedListNode removeDuplicates(LinkedListNode head) {
		Set<Integer> uniqueNodesData = new HashSet<>();
		LinkedListPointers pointers = new LinkedListPointers();
		StreamUtil.takeWhile(head, LinkedListNode::next, Objects::nonNull)
				.filter(l -> uniqueNodesData.add(l.getData()))
				.forEach(pointers::update);
		return pointers.head;
	}

	/**
	 * Removes duplicate nodes without using a temporary buffer.
	 *
	 * @param head the head node of the linked list. Assumed non-null.
	 *
	 * @return the head node of the updated list
	 */
	public static LinkedListNode removeDuplicatesWithOutBuffer(LinkedListNode head) {
		LinkedListPointers pointers = new LinkedListPointers();
		StreamUtil.takeWhile(head, LinkedListNode::next, Objects::nonNull)
				.forEach(l1 -> Stream.iterate(l1, LinkedListNode::next)
						.filter(l2 -> l1.getData() != l2.getData())
						.forEach(pointers::update));

		return head;
	}

	private static class LinkedListPointers {
		private LinkedListNode head;
		private LinkedListNode tail;

		private void update(LinkedListNode node) {
			LinkedListNode newNode = new LinkedListNode(node.getData());
			if (head == null) {
				head = newNode;
				tail = head;
			} else {
				tail.setNext(newNode);
				tail = newNode;
			}
		}
	}
}
