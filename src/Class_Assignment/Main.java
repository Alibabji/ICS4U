package Class_Assignment;
/*
Name: Nick Jeong
Title: Class Assignement
Date: March 10th, 2024
Description: Multi-functional fraction calculator
*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creating a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Creating an instance of FractionOperator class to perform fraction operations
        FractionOperator frac = new FractionOperator();

        double num; // Variable to store user input

        do {
            // Displaying the menu of operations
            frac.ShowMenu();

            // Getting user input for the selected operation
            num = scanner.nextDouble();

            // Checking if the input is a whole number
            if (num != (int) num) {
                System.out.println("Invalid Input!!");
                continue;
            }

            // Performing operations based on user input
            switch ((int) num) {
                case 1:
                    frac.Multiply(); // Multiplication operation
                    break;
                case 2:
                    frac.Divide(); // Division operation
                    break;
                case 3:
                    frac.Add(); // Addition operation
                    break;
                case 4:
                    frac.Subtract(); // Subtraction operation
                    break;
                case 5:
                    frac.ReduceFunc(); // Reducing fraction
                    break;
                case 6:
                    frac.Invert(); // Inverting fraction
                    break;
                case 0:
                    System.exit(1); // Exiting the program
                default:
                    System.out.print("Invalid Input!!\n"); // Handling invalid input
            }
        } while (true);
    }

}
