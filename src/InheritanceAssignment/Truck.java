package InheritanceAssignment;

public class Truck extends Vehicle {
    //Private double to hold the truck's load capacity
    private double loadCapacity;


    /**
     *pre: none
     *post: Truck created. Truck info initialized
     * with parameters.
     */
    public Truck(String make, String model, int year, double price, double loadCapacity) {
        super(make, model, year, price);
        this.loadCapacity = loadCapacity;
    }


    /**
     *pre: none
     *post: Collects and returns the truck's load capacity
     */
    public double getLoadCapacity() {
        return loadCapacity;
    }


    //Displays and formats truck details
    @Override
    public void displayDetails() {
        System.out.println("Truck Details:");
        System.out.println("Make: " + getMake());
        System.out.println("Model: " + getModel());
        System.out.println("Year: " + getYear());
        System.out.println("Price: $" + getPrice());
        System.out.println("Load Capacity: " + loadCapacity + " tons");
    }
}
