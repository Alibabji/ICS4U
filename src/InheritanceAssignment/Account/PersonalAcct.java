package InheritanceAssignment.Account;

/**
 * Represents a Personal Account that extends the Account class.
 * It includes specific functionality for personal accounts, such as handling withdrawals
 * with a fee for balances below $100.
 */
public class PersonalAcct extends Account {

    /**
     * Constructor to initialize a Personal Account with provided balance and account holder details.
     * @param bal The initial balance of the account
     * @param fName First name of the account holder
     * @param lName Last name of the account holder
     * @param str Street address of the account holder
     * @param city City of the account holder
     * @param st State of the account holder
     * @param zip ZIP code of the account holder
     */
    public PersonalAcct(double bal, String fName, String lName, String str, String city, String st, String zip) {
        super(bal, fName, lName, str, city, st, zip);
    }

    /**
     * Overrides the withdrawal method from the Account class to include a fee for
     * balances below $100.
     * @param amt The amount to withdraw from the account
     */
    @Override
    public void withdrawal(double amt) {
        double fee = 2.0;
        if (amt <= balance && balance - amt - ((balance - amt < 100) ? fee : 0) >= 0) {
            super.withdrawal(amt);
            if (balance < 100) {
                balance -= fee;
                System.out.println("Balance below $100. $2 fee charged.");
            }
        } else {
            System.out.println("Failed to withdraw due to insufficient balance.");
        }
    }
}
