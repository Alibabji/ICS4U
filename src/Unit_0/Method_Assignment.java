/*
Name: Nick Jeong
Title: Method Assignment
Date: February 15th, 2024
Description: Multi-purpose code that helps user get sum, factorial, isPrime, GCF, average, triangle, square, rectangle and circle's area, and volume of sphere and cone
*/
package Unit_0;
import java.util.Scanner;
import java.lang.Math;

import static java.lang.Math.*;

public class Method_Assignment {
    // Method to round the answer to 4 decimal places
    static double roundAns(double num) {
        return Math.round(num * 10000.0) / 10000.0;
    }

    // Method to display the options
    void showOption() {
        System.out.print("\n1. sum\n2. factorial\n3. isPrime\n4. Greatest Common Factor\n5. Average\n6. Triangle Area\n7. Square root\n8. Rectangle Area\n9. Circle Area\n10. Sphere Volume\n11. Cone Volume\nPress 0 to Exit\nYour Choice: ");
        return;
    }

    // Method to calculate the sum of two integers
    int sum(int a, int b) {
        if (a > b) {
            System.out.println("First number must be smaller than the Second!!");
            return -1;
        }
        int sum=0;
        for(int i=a;i<=b;i++)
            sum+=i;
        return sum;
    }

    // Method to calculate the factorial of a number
    int factorial(int n) {
        if (n < 0) {
            System.out.println("Factorial Value out of boundary!!");
            return -1;
        }
        if (n == 1 || n == 0) return 1;
        int result = 1;
        for (int i = 2; i <= n; i++) {
            if (result > Integer.MAX_VALUE / i) {
                System.out.println("Factorial Value out of boundary!!");
                return -1;
            }
            result *= i;
        }
        return result;
    }

    // Method to check if a number is prime
    boolean isPrime(int n) {
        if (n <=1) return false;
        if (n==2) return true;
        for (int i = 3; i < n; i+=2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Method to calculate the greatest common factor
    int gcf(int a, int b) {
        if(a<1||b<0) {
            System.out.println("Invalid Input!");
            return -1;
        }
        if(b == 0) return a;
        return gcf(b, a % b);
    }

    // Method to calculate the average of two numbers
    double average(double a, double b) {
        int sum=0;
        if (a > b) {
            System.out.println("First number must be smaller than the Second!!");
            return -1;
        }
        int j=0;
        for (int i = (int) a; i <= (int) b; i++,j++)
        {
            sum+=i;
        }
        return roundAns((double) sum/ j);
    }

    // Method to calculate the area of a triangle
    double triangleArea(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0 || (a + b <= c) || (a + c <= b) || (b + c <= a)) {
            System.out.println("Invalid Input!!");
            return 0;
        }
        double s = (a + b + c) / 2;
        return roundAns(Math.sqrt(s * ((s - a) * (s - b) * (s - c))));
    }

    // Method to calculate the square root using the Babylonian method
    double mySqrt(double n) {
        if(n<0) {
            System.out.print("Invalid Input, returns -1\n");
            return -1;
        }
        double a=n/2;
        double b=n/a;
        while(Math.abs(a-b)>0.0001) {
            a=(a+b)/2;
            b=n/a;
        }
        return roundAns(a);
    }

    // Method to calculate the area of a rectangle
    double rectangleArea(double a, double b){
        if(a==0||b==0) System.out.print("Invalid Input, The rectangle's area can't be 0!\n");
        else if(a<0||b<0) System.out.print("Invalid Input, Area can't be smaller than 0!\n");
        else return roundAns(a*b);
        return -1;
    }

    // Method to calculate the area of a circle
    double circleArea(double r){
        if(r<=0) System.out.print("Invalid Input, The radius must be bigger than 0!\n");
        else return roundAns(Math.PI*(Math.pow(r,2)));
        return -1;
    }

    // Method to calculate the volume of a sphere
    double SphereVolume(double r) {
        if(r<=0) System.out.print("Invalid Input, The radius must be bigger than 0!\n");
        else return roundAns((4 / 3.0) * Math.PI * Math.pow(r, 3));
        return -1;
    }

    // Method to calculate the volume of a cone
    double coneVolume(double r, double h) {
        if (r <= 0) {
            System.out.println("Invalid Input, The radius must be bigger than 0!");
            return -1;
        } else if (h <= 0) {
            System.out.println("Invalid Input, The height must be bigger than 0!");
            return -1;
        } else {
            return roundAns((1.0 / 3) * Math.PI * Math.pow(r, 2) * h);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Initialize Scanner object for user input
        Method_Assignment hw = new Method_Assignment(); // Create an instance of the Method_Assignment// class
        double option; // Variable to store the user's selected option

        // Start an infinite loop to continuously display options and handle user input
        while(true) {
            while(true)
            {
                hw.showOption(); // Display the available options to the user
                option = scanner.nextDouble();
                if(option==(int)option)break;
                System.out.println("Input value invalid!!");
            }// Read the user's input as the selected option

            // Exit the loop if the user chooses option 0
            if (option == 0) break;
            double num1, num2, num3;
            // Perform actions based on the selected option using a switch statement
            switch((int)option) {
                case 1:
                    // Prompt the user to enter two numbers and display the sum
                    do {
                        System.out.println("Enter two numbers:");
                        num1 = scanner.nextDouble();
                        num2 = scanner.nextDouble();
                        if (num1 != (int) num1 || num2 != (int) num2)
                            System.out.println("Input must be integers!");
                    }while(hw.sum((int)num1, (int)num2)==-1||num1 != (int) num1 || num2 != (int) num2);
                    System.out.println("Sum: " + hw.sum((int)num1, (int)num2));
                    break;
                case 2:
                    // Prompt the user to enter a number and display its factorial unless the factorial value is over integer's boundary.
                    do {
                        System.out.println("Enter a number:");
                        num1 = scanner.nextDouble();
                        if (num1 != (int) num1)
                            System.out.println("Input must be integers!");
                    }while(hw.factorial((int)num1)==-1||num1 != (int) num1);
                    System.out.println("Factorial: " + hw.factorial((int)num1));
                    break;
                case 3:
                    // Prompt the user to enter a number and check if it's prime
                    do {
                        System.out.println("Enter a number:");
                        num1 = scanner.nextDouble();
                        if (num1 != (int) num1)
                            System.out.println("Input must be integers!");
                    }while(num1 != (int) num1);
                    System.out.println("Is Prime: " + hw.isPrime((int)num1));
                    break;
                case 4:
                    // Prompt the user to enter two numbers and display their greatest common factor
                    do {
                        System.out.println("Enter two numbers:");
                        num1 = scanner.nextDouble();
                        num2 = scanner.nextDouble();
                        if (num1 != (int) num1 || num2 != (int) num2)
                            System.out.println("Input must be integers!");
                    }while(hw.gcf((int)num1,(int)num2)==-1||num1 != (int) num1 || num2 != (int) num2);
                    System.out.println("Greatest Common Factor: " + hw.gcf((int)num1, (int)num2));
                    break;
                case 5:
                    // Prompt the user to enter two numbers and display their average
                    do {
                        System.out.println("Enter two numbers:");
                        num1 = scanner.nextDouble();
                        num2 = scanner.nextDouble();
                        if (num1 != (int) num1 || num2 != (int) num2)
                            System.out.println("Input must be integers!");
                    }while(hw.average(num1, num2)==-1||num1 != (int) num1 || num2 != (int) num2);
                    System.out.println("Average: " + hw.average(num1, num2));
                    break;
                case 6:
                    // Continuously prompt the user to enter three sides of a triangle until valid input is provided, then display its area
                    do {
                        System.out.println("Enter three sides of the triangle:");
                        num1 = scanner.nextDouble();
                        num2 = scanner.nextDouble();
                        num3 = scanner.nextDouble();
                    }while(hw.triangleArea(num1,num2,num3)==0);
                    System.out.println("Triangle Area: " + hw.triangleArea(num1, num2, num3));
                    break;
                case 7:
                    // Continuously prompt the user to enter a number until valid input is provided, then display its square root
                    do {
                        System.out.println("Enter a number:");
                        num1 = scanner.nextDouble();
                    }while(hw.mySqrt(num1)==-1);
                    System.out.println("Square Root: " + hw.mySqrt(num1));
                    break;
                case 8:
                    // Continuously prompt the user to enter the length and width of a rectangle until valid input is provided, then display its area
                    do {
                        System.out.println("Enter the length and width of the rectangle:");
                        num1 = scanner.nextDouble();
                        num2 = scanner.nextDouble();
                    }while(hw.rectangleArea(num1,num2)==-1);
                    System.out.println("Rectangle Area: " + hw.rectangleArea(num1, num2));
                    break;
                case 9:
                    // Continuously prompt the user to enter the radius of a circle until valid input is provided, then display its area
                    do {
                        System.out.println("Enter the radius of the circle:");
                        num1 = scanner.nextDouble();
                    }while(hw.circleArea(num1)==-1);
                    System.out.println("Circle Area: " + hw.circleArea(num1));
                    break;
                case 10:
                    // Continuously prompt the user to enter the radius of a sphere until valid input is provided, then display its volume
                    do {
                        System.out.println("Enter the radius of the sphere:");
                        num1 = scanner.nextDouble();
                    }while(hw.SphereVolume(num1)==-1);
                    System.out.println("Sphere Volume: " + hw.SphereVolume(num1));
                    break;
                case 11:
                    // Continuously prompt the user to enter the radius and height of a cone until valid input is provided, then display its volume
                    do {
                        System.out.println("Enter the radius and height of the cone:");
                        num1 = scanner.nextDouble();
                        num2 = scanner.nextDouble();
                    } while(hw.coneVolume(num1,num2)==-1);
                    System.out.println("Cone Volume: " + hw.coneVolume(num1, num2));
                    break;
                default:
                    // Display an error message for invalid options
                    System.out.println("Invalid Option!");
                    break;
            }
        }
    } // End of main method
} // End of Method_Assignment Class
