/**
 * Simple demo application for the ParkingLot simulation.
 * Uses fixed configuration values for easier testing.
 */
public class ParkingApp {

    // Time Complexity: Overall time is the sum of each individual ParkingLot operation.
    public static void main(String[] args) {

        // Fixed Configuration Values
        final int n = 3; // number of stacks
        final int m = 3; // capacity of each stack
        final int q = 5; // entrance queue capacity

        System.out.println("=== Parking Lot Setup (Simple Demo) ===");

        // Create the parking lot using fixed values
        ParkingLot lot = new ParkingLot(n, m, q);

        System.out.println();
        System.out.println("Created parking lot with " + n + " stack(s),");
        System.out.println("stack capacity = " + m + ", entrance queue capacity = " + q + ".");
        System.out.println();

        // 1) Enqueue a few cars
        System.out.println("=== Step 1: Enqueue some cars ===");
        lot.addCarToQueue(new Car(5));
        lot.addCarToQueue(new Car(2));
        lot.addCarToQueue(new Car(9));
        lot.addCarToQueue(new Car(1));
        lot.addCarToQueue(new Car(7));

        lot.printParkingState();

        // 2) Park some cars in the first non-full stack
        System.out.println("\n=== Step 2: Park cars in first non-full stack ===");
        lot.parkNextCarInFirstEmptyStack(); // parks Car(5) in stack 0
        lot.parkNextCarInFirstEmptyStack(); // parks Car(2) in stack 0
        lot.parkNextCarInFirstEmptyStack(); // parks Car(9) in stack 0

        lot.printParkingState();

        // 3) Park the next car in a specific stack if possible
        System.out.println("\n=== Step 3: Park next car in a specific stack ===");
        int targetStackIndex = 0;
        if (n > 1) {
            targetStackIndex = 1; // parks Car(1) in stack 1
        }
        System.out.println("Trying to park next car in stack " + targetStackIndex + "...");
        lot.parkNextCarInSpecificStack(targetStackIndex);

        lot.printParkingState();

        // 4) Find a car
        System.out.println("\n=== Step 4: Find car with ID 7 (if present) ===");
        lot.find(7); // Car(7) is still in the queue

        // 5) Try to remove a car only if it is at the top of some stack
        System.out.println("\n=== Step 5: Try to remove car with ID 9 (only if at top of stack 0) ===");
        // Car(9) is at the top of stack 0 (position 0)
        lot.removeCarIfAtTop(9);

        lot.printParkingState();

        // 6) Sort stack 0 by carId using merge sort
        System.out.println("\n=== Step 6: Sort stack 0 by carId (merge sort) ===");
        // Stack 0 now has Car(5), Car(2). Sorts to Car(2) at top, then Car(5).
        lot.sortStackWithMergeSort(0);

        lot.printParkingState();

        // 7) Move cars from stack 0 to stack 1 if more than one stack exists
        if (n > 1) {
            System.out.println("\n=== Step 7: Move cars from stack 0 to stack 1 ===");
            // Moves Car(2) and Car(5) from stack 0 to stack 1.
            lot.moveFromStackToStack(0, 1);
            lot.printParkingState();
        } else {
            System.out.println("\nOnly one stack exists; skipping move-from-stack-to-stack step.");
        }

        System.out.println("\n=== Demo finished. ===");
    }
}