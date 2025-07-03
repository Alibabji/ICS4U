package CircularLinkedList;

import java.util.Scanner;

/**
 * Main class to demonstrate operations on a circular linked list.
 */
public class Main {

    /**
     * Displays the menu of options for the user.
     */
    public static void showMenu(){
        System.out.print("Please choose an Option:\n"
                + "1. Add new Element to Linked List\n"
                + "2. Check if List is empty\n"
                + "3. Display Linked List\n"
                + "4. End Program\n"
                + "Choice: ");
    }

    /**
     * Main method to run the menu-driven program.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        CircularLinkedList linkedList = new CircularLinkedList();
        int choice = 0;
        do{
            showMenu();
            choice = scanner.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Age: ");
                    int age = scanner.nextInt();
                    System.out.print("Name: ");
                    String name = scanner.next();
                    linkedList.addToList(name,age); // Add element to the linked list
                    System.out.println("Added to List!\n");
                    break;
                case 2:
                    String message = linkedList.isEmpty() ? "The Circular-Linked-List is empty" : "The Circular-Linked-List is not empty";
                    System.out.println(message+"\n");
                    break;
                case 3:
                    if (linkedList.isEmpty()) {
                        System.out.println("List is empty!!\n");
                    } else {
                        linkedList.display(); // Display the linked list
                        System.out.print("\n");
                    }
                    break;
                case 4:
                    break; // End program
                default:
                    System.out.println("Please input a valid option!!!\n");
            }
        } while(choice != 4);

        scanner.close();
        System.out.println("Program ended.");
    }
}
