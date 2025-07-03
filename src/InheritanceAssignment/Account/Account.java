package InheritanceAssignment.Account;

import java.text.NumberFormat;

/**
 * Represents a bank account with a customer associated.
 */
public class Account {
    protected double balance;
    private Customer cust;

    /**
     * Constructor to initialize an account with a starting balance and customer information.
     *
     * @param bal  The initial balance of the account
     * @param fName  First name of the customer
     * @param lName  Last name of the customer
     * @param str  Street address of the customer
     * @param city  City of residence of the customer
     * @param st  State of residence of the customer
     * @param zip  ZIP code of the customer
     */
    public Account(double bal, String fName, String lName, String str, String city, String st, String zip) {
        balance = bal;
        cust = new Customer(fName, lName, str, city, st, zip);
    }

    /**
     * Retrieves the current balance of the account.
     *
     * @return The current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposits a specified amount into the account.
     *
     * @param amt The amount to deposit
     */
    public void deposit(double amt) {
        balance += amt;
    }

    /**
     * Withdraws a specified amount from the account if sufficient funds are available.
     *
     * @param amt The amount to withdraw
     */
    public void withdrawal(double amt) {
        if (amt <= balance) {
            balance -= amt;
        } else {
            System.out.println("Not enough money in account.");
        }
    }

    /**
     * Returns a string representation of the account, including customer information and balance.
     *
     * @return A string representation of the account
     */
    public String toString() {
        String accountString;
        NumberFormat money = NumberFormat.getCurrencyInstance();
        accountString = cust.toString();
        accountString += "Current balance is " + money.format(balance);
        return accountString;
    }
}
