// Shows a Car in a Parking lot
public class Car {

    private int carId;

    // Time Complexity: O(1)
    public Car(int carId) {
        this.carId = carId;
    }

    // Time Complexity: O(1)
    public int getCarId() {
        return carId;
    }

    // Time Complexity: O(1)
    @Override
    public String toString() {
        return "Car(" + carId + ")";
    }

    // Two cars are equal if they have the same carId.
    // Time Complexity: O(1)
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Car other = (Car) obj;
        return this.carId == other.carId;
    }

    // Time Complexity: O(1)
    @Override
    public int hashCode() {
        return Integer.hashCode(carId);
    }

}
