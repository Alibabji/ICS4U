package InheritanceAssignment;

public class Car extends Vehicle {
    //Number of doors private integer
    private int numberOfDoors;


    /**
     *pre: none
     *post: Car created. Car info initialized
     * with parameters.
     */
    public Car(String make, String model, int year, double price, int numberOfDoors) {
        super(make, model, year, price);
        this.numberOfDoors = numberOfDoors;
    }


    /**
     *pre: none
     *post: Retrieves and returns number of car doors
     */
    public int getNumberOfDoors() {
        return numberOfDoors;
    }


    //Displays and formats car details
    @Override
    public void displayDetails() {
        System.out.println("Car Details:");
        System.out.println("Make: " + getMake());
        System.out.println("Model: " + getModel());
        System.out.println("Year: " + getYear());
        System.out.println("Price: $" + getPrice());
        System.out.println("Number of Doors: " + numberOfDoors);
    }
}

