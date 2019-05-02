import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Binary Search Tree
 *
 *  @version 07.04.19
 *
 *  @author  Ciarán John Hagen
 */

@RunWith(JUnit4.class)
public class BSTTest
{
    
    @Test
     public void testMedian() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         assertEquals("Median empty tree", null, bst.median()); 
                 
         bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     |
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     |
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
                          
         assertEquals("Median regular tree", 4, (int) bst.height());        
     }
     
     @Test
     public void testHeight() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         assertEquals("Height empty tree", -1, (int) bst.height()); 
                 
         bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     |
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     |
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
                          
         assertEquals("Height regular tree", 4, (int) bst.height());        
     }

  
  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
      
     @Test
     public void testPrettyPrint() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         assertEquals("Checking pretty printing of empty tree",
                 "-null\n", bst.prettyPrintKeys());
          
                              //  -7
                              //   |-3
                              //   | |-1
                              //   | | |-null
         bst.put(7, 7);       //   | |  -2
         bst.put(8, 8);       //   | |   |-null
         bst.put(3, 3);       //   | |    -null
         bst.put(1, 1);       //   |  -6
         bst.put(2, 2);       //   |   |-4
         bst.put(6, 6);       //   |   | |-null
         bst.put(4, 4);       //   |   |  -5
         bst.put(5, 5);       //   |   |   |-null
                              //   |   |    -null
                              //   |    -null
                              //    -8
                              //     |-null
                              //      -null
         
         String result = 
          "-7\n" +
          " |-3\n" + 
          " | |-1\n" +
          " | | |-null\n" + 
          " | |  -2\n" +
          " | |   |-null\n" +
          " | |    -null\n" +
          " |  -6\n" +
          " |   |-4\n" +
          " |   | |-null\n" +
          " |   |  -5\n" +
          " |   |   |-null\n" +
          " |   |    -null\n" +
          " |    -null\n" +
          "  -8\n" +
          "   |-null\n" +
          "    -null\n";
         assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
     }


     /** <p>Test {@link BST#delete(Comparable)}.</p> */
     @Test
     public void testDelete() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.delete(1);
         assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
         
         bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     |
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     |
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
         
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         
         bst.delete(9);
         assertEquals("Deleting non-existent key",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

         bst.delete(8);
         assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

         bst.delete(6);
         assertEquals("Deleting node with single child (left)",
                 "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

         bst.delete(3);
         assertEquals("Deleting node with two children (left child)",
                 "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
                 
         bst.delete(4);
         assertEquals("Deleting node with single child (right)",
                 "(((()1())2(()5()))7())", bst.printKeysInOrder());   
                 
                 
         BST<Integer, Integer> bst2 = new BST<Integer, Integer>();
         
         bst2.put(2, 2);   //        _2_
         bst2.put(5, 5);   //            |
         bst2.put(4, 4);   //            5
         bst2.put(7, 7);   //          /   |
         bst2.put(6, 6);   //         4    7
         bst2.put(8, 8);   //            /   |
                          //           6    8
                            
         bst2.delete(5);
         assertEquals("Deleting node with two children (right child)",
                 "(()2((()4())6(()7(()8()))))", bst2.printKeysInOrder()); 
     }
     
     
}

