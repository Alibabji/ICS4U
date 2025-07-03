package PiggyBank;

public class BankingOperator {
    private static double balance;

    // Constructor to initialize balance
    BankingOperator() {
        balance = 0;
    }

    /********************
     * Pre: None
     * Post: Show total account balance
     * @return None
     ********************/
    public static void ShowTotal() {
        System.out.printf("Account Balance: $%.2f\n", balance);
    }

    /********************
     * Pre: Amount to deposit (n)
     * Post: Deposit the specified amount into the account
     * @param n Amount to deposit
     * @return None
     ********************/
    public static void Deposit(double n) {
        System.out.printf("Total amount of $%.2f was deposited.\n", n);
        balance += n;
    }

    /********************
     * Pre: None
     * Post: Withdraw the total account balance and set balance to zero
     * @return None
     ********************/
    public static void Withdraw(double n) {
        if(n>balance) {
            System.out.printf("WITHDRAWING AMOUNT IS MORE THAN THE BALANCE!!\n");
            return;
        }
        System.out.printf("Total amount of $%.2f was withdrawn.\n", n);
        balance -= n;
    }
}
