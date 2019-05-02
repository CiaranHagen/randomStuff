// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  
 *  @version 18/09/18 12:21:09
 */
class Collinear
{

   // ----------------------------------------------------------
    /**
     * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
     * A non-horizontal line will have to cross all three of these lines. Thus
     * we are looking for 3 points, each in a1, a2, a3 which lie on the same
     * line.
     *
     * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
     * 
     * x1(y2−y3)+x2(y3−y1)+x3(y1−y2)=0 
     *
     * In our case y1=1, y2=2, y3=3.
     *
     * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
     *
     * ----------------------------------------------------------
     *
     * 
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of growth: O(length(a1)*length(a2)*length(a3)) = O(n³)
     *
     *  Explanation: The method goes through each array and compares each element to each 
     *  element, creating length(a1)*length(a2)*length(a3) groups for comparison. 
     */
    static int countCollinear(int[] a1, int[] a2, int[] a3)
    {
      int res = 0;
      for (int x1 = 0; x1<a1.length; x1++){
        for (int x2 = 0; x2<a2.length; x2++){
          for (int x3 = 0; x3<a3.length; x3++){
            if ((-a1[x1]+2*a2[x2]-a3[x3])==0) {
              res++;
            } 
          }
        }
      }
      return res;
    }

    // ----------------------------------------------------------
    /**
     * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinearFast(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * In this implementation you should make non-trivial use of InsertionSort and Binary Search.
     * The performance of this method should be much better than that of the above method.
     *
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: O(length(a1)*length(a3) * log(a2) + length(a2)²) = O(n²log(n) + n²)
     *
     *  Explanation: This method essentially does the same thing as the first, only that it does not randomly check for a 
     *  line, but calculates for all the first and last possible points, where the middle point would have to be. Thus it 
     *  only has to check this particular point instead of every point in a2. This reduces the number of comparisons significantly.
     *  The log(n) and n² come from the binary searches and the sort execution at the start.
     *
     *
     */
    static int countCollinearFast(int[] a1, int[] a2, int[] a3)
    {
      int res = 0;
      Collinear.sort(a2);
      for (int i=0; i < a1.length; i++) {
        for (int j=0; i < a3.length; j++) {
            int prob = (a1[i]+a3[j])/2;
            if (Collinear.binarySearch(a2,prob)) {
                res++;
            }
        }
      }
      return res;
    }

    // ----------------------------------------------------------
    /**
     * Sorts an array of integers according to InsertionSort.
     * This method is static, thus it can be called as Collinear.sort(a)
     * @param a: An UNSORTED array of integers. 
     * @return after the method returns, the array must be in ascending sorted order.
     *
     * ----------------------------------------------------------
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: N^2
     *
     *  Explanation: Two linear for-loops.
     *
     */
    static void sort(int[] a)
    {
      for ( int j = 1; j<a.length; j++)
      {
        int i = j - 1;
        while(i>=0 && a[i]>a[i+1])
        {
          int temp = a[i];
          a[i] = a[i+1];
          a[i+1] = temp;
          i--;
        }
      }
    }

    // ----------------------------------------------------------
    /**
     * Searches for an integer inside an array of integers.
     * This method is static, thus it can be called as Collinear.binarySearch(a,x)
     * @param a: A array of integers SORTED in ascending order.
     * @param x: An integer.
     * @return true if 'x' is contained in 'a'; false otherwise.
     *
     * ----------------------------------------------------------
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: log(length(a))
     *
     *  Explanation: The number of comparisons is exponentially 
     *  proportional to the number of items. i.e. it does one comparison, 
     *  then halves the amount of items and repeats this process.
     *
     */
    static boolean binarySearch(int[] a, int x)
    {
      boolean found = false;
      int pos;
      while (!found && (a.length >= 1)) {
        pos = a.length / 2;
        if (x > a[pos]) {
            System.arraycopy(a, pos+1, a, 0, a.length-pos);
        } else if (x < a[pos]) {
            System.arraycopy(a, 0, a, 0, pos);
        } else {
            found = true;
        }
      }
      return found;
    }

}
