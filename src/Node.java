/*
 * Node class for singly linked lists of Cars.
 */
public class Node {

    public Car data;
    public Node next;

    // Time Complexity: O(1)
    public Node(Car data) {
        this.data = data;
        this.next = null;
    }
}
