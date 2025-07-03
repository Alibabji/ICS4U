package Class_Assignment;
import java.util.Scanner;

import static java.lang.Math.abs;

public class FractionOperator {
    private static int numA;
    private static int denA;
    private static int numB;
    private static int denB;
    public FractionOperator()
    {

    }
    /********************
     * Pre: None
     * Post: Calculate the greatest common divisor of two integers using Euclid's algorithm
     * @param a The first integer
     * @param b The second integer
     * @return The greatest common divisor of a and b
     ********************/
    public static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    public static int lcm(int a, int b)
    {
        return a*b/gcd(a,b);
    }

    /********************
     * Pre: None
     * Post: Prompt the user to input numerator and denominator of fractions
     * @param n 1 if inputting the first fraction, 2 if inputting the second fraction
     ********************/
    public static void Input(int n) {
        double temp1, temp2;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Input the numerator: ");
            temp1 = scanner.nextDouble();
            System.out.print("Input the denominator: ");
            temp2 = scanner.nextDouble();
            if (temp2 == 0) System.out.print("Denominator cannot be 0!!\n");
            if (temp1 == (int) temp1 && temp2 == (int) temp2) {
                numA = (int) temp1;
                denA = (int) temp2;
                break;
            }
            System.out.print("INPUT VALUE CANNOT BE DECIMAL!!\n");
        } while (temp2 != 0);
        if (n == 2) {
            do {
                System.out.print("\nInput another numerator: ");
                temp1 = scanner.nextDouble();
                System.out.print("Input another denominator: ");
                temp2 = scanner.nextDouble();
                if (temp2 == 0) System.out.print("Denominator cannot be 0!!\n");
                if (temp1 == (int) temp1 && temp2 == (int) temp2) {
                    numB = (int) temp1;
                    denB = (int) temp2;
                    break;
                }
                System.out.print("INPUT VALUE CANNOT BE DECIMAL!!\n");
            } while (temp2 != 0);
        }
    }

    /********************
     * Pre: None
     * Post: Display the menu options
     ********************/
    public static void ShowMenu() {
        System.out.print("\n-----Menu-----\n1. Multiply\n2. Divide\n3. Add\n4. Subtract\n5. Reduce\n6. Invert\n0. Exit Program\n\nChoice: ");
    }

    /********************
     * Pre: The first fraction has been inputted
     * Post: Reduce the first fraction to its simplest form and display
     ********************/
    public static void ReduceFunc() {
        Input(1);
        int temp = gcd(numA, denA);
        if (denA / temp == 1) System.out.printf("Reduced Value: %d", numA / temp);
        else System.out.printf("Reduced Value: %d/%d", numA / temp, denA / temp);
    }

    /********************
     * Pre: None
     * Post: Reduce a fraction to its simplest form and display
     * @param n The numerator of the fraction
     * @param d The denominator of the fraction
     ********************/
    public static void Reduce(int n, int d) {
        int temp = gcd(n, d);
        if (d / temp == 1) System.out.printf("%d", n / temp);
        else {
            if (n / temp < 0 || d / temp < 0) System.out.print('-');
            System.out.printf("%d/%d\n", abs(n / temp), abs(d / temp));
        }
    }

    /********************
     * Pre: Two fractions have been inputted
     * Post: Multiply the two fractions and display the result
     ********************/
    public static void Multiply() {
        Input(2);
        int num = numA * numB;
        int den = denA * denB;
        System.out.printf("%d/%d * %d/%d = %d/%d\nSimplified: ", numA, denA, numB, denB, num, den);
        Reduce(num, den);
    }

    /********************
     * Pre: Two fractions have been inputted
     * Post: Divide the two fractions and display the result
     ********************/
    public static void Divide()
    {
        Input(2);
        int num = numA*denB;
        int den = denA*numB;
        System.out.printf("%d/%d ÷ %d/%d = %d/%d\nSimplified: ",numA,denA,numB,denB,num,den);
        Reduce(num,den);
    }

    /********************
     * Pre: Two fractions have been inputted
     * Post: Add the two fractions and display the result
     ********************/
    public static void Add()
    {
        Input(2);
        int temp = lcm(denA,denB);
        int num=(numA*(temp/denA))+(numB*(temp/denB));
        int den=temp;
        System.out.printf("%d/%d + %d/%d = %d/%d\nSimplified: ",numA,denA,numB,denB,num,den);
        Reduce(num,den);
    }

    /********************
     * Pre: Two fractions have been inputted
     * Post: Subtract the two fractions and display the result
     ********************/
    public static void Subtract()
    {
        Input(2);
        int temp = lcm(denA,denB);
        int num=(numA*(temp/denA))-(numB*(temp/denB));
        int den=temp;
        System.out.printf("%d/%d - %d/%d = %d/%d\nSimplified: ",numA,denA,numB,denB,num,den);
        Reduce(num,den);
    }

    /********************
     * Pre: One fraction has been inputted
     * Post: Invert the fraction and display the result
     ********************/
    public static void Invert()
    {
        do {
            Input(1);
            if(numA==0)
            {
                System.out.printf("FRACTION WITH NUMERATOR OF 0 CANT ME INVERTED!!\n");
                continue;
            }
            System.out.printf("Inverted Value: %d/%d\nSimplified: ", denA,numA);
            Reduce(denA,numA);
        }while(numA==0);

    }
}
