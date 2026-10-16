/**
 * Simple singly linked list implementation for Car objects.
 */
public class LinkedList {

    private Node head;
    private Node tail;
    private int size;

    // Time Complexity: O(1)
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Time Complexity: O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    // Time Complexity: O(1)
    public int size() {
        return size;
    }

    /**
     * Adds a Car at the beginning (as the new head).
     * Time Complexity: O(1)
     */
    public void addFirst(Car data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;

        if (tail == null) {
            tail = newNode;
        }

        size++;
    }

    /**
     * Removes and returns the first Car of the list.
     * Returns null if the list is empty.
     * Time Complexity: O(1)
     */
    public Car removeFirst() {
        if (head == null) {
            return null;
        }

        Node oldHead = head;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        size--;
        return oldHead.data;
    }

    /**
     * Returns the first Car without removing it.
     * Returns null if empty.
     * Time Complexity: O(1)
     */
    public Car getFirst() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    /**
     * Removes all elements from the list.
     * Time Complexity: O(1)
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns the index (0-based) of the first occurrence of the given Car
     * in the list (using equals). Returns -1 if not found.
     * Time Complexity: O(n)
     */
    public int indexOf(Car item) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if ((item == null && current.data == null) ||
                    (item != null && item.equals(current.data))) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    /**
     * Sorts this linked list using recursive merge sort by carId.
     * The smallest carId will end up at the head.
     * Time Complexity: O(n log n)
     */
    public void sortWithMergeSort() {
        if (size <= 1) {
            return;
        }

        head = mergeSort(head);

        // After sorting, we need to update tail and size.
        recalculateTail();
    }

    // Recursively sorts the list starting at 'h' using merge sort.
    private Node mergeSort(Node h) {
        if (h == null || h.next == null) {
            return h;
        }

        Node middle = getMiddle(h);
        Node nextOfMiddle = middle.next;
        middle.next = null;  // split the list

        Node left = mergeSort(h);
        Node right = mergeSort(nextOfMiddle);

        return merge(left, right);
    }

    // Finds the middle node using slow/fast pointer.
    private Node getMiddle(Node h) {
        if (h == null) {
            return null;
        }

        Node slow = h;
        Node fast = h;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Merges two sorted lists (by Car id) into one sorted list.
    private Node merge(Node left, Node right) {
        Node preHead = new Node(null);
        Node current = preHead;

        while (left != null && right != null) {
            int leftId = left.data.getCarId();
            int rightId = right.data.getCarId();

            if (leftId <= rightId) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }

            current = current.next;
        }

        if (left != null) {
            current.next = left;
        } else if (right != null) {
            current.next = right;
        }

        return preHead.next;
    }

    // Recalculate tail pointer and size.
    private void recalculateTail() {
        Node current = head;
        tail = null;
        int count = 0;

        while (current != null) {
            tail = current;
            current = current.next;
            count++;
        }

        size = count;
    }

    // Time Complexity: O(n)
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }

}
