package InheritanceAssignment;

public class Minivan extends Vehicle {
    //Private integer to hold minivan seating capacity
    private int seatingCapacity;


    /**
     *pre: none
     *post: Minivan created. Minivan info initialized
     * with parameters.
     */
    public Minivan(String make, String model, int year, double price, int seatingCapacity) {
        super(make, model, year, price);
        this.seatingCapacity = seatingCapacity;
    }


    /**
     *pre: none
     *post: Collects and returns the Minivan's seating capacity
     */
    public int getSeatingCapacity() {
        return seatingCapacity;
    }


    //Displays and formats minivan details
    @Override
    public void displayDetails() {
        System.out.println("Minivan Details:");
        System.out.println("Make: " + getMake());
        System.out.println("Model: " + getModel());
        System.out.println("Year: " + getYear());
        System.out.println("Price: $" + getPrice());
        System.out.println("Seating Capacity: " + seatingCapacity);
    }
}

