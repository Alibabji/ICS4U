package arrayAssignment;
/*
Name: Nick Jeong
Title: Array Assignment
Date: February 25th, 2024
Description: prints out entire year calendar for specific year
*/
import java.util.Scanner;

public class calendar {
    /********************
     * Pre: None
     * Post: Show menu options
     * @return None
     ********************/
    public static void showOption() {
        System.out.print("0. Exit Program\n1. Output Calendar\nYour Choice: ");
        return;
    }

    /********************
     * Pre: None
     * Post: Initialize the calendar array with empty spaces
     * @param arr The array to be initialized
     * @return The initialized array
     ********************/
    public static char[][] init(char[][] arr) {
        // Loop through rows and columns to fill with empty spaces
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 64; j++)
                arr[i][j] = ' ';
        }
        // Array of month names
        String[] month={"January","February","March","April","May",
                "June","July","August","September","October","November","December"};
        // Days of the week string
        String day="Su  M Tu  W Th  F Sa  Su  M Tu  W Th  F Sa  Su  M Tu  W Th  F Sa";

        int i=0,j=0,k=0;
        // Loop through each month
        for(;i<12;i++) {
            int temp = (20-month[i].length())/2;
            j+=temp;
            // Add month name to calendar array
            for(int d=0;d<month[i].length();d++)
                arr[k][j+d]=month[i].charAt(d);
            j+=month[i].length();
            j+=20-(temp+month[i].length())+2;
            // Move to the next row after every three months
            if((i+1)%3==0) {
                k+=9;
                j=0;
            }
        }
        i=1;
        // Add days of the week to the calendar array
        for(;i<35;i+=9) {
            for(k=0;k<day.length();j++,k++)
                arr[i][k]=day.charAt(k);
        }
        return arr;
    }

    /********************
     * Pre: None
     * Post: Calculate and fill in the dates for a specific month
     * @param arr The array to fill in
     * @param n Starting position for the month
     * @param maxDay Maximum days in the month
     * @param month The month number
     * @return The updated array
     ********************/
    public static char[][] calc(char[][]arr,int n, int maxDay, int month) {
        int tempX=1+(month-1)%3*22,tempY=2+(month-1)/3*9;
        int y=tempY;
        int x=tempX;
        x+=n*3;
        // Loop through days of the month
        for(int i=1;i<=maxDay;i++,x+=3) {
            int one=i%10;
            int ten = (i-one)/10;
            // Add the tens and ones digits of the date to the calendar array
            if(ten!=0) arr[y][x-1]=(char)(ten+'0');
            arr[y][x]=(char)(one+'0');
            // Move to the next row if a week is completed
            if(((x-tempX)/3+1)%7==0) {
                y++;
                x=tempX-3;
            }
        }
        return arr;
    }

    /********************
     * Pre: None
     * Post: Start generating the calendar for the given year
     * @param arr The array to fill in
     * @param year The year for which the calendar is generated
     * @return The updated array
     ********************/
    public static char[][] start(char[][] arr,int year) {
        int i=1;
        // Loop through each month
        for(;i<=7;i++) {
            int tempYear=year;
            if(i<=2)tempYear-=1;
            int m = ((9+i)%12)+1, C = tempYear/100,D=tempYear%100;
            double f = (1+7)+Math.floor((13.0*m-1)/5.0)+D+Math.floor(D/4.0)+Math.floor(C/4.0)-2.0*C;
            int day=(int)f%7;
            if(day<0) day=7+day;
            // Calculate and fill in dates for February considering leap years
            if(i==2) {
                if(year%4==0) {
                    if(year%100!=0) arr=calc(arr,day,29,2);
                    else if(year%100==0&&year%400==0)arr=calc(arr,day,29,2);
                    else arr=calc(arr,day,28,2);
                }
                else arr=calc(arr,day,28,2);
            }
            // Calculate and fill in dates for other months
            else if(i%2==1) arr=calc(arr,day,31,i);
            else arr=calc(arr,day,30,i);
        }
        // Continue calculating and filling dates for the remaining months
        for(;i<=12;i++) {
            int m = ((9+i)%12)+1,C = year/100,D=year%100;
            double f = (1+7)+Math.floor((13.0*m-1)/5.0)+D+Math.floor(D/4.0)+Math.floor(C/4.0)-2.0*C;
            int day=(int)f%7;
            if(day<0)day=7+day;
            if(i%2==1) {
                arr=calc(arr,day,30,i);
            }
            else arr=calc(arr,day,31,i);
        }
        return arr;
    }

    /********************
     * Pre: None
     * Post: Execute the calendar program
     * @return None
     ********************/
    public static void run() {
        Scanner scanner = new Scanner(System.in);

        char[][] arr = new char[36][64];
        double year = 0.0;
        boolean validInput = false;

        // Prompt user for a valid year
        do {
            System.out.print("Input a year: ");
            year=scanner.nextDouble();
            if(year==(int)year&&year>=0)validInput=true;
            else System.out.println("Invalid input. Please enter a valid positive integer.");
        } while (!validInput);

        // Print the year at the center of the screen
        for (int i = 0; i < 30; i++)
            System.out.print(' ');
        System.out.println((int)year);

        // Initialize and generate the calendar
        arr = init(arr);
        arr = start(arr, (int)year);

        // Display the generated calendar
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 64; j++)
                System.out.print(arr[i][j]);
            System.out.print('\n');
        }
        return;
    }

    // Main method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double option=0;
        // Loop to display menu and execute options
        do {
            showOption();
            option=scanner.nextDouble();

            if(option==1) run();
            else if(option!=0) System.out.println("Invalid Input!");
        } while(option!=0);
    }
}
