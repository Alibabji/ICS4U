package CircularLinkedList;

/**
 * Represents a node in a linked list that stores a name, age, and a reference to the next node.
 */
public class ListNode {
    String name;
    int age;
    ListNode next;

    /**
     * Default constructor initializes name to null, age to 0, and next to null.
     */
    public ListNode(){
        this.name = null;
        this.age = 0;
        this.next = null;
    }

    /**
     * Constructor initializes node with provided name and age, and sets next to null.
     * @param name The name to be stored in the node
     * @param age The age to be stored in the node
     */
    public ListNode(String name, int age){
        this.name = name;
        this.age = age;
        this.next = null;
    }

    /**
     * Constructor initializes node with provided name, age, and reference to the next node.
     * @param name The name to be stored in the node
     * @param age The age to be stored in the node
     * @param next Reference to the next ListNode in the linked list
     */
    public ListNode(String name, int age, ListNode next){
        this.name = name;
        this.age = age;
        this.next = next;
    }

    /**
     * Retrieves the age stored in the node.
     * @return The age stored in the node
     */
    public int getAge(){
        return this.age;
    }

    /**
     * Retrieves the name stored in the node.
     * @return The name stored in the node
     */
    public String getName(){
        return this.name;
    }
}
