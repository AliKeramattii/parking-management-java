// ParkingLot.java

/**
 * Represents a parking lot with an entrance queue and an array of stacks.
 * Indexing convention:
 * - Stack indices are 0-based: 0, 1, ..., n-1
 * - Positions inside a stack are 0-based, where 0 means "top of the stack".
 */
public class ParkingLot {

    private Stack[] stacks;
    private int n; // number of stacks
    private int m; // capacity of each stack
    private int queueCapacity;
    private Queue entranceQueue;

    // Time Complexity: O(n)
    public ParkingLot(int n, int m, int queueCapacity) {

        if (n <= 0 || m <= 0 || queueCapacity <= 0) {
            throw new IllegalArgumentException("n, m, and queueCapacity must all be > 0");
        }

        this.n = n;
        this.m = m;
        this.queueCapacity = queueCapacity;

        // Create the array of stacks
        stacks = new Stack[n];
        for (int i = 0; i < n; i++) {
            stacks[i] = new Stack(m);
        }

        // Create the entrance queue
        entranceQueue = new Queue(queueCapacity);
    }

    /**
     * Adds a car to the entrance queue.
     */
    // Time Complexity: O(1)
    public void addCarToQueue(Car car) {

        if (car == null) {
            System.out.println("Cannot add null car to queue.");
            return;
        }

        if (entranceQueue.isFull()) {
            System.out.println("Entrance queue is full. Cannot add " + car + ".");
            return;
        }

        entranceQueue.enqueue(car);
        System.out.println("Added " + car + " to entrance queue.");
    }

    /**
     * Parks the next car from the queue into the first stack that has free space.
     * If all stacks are full, the car is placed back into the queue (if there is space)
     * and false is returned.
     * @return true if parked successfully, false otherwise
     */
    // Time Complexity: O(n)
    public boolean parkNextCarInFirstEmptyStack() {

        if (entranceQueue.isEmpty()) {
            System.out.println("No cars in the entrance queue to park.");
            return false;
        }

        Car car = entranceQueue.dequeue();
        if (car == null) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            if (!stacks[i].isFull()) {
                stacks[i].push(car);
                System.out.println("Parked " + car + " in stack " + i + ".");
                return true;
            }
        }

        System.out.println("All stacks are full. Could not park " + car + ".");

        if (!entranceQueue.isFull()) {
            entranceQueue.enqueue(car);
            System.out.println("Returned " + car + " back to entrance queue.");
        } else {
            System.out.println("Entrance queue is also full. " +
                    "Car " + car + " cannot re-enter the queue.");
        }

        return false;
    }

    /**
     * Parks the next car from the queue into a specific stack, if there is space.
     * If the stack is full or index is invalid, the car is returned to the queue if possible.
     * @param stackIndex the index of the target stack (0-based)
     * @return true if parked successfully, false otherwise
     */
    // Time Complexity: O(1) + O(n) worst
    public boolean parkNextCarInSpecificStack(int stackIndex) {

        if (stackIndex < 0 || stackIndex >= n) {
            System.out.println("Invalid stack index: " + stackIndex);
            return false;
        }

        if (entranceQueue.isEmpty()) {
            System.out.println("No cars in the entrance queue to park.");
            return false;
        }

        Car car = entranceQueue.dequeue();
        if (car == null) {
            return false;
        }

        Stack stack = stacks[stackIndex];

        if (stack.isFull()) {
            System.out.println("Stack " + stackIndex + " is full. Cannot park " + car + ".");

            if (!entranceQueue.isFull()) {
                entranceQueue.enqueue(car);
                System.out.println("Returned " + car + " back to entrance queue.");
            } else {
                System.out.println("Entrance queue is also full. " +
                        "Car " + car + " cannot re-enter the queue.");
            }

            return false;
        }

        stack.push(car);
        System.out.println("Parked " + car + " in stack " + stackIndex + ".");
        return true;
    }

    /**
     * Finds a car by carId in the parking lot.
     * @param carId the id of the car to find
     * @return an array [stackIndex, positionInStack], both 0-based, or null if not found
     */
    // Time Complexity: O(N) over all cars in all stacks
    public int[] find(int carId) {

        Car target = new Car(carId);

        for (int i = 0; i < n; i++) {
            int position = stacks[i].indexOf(target);
            if (position != -1) {
                System.out.println("Found car " + target + " in stack " + i +
                        " at position " + position + " (0 = top).");
                return new int[]{i, position};
            }
        }

        System.out.println("Car with id " + carId + " not found in any stack.");
        return null;
    }

    /**
     * Removes a car from the parking lot only if it is at the top of some stack.
     * If the car is not on the top of any stack, nothing is removed.
     * @param carId the id of the car to remove
     * @return true if a car was removed, false otherwise
     */
    // Time Complexity: O(n)
    public boolean removeCarIfAtTop(int carId) {

        for (int i = 0; i < n; i++) {
            Stack stack = stacks[i];

            if (!stack.isEmpty()) {
                Car topCar = stack.peek();
                if (topCar != null && topCar.getCarId() == carId) {
                    stack.pop();
                    System.out.println("Removed " + topCar +
                            " from top of stack " + i + ".");
                    return true;
                }
            }
        }

        System.out.println("Car with id " + carId +
                " is not at the top of any stack. No car removed.");
        return false;
    }

    /**
     * Sorts the cars in a particular stack by carId using merge sort.
     * @param stackIndex the index of the stack to sort
     */
    // Time Complexity: O(m log m)
    public void sortStackWithMergeSort(int stackIndex) {

        if (stackIndex < 0 || stackIndex >= n) {
            System.out.println("Invalid stack index: " + stackIndex);
            return;
        }

        Stack stack = stacks[stackIndex];

        if (stack.isEmpty()) {
            System.out.println("Stack " + stackIndex + " is empty. Nothing to sort.");
            return;
        }

        stack.sortWithMergeSort();
        System.out.println("Sorted stack " + stackIndex + " by carId using merge sort.");
    }

    /**
     * Moves cars from stack 'fromIndex' to stack 'toIndex'.
     */
    // Time Complexity: O(F + n) where F is the number of cars moved
    public void moveFromStackToStack(int fromIndex, int toIndex) {

        if (fromIndex < 0 || fromIndex >= n || toIndex < 0 || toIndex >= n) {
            System.out.println("Invalid stack indices: from=" +
                    fromIndex + ", to=" + toIndex);
            return;
        }

        if (fromIndex == toIndex) {
            System.out.println("Source and destination stacks are the same. No move performed.");
            return;
        }

        Stack fromStack = stacks[fromIndex];

        if (fromStack.isEmpty()) {
            System.out.println("Source stack " + fromIndex + " is empty. Nothing to move.");
            return;
        }

        int totalFreeSpace = 0;
        for (int i = 0; i < n; i++) {
            if (i == fromIndex) {
                continue;
            }
            totalFreeSpace += (m - stacks[i].size());
        }

        if (totalFreeSpace == 0) {
            System.out.println("No free space in other stacks. Cannot move cars from stack " +
                    fromIndex + ".");
            return;
        }

        int visitedStacks = 0;
        int currentIndex = toIndex;

        while (!fromStack.isEmpty() && visitedStacks < n - 1) {

            if (currentIndex == fromIndex) {
                currentIndex = (currentIndex + 1) % n;
                visitedStacks++;
                continue;
            }

            Stack destStack = stacks[currentIndex];

            while (!fromStack.isEmpty() && !destStack.isFull()) {
                Car movedCar = fromStack.pop();
                destStack.push(movedCar);
                System.out.println("Moved " + movedCar + " from stack " +
                        fromIndex + " to stack " + currentIndex + ".");
            }

            currentIndex = (currentIndex + 1) % n;
            visitedStacks++;
        }

        if (fromStack.isEmpty()) {
            System.out.println("All cars moved from stack " + fromIndex + ".");
        } else {
            System.out.println("Not enough space to move all cars from stack " +
                    fromIndex + ". " + fromStack.size() + " car(s) remain.");
        }
    }

    /**
     * Prints the current state of the parking lot.
     */
    // Time Complexity: O(N)
    public void printParkingState() {

        System.out.println("==== Parking Lot State ====");
        System.out.println("Entrance queue (capacity " + queueCapacity + "): " + entranceQueue);

        for (int i = 0; i < n; i++) {
            System.out.println("Stack " + i + " (capacity " + m + "): " + stacks[i]);
        }

        System.out.println("===========================");
    }
}
