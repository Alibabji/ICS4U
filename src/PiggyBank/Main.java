package PiggyBank;
/*
Name: Nick Jeong
Title: Banking Assignment (Q1)
Date: March 10th, 2024
Description: Banking system that deposit and withdraws money
*/
import Class_Assignment.FractionOperator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creating a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Creating an instance of OperatorHandler class to perform operations
        OperatorHandler acc = new OperatorHandler();

        double num; // Variable to store user input

        do {
            // Displaying the menu of operations
            acc.ShowMenu();

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
                    acc.ShowTotal(); // Show total operation
                    break;
                case 2:
                    acc.Penny(); // Penny operation
                    break;
                case 3:
                    acc.Nickel(); // Nickel operation
                    break;
                case 4:
                    acc.Dime(); // Dime operation
                    break;
                case 5:
                    acc.Quarter(); // Quarter operation
                    break;
                case 6:
                    acc.Withdraw(); // Withdraw operation
                    break;
                case 0:
                    System.exit(1); // Exiting the program
                default:
                    System.out.print("Invalid Input!!\n"); // Handling invalid input
            }
        } while (true);
    }

}

