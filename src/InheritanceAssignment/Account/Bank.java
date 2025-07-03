package InheritanceAssignment.Account;

import java.util.Scanner;
import java.text.NumberFormat;

/**
 * Main class to simulate banking operations on personal and business accounts.
 */
public class Bank {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        NumberFormat money = NumberFormat.getCurrencyInstance();

        // Create instances of PersonalAcct and BusinessAcct
        PersonalAcct personalAcct = new PersonalAcct(150, "John", "Doe", "123 Elm St", "Springfield", "IL", "62704");
        BusinessAcct businessAcct = new BusinessAcct(600, "Jane", "Smith", "456 Oak St", "Greenville", "TX", "75401");

        Account selectedAccount = null;
        int choice = 0;

        do {
            try {
                if (selectedAccount == null) {
                    // Prompt user to select an account
                    System.out.println("Select account to make changes:");
                    System.out.println("1. Personal Account");
                    System.out.println("2. Business Account");
                    System.out.print("Enter choice: ");
                    int accountChoice = input.nextInt();

                    // Assign selected account based on user input
                    if (accountChoice == 1) {
                        selectedAccount = personalAcct;
                        System.out.println("Personal account selected.");
                    } else if (accountChoice == 2) {
                        selectedAccount = businessAcct;
                        System.out.println("Business account selected.");
                    } else {
                        System.out.println("Invalid choice. Please select a valid account.");
                        continue; // Continue to prompt for valid input
                    }
                }

                // Display menu options for selected account
                System.out.println("\nSelect an option:");
                System.out.println("1. Deposit");
                System.out.println("2. Withdrawal");
                System.out.println("3. Get balance");
                System.out.println("4. Get info");
                System.out.println("5. Change account");
                System.out.println("6. End program");
                System.out.print("Enter choice: ");
                choice = input.nextInt();

                // Perform operations based on user choice
                switch (choice) {
                    case 1:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = input.nextDouble();
                        selectedAccount.deposit(depositAmount);
                        System.out.println("Deposited " + money.format(depositAmount));
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = input.nextDouble();
                        selectedAccount.withdrawal(withdrawalAmount);
                        break;
                    case 3:
                        System.out.println("Current balance: " + money.format(selectedAccount.getBalance()));
                        break;
                    case 4:
                        // Print the account information
                        System.out.println("Account Information: \n" + selectedAccount.toString());
                        break;
                    case 5:
                        selectedAccount = null;  // Reset the selected account to prompt for re-selection
                        break;
                    case 6:
                        System.out.println("Ending program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
                input.nextLine(); // Clear the invalid input from the scanner
            }
        } while (choice != 6);

        input.close(); // Close the scanner
    }
}
