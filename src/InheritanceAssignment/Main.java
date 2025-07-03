package InheritanceAssignment;

public class Main {
    public static void main(String[] args){
        Truck truck = new Truck("test","test1",2021,123.2,10.0);
        Car car = new Car("Tesla","Model 3",2019,123000,2);
        Minivan minivan = new Minivan("Test","TTest",100,2,6);
        truck.displayDetails();
        System.out.println();
        car.displayDetails();
        System.out.println();
        minivan.displayDetails();
    }
}
