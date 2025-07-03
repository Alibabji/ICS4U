package CircularLinkedList;

public class CircularLinkedList {
    private ListNode CL;

    public CircularLinkedList(){
        this.CL = null;
    }

    /********************
     * Pre: None
     * Post: Add a new node to the circular linked list
     * @param str The name to be stored in the new node
     * @param num The age to be stored in the new node
     ********************/
    void addToList(String str, int num){
        ListNode node = new ListNode(str,num);
        if(CL == null){
            CL = node;
            node.next = node;
        }else{
            ListNode current = CL;
            while(current.next != CL){
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
        }
    }

    /********************
     * Pre: None
     * Post: Display the contents of the circular linked list
     ********************/
    void display(){
        if(CL == null){
            System.out.println("No List Exists to Print!!!");
        }else{
            ListNode current = CL;
            while(current.next != CL){
                System.out.print(current.age + ", " + current.name + "\n");
                current = current.next;
            }
            System.out.print(current.age + ", " + current.name+"\n");
            current=current.next;
            System.out.print(current.age + ", " + current.name);
            System.out.println();
        }
    }

    /********************
     * Pre: None
     * Post: Check if the circular linked list is empty
     * @return true if the list is empty, false otherwise
     ********************/
    public boolean isEmpty() {
        return CL == null;
    }

    // Assuming the ListNode class is defined somewhere within the CircularLinkedList package
    private static class ListNode {
        String name;
        int age;
        ListNode next;

        ListNode(String name, int age) {
            this.name = name;
            this.age = age;
            this.next = null;
        }
    }
}
