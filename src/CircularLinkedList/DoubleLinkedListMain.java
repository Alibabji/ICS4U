package CircularLinkedList;

import java.util.Scanner;

/**
 * Main class to demonstrate operations on a circular doubly linked list.
 */
public class DoubleLinkedListMain {
    // Create an instance of DoubleLinkedList
    public static DoubleLinkedList ll = new DoubleLinkedList();

    /**
     * Displays the menu of options for the user.
     */
    public static void showMenu() {
        System.out.print("Please choose an Option:\n"
                + "1. Check if List is empty\n"
                + "2. Insert Front\n"
                + "3. Insert Back\n"
                + "4. Display All\n"
                + "5. Display in Reverse Order\n"
                + "6. Peek at the Top\n"
                + "7. Remove Front\n"
                + "8. Remove Last\n"
                + "9. Delete Link\n"
                + "10. End program\n"
                + "Choice: ");
    }

    /**
     * Helper method to add an element to the linked list.
     * @param fb If true, insert at the back; otherwise, insert at the front.
     */
    public static void addElem(boolean fb){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Age: ");
        int age = scanner.nextInt();
        System.out.print("Name: ");
        String name = scanner.next();
        if (fb) {
            ll.insertBack(name, age);
        } else {
            ll.insertFront(name, age);
        }
        System.out.println("Added to List!\n");
    }

    /**
     * Main method to run the menu-driven program.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            showMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (ll.isEmpty()) {
                        System.out.println("List is empty!!\n");
                    } else {
                        System.out.println("List is not empty!\n");
                    }
                    break;
                case 2:
                    addElem(false); // Insert at front
                    break;
                case 3:
                    addElem(true); // Insert at back
                    break;
                case 4:
                    ll.display(); // Display all nodes
                    break;
                case 5:
                    ll.displayReversal(); // Display in reverse order
                    break;
                case 6:
                    ll.peek(); // Peek at the top element
                    break;
                case 7:
                    ll.removeFront(); // Remove front element
                    break;
                case 8:
                    ll.removeLast(); // Remove last element
                    break;
                case 9:
                    System.out.println("Input a name of the node to delete: ");
                    String temp = scanner.next();
                    ll.deleteLink(temp); // Delete a specific node
                    break;
                case 10:
                    break; // End program
                default:
                    System.out.println("Please input a valid option!!!\n");
            }
        } while (choice != 10);
        scanner.close();
        System.out.println("Program ended.");
    }
}
