package CircularLinkedList;

public class DoubleLinkedList {
    private class Node {
        int age;
        String name;
        Node next;
        Node prev;
        Node(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    private int size;
    private Node head;
    private Node end;
    public DoubleLinkedList() {
        this.size = 0;
        this.head = null;
        this.end = null;
    }

    /********************
     * Pre: None
     * Post: Insert a new node at the front of the double linked list
     * @param name The name to be stored in the new node
     * @param age The age to be stored in the new node
     ********************/
    public void insertFront(String name, int age){
        Node newNode = new Node(name, age);
        newNode.prev = null;
        newNode.next = head;
        if(end==null)end = newNode;
        if(head!=null)head.prev=newNode;
        head=newNode;
        size++;
    }

    /********************
     * Pre: None
     * Post: Insert a new node at the back of the double linked list
     * @param name The name to be stored in the new node
     * @param age The age to be stored in the new node
     ********************/
    public void insertBack(String name, int age){
        Node newNode = new Node(name,age);
        newNode.next=null;
        newNode.prev=end;
        if(head==null)head = newNode;
        if(end!=null)end.next=newNode;
        end = newNode;
        size++;
    }

    /********************
     * Pre: None
     * Post: Display the contents of the double linked list
     ********************/
    public void display(){
        if(head==null&&end==null) {
            System.out.println("Nothing exists to Print!");
            return;
        }
        for (Node iter = head; iter != null; iter = iter.next) {
            System.out.println(iter.name+", "+ iter.age);
        }
        System.out.println();
    }

    /********************
     * Pre: None
     * Post: Display the contents of the double linked list in reverse order
     ********************/
    public void displayReversal(){
        if(head==null&&end==null) {
            System.out.println("Nothing exists to Print!");
            return;
        }
        for (Node iter = end; iter != null; iter = iter.prev) {
            System.out.println(iter.name+", "+iter.age);
        }
        System.out.println();
    }

    /********************
     * Pre: None
     * Post: Get the head node of the double linked list
     * @return The head node of the list
     ********************/
    public Node peek(){
        System.out.println("Head:\nName: "+head.name+"\nAge: "+head.age);
        return head;
    }

    /********************
     * Pre: The list must not be empty
     * Post: Remove the first node from the double linked list
     ********************/
    public void removeFront(){
        try {
            if (size <= 0)
                throw new IndexOutOfBoundsException("Position Out of Boundary!!!\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return;
        }
        if (end == head) {
            head = null;
            end = null;
            size--;
            System.out.println("Node Deleted\n");
            return;
        }
        head = head.next;
        head.prev = null;
        size--;
        System.out.println("Node Deleted\n");
        return;
    }

    /********************
     * Pre: The list must not be empty
     * Post: Remove the last node from the double linked list
     ********************/
    public void removeLast(){
        try {
            if (size <= 0)
                throw new IndexOutOfBoundsException("Position Out of Boundary!!!\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return;
        }
        if (end == head) {
            head = null;
            end = null;
            size--;
            System.out.println("Node Deleted\n");
            return;
        }
        end = end.prev;
        end.next = null;
        size--;
        System.out.println("Node Deleted\n");
        return;
    }

    /********************
     * Pre: None
     * Post: Delete the node with the specified name from the double linked list
     * @param name The name of the node to be deleted
     ********************/
    public void deleteLink(String name) {
        Node current = head;

        // Traverse the list to find the node with the specified name
        while (current != null && !current.name.equals(name)) {
            current = current.next;
        }

        // If the node with the specified name was not found
        if (current == null) {
            System.out.println("Node with name " + name + " not found.\n");
            return;
        }

        // Adjust pointers to remove the node
        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            // Node to be deleted is the head
            head = current.next;
        }

        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            // Node to be deleted is the end
            end = current.prev;
        }

        // Clear the pointers of the current node
        current.next = null;
        current.prev = null;

        // Decrease the size of the list
        size--;

        System.out.println("Node with name " + name + " deleted.\n");
    }

    /********************
     * Pre: None
     * Post: Check if the double linked list is empty
     * @return true if the list is empty, false otherwise
     ********************/
    public boolean isEmpty(){
        if(size!=0)return false;
        else return true;
    }
}
