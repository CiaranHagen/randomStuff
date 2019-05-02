import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;  

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Ciarán John Hagen
 *  @version 6/04/19
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: O(1) (linear)
     *
     * Justification:
     *  All this method has to do is check if either the head or the tail of the DLL 
     *  are null. I chose to use head, but either one could be checked as in case of 
     *  an empty list (and only in that case), both are null.
     */
    public boolean isEmpty()
    {
      if (head == null) {
        return true;
      } else {
        return false;
      }
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: O(n)
     *
     * Justification:
     *  In the worst case, the method has to go through each node to find out that 
     *  it is supposed to insert at the end. In that case it has to do n comparisons. 
     *  The relinking and actual inserting can be done in linear time.
     */
    public void insertBefore( int pos, T data ) 
    {
      DLLNode node = head;
      
      if (pos <= 0) {
        DLLNode newNode = new DLLNode(data, null, head);
        head.prev = newNode;
        head = newNode;
      }
      
      for (int i=0; i<pos; i++) {
        node = node.next;
        if (node == null) {
          DLLNode newNode = new DLLNode(data, tail, null);
          tail.next = newNode;
          tail = newNode;
        }
      }
      DLLNode newNode = new DLLNode(data, node.prev, node);
      node.prev.next = newNode;
      node.prev = newNode;
      return;
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: O(n)
     *
     * Justification:
     *  If pos is the position of the last element in the DLL, the method will have 
     *  to iterate each node to figure this out. In that case, it will have to do 
     *  n comparisons.
     *
     */
    public T get(int pos) 
    {
      if (pos < 0) {
        return null;
      }
      DLLNode node = head;
      for (int i=0; i<pos; i++) {
        node = node.next;
        if (node == null) {
          return null;
        }
      }
      return node.data;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic running time cost: O(n)
     *
     * Justification:
     *  As for most of the methods up until this point, this method iterates all the nodes
     *  to find the right position therefore needing O(n) comparisons. The actual 
     *  deletion is done in linear time.
     */
    public boolean deleteAt(int pos) 
    {
      if (pos < 0) {
        return false;
      }
      
      DLLNode node = head;
      for (int i=0; i<pos; i++) {
        node = node.next;
        if (node == null) {
          return false;
        }
      }
      node.next.prev = node.prev;
      node.prev.next = node.next;
      node = null;
      return true;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: O(n)
     *
     * Justification:
     *  This method performs tasks in linear running time for each node in the 
     *  DLL, therefore using O(n) time.
     */
    public void reverse()
    {
      DLLNode node = head;
      DLLNode next = head;
      tail = node;
      while (node != null) {
        next = node.next;
        node.next = node.prev;
        node.prev = next;
        if (next == null) {
          head = node;
        }
        node = next;
      }
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: o(n²)
     *
     * Justification:
     *  This method takes under n² time in the worst case, as it has to run through 
     *  each element to get the data, but only has to compare the data to the data 
     *  of prior nodes respectively. So the head uses 0 comparisons, whereas the 
     *  tail uses n comparisons to determine uniqueness.
     */
     public void makeUnique()
    {
      ArrayList<T> dataL = new ArrayList<T>();
      DLLNode node = head;
      while (node != null) {
        for (int j=0; j<dataL.size(); j++) {
          if (node.data == dataL.get(j)) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node = null;
          }
        }
        dataL.add(node.data);
        node = node.next;
      }
    }


    /*----------------------- STACK API 
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: O(1) (linear)
     *
     * Justification:
     *  All this method has to do is create a new head and relink the head's prev 
     *  in case the head is not null. 
     *  It pushes a new node on top.
     */
    public void push(T item) 
    {
      DLLNode newNode = new DLLNode(item, null, head);
      if (head != null) {
        head.prev = newNode;
      }
      head = newNode;
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: O(1) (linear)
     *
     * Justification:
     *  This method, like the push method just does some relinking. It sets the second element as a head, removes the old head and returns it's value. All of these actions are linear.
     */
    public T pop() 
    {
      if (head == null) {
        return null;
      }
      
      DLLNode popNode = head;
      T datata = popNode.data;
      if ((head != null)&&(head.next != null)) {
        head.next.prev = null;
        head = head.next;
        popNode = null;
      } else if ((head != null)&&(head.next == null)){
        popNode = null;
        head = null;
      }
      return datata;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */
 
    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: O(1) (linear)
     *
     * Justification:
     *  This is essentially the same thing as the push method in this implementation.
     */
    public void enqueue(T item) 
    {
      DLLNode newNode = new DLLNode(item, null, head);
      if (head != null) {
        head.prev = newNode;
      }
      head = newNode;
    }

     /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an equeue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: O(1) (linear)
     *
     * Justification:
     *  This is another case of relinking nodes. It does essentially the same thing as 
     *  the pop method, with the minor difference that it pops the tail off instead 
     *  of the head. It consists however, like the pop method, of linear operations an
     *  thus runs in linear time.
     */
    public T dequeue() 
    {
      T datata = null;
      if (tail != null) {
        DLLNode popNode = tail;
        datata = popNode.data;
        if (tail.prev != null) {
          tail.prev.next = null;
        }
        tail = tail.prev;
        popNode = null;
      }  
      return datata;
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}



