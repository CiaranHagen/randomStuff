import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  Ciarán John Hagen
 *  @version 6/04/19
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
    }

    @Test
    public void testPushPopAndReverse()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.push(3);
        assertEquals( "Checking first push (3)", "3", testDLL.toString() );
        testDLL.push(4);
        assertEquals( "Checking second push (4)", "4,3", testDLL.toString() );
        testDLL.push(1);
        assertEquals( "Checking third push (1)", "1,4,3", testDLL.toString() );
        testDLL.push(3);
        assertEquals( "Checking fourth push (3)", "3,1,4,3", testDLL.toString() );
        testDLL.push(7);
        assertEquals( "Checking fifth push (7)", "7,3,1,4,3", testDLL.toString() );
        testDLL.reverse();
        assertEquals( "Checking reversed DLL", "3,4,1,3,7", testDLL.toString() );
        int test = testDLL.pop();
        assertEquals( "Checking regular pop value", 3, test );
        assertEquals( "Checking regular pop DLL", "4,1,3,7", testDLL.toString() );
        test = testDLL.pop();
        test = testDLL.pop();
        test = testDLL.pop();
        assertEquals( "Checking second to last pop value", 3, test );
        assertEquals( "Checking second to last pop DLL", "7", testDLL.toString() );
        test = testDLL.pop();
        assertEquals( "Checking last pop value", 7, test );
        assertEquals( "Checking last pop DLL", "", testDLL.toString() );
        test = testDLL.pop();
        assertEquals( "Checking empty pop value", null, test );
        assertEquals( "Checking empty pop DLL", "", testDLL.toString() );
        
        
    }
    
    @Test
    public void testEmpty()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        boolean test = testDLL.isEmpty();
        assertEquals( "Checking emptyness", true, test );
        testDLL.push(3);
        testDLL.push(4);
        testDLL.push(1);
        testDLL.push(3);
        testDLL.push(7);
        test = testDLL.isEmpty();
        assertEquals( "Checking non-emptyness", false, test );
    }
    
    @Test
    public void testGet()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.push(3);
        testDLL.push(4);
        testDLL.push(1);
        testDLL.push(3);
        testDLL.push(7);
        assertEquals( "Checking get head", 7, (int) testDLL.get(0) );
        assertEquals( "Checking get tail", 3, (int) testDLL.get(4) );
        assertEquals( "Checking get middle", 1, (int) testDLL.get(2) );
        assertEquals( "Checking get negative", null, (int) testDLL.get(-5) );
        assertEquals( "Checking get after", null, (int) testDLL.get(700) );
        
    }
    
    @Test
    public void testDelete()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.push(3);
        testDLL.push(4);
        testDLL.push(1);
        testDLL.push(3);
        testDLL.push(7);
        boolean worked = testDLL.deleteAt(-5);
        assertEquals( "Checking return for negative pos", false, worked );
        assertEquals( "Checking list after negative pos", "7,3,1,4,3", testDLL.toString() );
        
        worked = testDLL.deleteAt(700);
        assertEquals( "Checking return for after pos", false, worked );
        assertEquals( "Checking list after after pos", "7,3,1,4,3", testDLL.toString() );
        
        worked = testDLL.deleteAt(0);
        assertEquals( "Checking return for 0 pos", true, worked );
        assertEquals( "Checking list after 0 pos", "3,1,4,3", testDLL.toString() );
        
        worked = testDLL.deleteAt(3);
        assertEquals( "Checking return for last pos", true, worked );
        assertEquals( "Checking list after last pos", "3,1,4", testDLL.toString() );
        
        worked = testDLL.deleteAt(1);
        assertEquals( "Checking return for middle pos", true, worked );
        assertEquals( "Checking list after middle pos", "3,4", testDLL.toString() );
    }
    
    @Test
    public void testUnique()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.push(3);
        testDLL.push(4);
        testDLL.push(1);
        testDLL.push(3);
        testDLL.push(7);
        testDLL.makeUnique();
        assertEquals( "Checking uniqueness with double at end", "7,3,1,4", testDLL.toString() );
        
        DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
        testDLL2.push(4);
        testDLL2.push(3);
        testDLL2.push(1);
        testDLL2.push(3);
        testDLL2.push(7);
        testDLL2.makeUnique();
        assertEquals( "Checking uniqueness with double in middle", "7,3,1,4", testDLL2.toString() );
    }
    
    @Test
    public void testQueue()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        int dater = testDLL.dequeue();
        assertEquals( "Checking empty dequeue value", null, dater );
        assertEquals( "Checking empty dequeue DLL", "", testDLL.toString() );
        testDLL.enqueue(3);
        assertEquals( "Checking enqueue 3", "3", testDLL.toString() );
        testDLL.enqueue(4);
        assertEquals( "Checking enqueue 4", "4,3", testDLL.toString() );
        testDLL.enqueue(1);
        assertEquals( "Checking enqueue 1", "1,4,3", testDLL.toString() );
        testDLL.enqueue(3);
        assertEquals( "Checking enqueue 3", "3,1,4,3", testDLL.toString() );
        testDLL.enqueue(7);
        assertEquals( "Checking enqueue 7", "7,3,1,4,3", testDLL.toString() );
        
        dater = testDLL.dequeue();
        assertEquals( "Checking empty dequeue value", 3, (int) dater );
        assertEquals( "Checking empty dequeue DLL", "7,3,1,4", testDLL.toString() );
        dater = testDLL.dequeue();
        assertEquals( "Checking empty dequeue value", 4, (int) dater );
        assertEquals( "Checking empty dequeue DLL", "7,3,1", testDLL.toString() );
        dater = testDLL.dequeue();
        assertEquals( "Checking empty dequeue value", 1, (int) dater );
        assertEquals( "Checking empty dequeue DLL", "7,3", testDLL.toString() );
        dater = testDLL.dequeue();
        assertEquals( "Checking empty dequeue value", 3, (int) dater );
        assertEquals( "Checking empty dequeue DLL", "7", testDLL.toString() );
        dater = testDLL.dequeue();
        assertEquals( "Checking empty dequeue value", 7, (int) dater );
        assertEquals( "Checking empty dequeue DLL", "", testDLL.toString() );
        
    }
    
    @Test
    public void test()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    }
}

