package PiggyBank;

import java.util.Scanner;

public class OperatorHandler {
    private static BankingOperator bank;

    // Constructor to initialize the banking operator
    OperatorHandler() {
        bank = new BankingOperator();
    }

    /********************
     * Pre: None
     * Post: Display menu options
     * @return None
     ********************/
    public static void ShowMenu() {
        System.out.print("\n1. Show total in bank.\n2. Add a penny.\n3. Add a nickel.\n4. Add a dime.\n5. Add a quarter.\n6. Take money out of bank.\nEnter 0 to quit\nEnter your choice: ");
        return;
    }

    /********************
     * Pre: None
     * Post: Display the total amount in the bank
     * @return None
     ********************/
    public static void ShowTotal() {
        bank.ShowTotal();
    }

    /********************
     * Pre: None
     * Post: Deposit a penny into the bank
     * @return None
     ********************/
    public static void Penny() {
        bank.Deposit(0.01);
    }

    /********************
     * Pre: None
     * Post: Deposit a nickel into the bank
     * @return None
     ********************/
    public static void Nickel() {
        bank.Deposit(0.05);
    }

    /********************
     * Pre: None
     * Post: Deposit a dime into the bank
     * @return None
     ********************/
    public static void Dime() {
        bank.Deposit(0.1);
    }

    /********************
     * Pre: None
     * Post: Deposit a quarter into the bank
     * @return None
     ********************/
    public static void Quarter() {
        bank.Deposit(0.25);
    }

    /********************
     * Pre: None
     * Post: Withdraw the total amount from the bank
     * @return None
     ********************/
    public static void Withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("How much? : $");
        double temp = scanner.nextDouble();
        bank.Withdraw(temp);
    }
}
