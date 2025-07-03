package InheritanceAssignment.Account;

/**
 * Represents a customer with basic personal and address information.
 */
public class Customer {
    private String firstName, lastName, street, city, state, zip;

    /**
     * Constructor to initialize a Customer object with provided personal and address details.
     * @param fName The first name of the customer
     * @param lName The last name of the customer
     * @param str The street address of the customer
     * @param c The city of the customer
     * @param s The state of the customer
     * @param z The ZIP code of the customer
     */
    public Customer(String fName, String lName, String str, String c, String s, String z) {
        firstName = fName;
        lastName = lName;
        street = str;
        city = c;
        state = s;
        zip = z;
    }

    /**
     * Returns a string representation of the customer's information.
     * Format:
     * FirstName LastName
     * Street
     * City, State ZIP
     * @return Formatted string representing the customer's information
     */
    public String toString() {
        String custString;
        custString = firstName + " " + lastName + "\n";
        custString += street + "\n";
        custString += city + ", " + state + " " + zip + "\n";
        return custString;
    }
}
