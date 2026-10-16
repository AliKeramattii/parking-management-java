/**
 * Stack implementation on top of LinkedList.
 * The top of the stack is stored at the head of the linked list.
 */
public class Stack {

    private LinkedList list;
    private int capacity;

    // Time Complexity: O(1)
    public Stack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Stack capacity must be > 0");
        }

        this.capacity = capacity;
        this.list = new LinkedList();
    }

    /**
     * Pushes a Car onto the top of the stack.
     * Time Complexity: O(1)
     */
    public void push(Car item) {
        if (isFull()) {
            throw new IllegalStateException("Stack is full");
        }

        list.addFirst(item);
    }

    /**
     * Removes and returns the top Car of the stack.
     * Returns null if the stack is empty.
     * Time Complexity: O(1)
     */
    public Car pop() {
        if (isEmpty()) {
            return null;
        }

        return list.removeFirst();
    }

    /**
     * Returns the top Car without removing it.
     * Returns null if the stack is empty.
     * Time Complexity: O(1)
     */
    public Car peek() {
        if (isEmpty()) {
            return null;
        }

        return list.getFirst();
    }

    // Time Complexity: O(1)
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // Time Complexity: O(1)
    public boolean isFull() {
        return list.size() >= capacity;
    }

    // Time Complexity: O(1)
    public int size() {
        return list.size();
    }

    /**
     * Returns the index (0-based) of the first occurrence of the given Car
     * in the stack, where index 0 means the top of the stack.
     * Returns -1 if not found.
     * Time Complexity: O(n)
     */
    public int indexOf(Car item) {
        return list.indexOf(item);
    }

    /**
     * Sorts the elements in the stack using merge sort by carId.
     * After sorting, the smallest carId will be at the top.
     * Time Complexity: O(n log n)
     */
    public void sortWithMergeSort() {
        list.sortWithMergeSort();
    }

    // Time Complexity: O(n)
    @Override
    public String toString() {
        return list.toString();
    }
}
