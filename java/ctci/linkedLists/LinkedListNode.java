package ctci.linkedLists;

/**
 *
 */
public class LinkedListNode {
	private int data;
	private LinkedListNode next;

	public LinkedListNode(int data) {
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public LinkedListNode next() {
		return next;
	}

	void setNext(LinkedListNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "LinkedListNode{" +
				"data=" + data +
				'}';
	}
}
