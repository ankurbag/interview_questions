package ctci.hard;

/**
 * 17.12 BiNode: Consider a simple data structure called BiNode, which has pointers to two other nodes. The data
 * structure BiNode could be used to represent both a binary tree (where node1 is the left node and node2 is the right
 * node) or a doubly linked list (where node is the previous node and node 2 is the next node). Implement a method to
 * convert a binary search tree (implemented with BiNOde) into a doubly linked list. The values should be kept in order
 * and the operation should be performed in place (that is, on the original data structure).
 */
public class BiNode {
    /**
     * Left node if binary tree or previous node if doubly linked list
     */
    private BiNode node1;

    /**
     * Right node if binary tree or next node if doubly linked list
     */
    private BiNode node2;

    public static BiNode asDoublyLinkedList(BiNode root) {
        BiNode biNode = convertToDoubleLinkedList(root);
        biNode.node1.node2 = null;
        biNode.node1 = null;

        return biNode;
    }

    private static BiNode convertToDoubleLinkedList(BiNode root) {
        if (root == null) {
            return null;
        }

        // leftHead the left most node of the linked list returned from the left node of root i.e. root.node1
        // leftHead.node1 is supposed to point to tail and the root should be appended to the tail.
        BiNode leftHead = convertToDoubleLinkedList(root.node1);

        // rightHead the left most node of the linked list returned from the right node of root i.e. root.node2
        // rightHead.node1 is supposed to point to tail and the root should be prepended to rightHead.
        BiNode rightHead = convertToDoubleLinkedList(root.node2);

        if (leftHead == null && rightHead == null) {
            // straight forward case when both nodes are null. We just need to return root node as head and tail.
            root.node1 = root;
            root.node2 = root;
            return root;
        } else if (leftHead != null && rightHead == null) {
            // there is no left node, which means we need to return root as head of the list. The previous node
            // of root (root.node1) should point to tail of this list (rightHead.node1), and the next node of root
            // (root.node2) should point to rightHead
            BiNode rightTail = rightHead.node1;
            rightHead.node1 = root;
            root.node2 = rightHead;

            // making new circular connections between root (new head) and rightTail
            root.node1 = rightTail;
            rightTail.node2 = root;
            return root;
        } else if (leftHead == null && rightHead != null) {
            // there is no right node, which means we need to return leftHead as head of the list. The previous node
            // of leftHead (leftHead.node1) should point to tail of this list (root), and the next node of root
            // (root.node2) should point to leftHead.
            BiNode leftTail = leftHead.node1;

            // root is new tail and leftTail is second last
            leftTail.node2 = root;
            root.node1 = leftTail;

            // making circular connections between leftHead (head) and root (new tail)
            root.node2 = leftHead;
            leftHead.node1 = root;
            return leftHead;
        } else {
            BiNode leftTail = leftHead.node1;
            leftTail.node2 = root;
            root.node1 = leftTail;

            BiNode rightTail = rightHead.node1;
            rightHead.node1 = root;
            root.node2 = rightHead;

            // making new circular connections between leftHead and rightTail
            leftHead.node1 = rightTail;
            rightTail.node2 = leftHead;

            return leftHead;
        }
    }
}
