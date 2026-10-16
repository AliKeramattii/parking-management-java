/**
 * Simple queue implementation using a singly linked list of nodes (Cars).
 */
public class Queue {

    private Node front;  // head
    private Node rear;   // tail
    private int size;
    private int capacity;

    // Time Complexity: O(1)
    public Queue(int capacity) {

        if (capacity <= 0) {
            throw new IllegalArgumentException("Queue capacity must be > 0");
        }

        this.capacity = capacity;
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Adds a Car to the end (rear) of the queue.
     * Throws an exception if the queue is full.
     * Time Complexity: O(1)
     */
    public void enqueue(Car item) {

        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }

        Node newNode = new Node(item);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;
    }

    /**
     * Removes and returns the Car at the front of the queue.
     * Returns null if the queue is empty.
     * Time Complexity: O(1)
     */
    public Car dequeue() {

        if (isEmpty()) {
            return null;
        }

        Node oldFront = front;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;
        return oldFront.data;
    }

    /**
     * Returns the Car at the front without removing it.
     * Returns null if the queue is empty.
     * Time Complexity: O(1)
     */
    public Car peek() {

        if (isEmpty()) {
            return null;
        }

        return front.data;
    }

    // Time Complexity: O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    // Time Complexity: O(1)
    public boolean isFull() {
        return size >= capacity;
    }

    // Time Complexity: O(1)
    public int size() {
        return size;
    }

    // Time Complexity: O(n)
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node current = front;
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
