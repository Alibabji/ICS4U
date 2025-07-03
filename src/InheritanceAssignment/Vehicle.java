package InheritanceAssignment;

public abstract class Vehicle {
    //Private variables to hold vehicle details
    private String make;
    private String model;
    private int year;
    private double price;


    /**
     * Constructor
     * pre: none
     * post: A vehicle object is created with specified parameters.
     */
    public Vehicle(String make, String model, int year, double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }


    //Returns the make of the vehicle.
    public String getMake() {
        return make;
    }


    //Returns the model of the vehicle.
    public String getModel() {
        return model;
    }


    //Returns the year of the vehicle.
    public int getYear() {
        return year;
    }


    //Returns the price of the vehicle.
    public double getPrice() {
        return price;
    }


    //Abstract method to be implemented by subclasses to display vehicle details.
    public abstract void displayDetails();
}
