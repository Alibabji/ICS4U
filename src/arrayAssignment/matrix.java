//package arrayAssignment;
///*
//Name: Nick Jeong
//Title: Array Assignment
//Date: February 25th, 2024
//Description: multiplies the method and prints it out
//*/
//import java.util.Scanner;
//
//public class matrix {
//    /********************
//     * Pre: None
//     * Post: Show menu options
//     * @return None
//     ********************/
//    public static void showOption()
//    {
//        System.out.print("1. Multiply two 2x2 Arrays\n0. Exit Program\nChoice: ");
//    }
//
//    // Function to display a matrix
//    /********************
//     * Pre: None
//     * Post: Displays the elements of the matrix
//     * @param x The matrix to be displayed
//     ********************/
//    public static void display(int x[][]) {
//        try {
//            for (int row = 0; row < x.length; row++) {
//                for (int column = 0; column < x[row].length; column++) {
//                    System.out.print(x[row][column] + "\t");
//                }
//                System.out.println();
//            }
//        } catch (Exception e) {
//            System.out.println("An error occurred while displaying the matrix. \n3Please fix error and Restart!");
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        double firstArray[][];
//        double secondArray[][];
//        do{
//            double option=0;
//            showOption();
//            option=scanner.nextDouble();
//            if(option==0) System.exit(0);
//            else if(option!=1) {
//                System.out.println("Invalid Input!");
//                continue;
//            }
//            boolean validInput = false;
//            for(int i=0;i<2;i++)
//            {
//                for(int j=0)
//                do{
//                    firstArray[i][0]=scanner.nextDouble();
//                    if(firstArray[i][0])
//                }while(validInput==true)
//                if()
//            }
//        }
//        try {
//            // Define first array
//            int firstArray[][] = {{1, 2,3}, {4,5,6}, {7,8,9}}; // Edit the values if needed
//            for(int i=0;i<2;i++)
//            {
//
//            }
//            // Define second array
//            int secondArray[][] = {{7, 8, 9, 10},{1,2,3,4}, {11, 12, 13, 14}}; // Edit the values if needed
//
//            // Initialize result array with dimensions of first array rows and second array columns
//            int[][] resultArray = new int[firstArray.length][secondArray[0].length];
//
//            // Display the first array
//            System.out.println("This is the first array");
//            display(firstArray);
//
//            // Display the second array
//            System.out.println("\nThis is the second array");
//            display(secondArray);
//
//            // Perform matrix multiplication
//            for (int i = 0; i < firstArray.length; i++) {
//                for (int j = 0; j < secondArray[0].length; j++) {
//                    for (int k = 0; k < firstArray[0].length; k++) {
//                        // Calculate each element of the result array by multiplying corresponding elements of
//                        // rows from the first array and columns from the second array
//                        resultArray[i][j] += firstArray[i][k] * secondArray[k][j];
//                    }
//                }
//            }
//
//            // Display the result array
//            System.out.println("\nThis is the result array");
//            display(resultArray);
//        } catch (Exception e) {
//            System.out.println("An error occurred in the main method. \nPlease restart after fixing the Error.");
//            System.exit(0);
//        }
//    }
//}
