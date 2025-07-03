package InheritanceAssignment.Account;

/**
 * This class represents a Business Account that extends the Account class.
 * It includes specific functionality for business accounts, such as handling withdrawals
 * with a fee for balances below $500.
 */
public class BusinessAcct extends Account {

    /**
 * Constructor to initialize a Business Account with provided balance and account holder details.
     * @param bal The initial balance of the account
     * @param fName First name of the account holder
     * @param lName Last name of the account holder
     * @param str Street address of the account holder
     * @param city City of the account holder
     * @param st State of the account holder
     * @param zip ZIP code of the account holder
     */
    public BusinessAcct(double bal, String fName, String lName, String str, String city, String st, String zip) {
        super(bal, fName, lName, str, city, st, zip);
    }

    /**
     * Overrides the withdrawal method from the Account class to include a fee for
     * balances below $500.
     * @param amt The amount to withdraw from the account
     */
    @Override
    public void withdrawal(double amt) {
        double fee = 10.0;
        if (amt <= balance && balance - amt - ((balance - amt < 500) ? fee : 0) >= 0) {
            super.withdrawal(amt);
            if (balance < 500) {
                balance -= fee;
                System.out.println("Balance below $500. $10 fee charged.");
            }
        } else {
            System.out.println("Failed to withdraw due to insufficient balance.");
        }
    }
}
