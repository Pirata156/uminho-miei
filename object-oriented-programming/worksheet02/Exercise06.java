import java.util.Scanner;   //Input class
import java.util.Arrays;    //Arrays class

/**
 * Program that reads N integer elements into an array, receives two valid indices from
 * the read array and creates an array with only the elements between these two indices.
 * Use a helper method.
 *
 * @author (PIRATA)
 * @version (2012.04.25)
 */
public class Exercise06
{
    /**
     * Auxiliary method to read N elements into an integer array.
     * All input values are assumed correct.
     *
     * @param n     Number of elements to be read
     * @return      Array with the elements read
     */
    public static int[] readIntArray(int n)
    {
        Scanner keyboard = new Scanner(System.in);
        int[] res = new int[n];     //I already know the size of N!
        int i = 0;
        
        while(i < n) {
            System.out.print("list[" + i + "]: ");
            res[i] = keyboard.nextInt();
            i++;
        }
        keyboard.close();
        return res;
    }
    
    /**
     * Auxiliary method to select a sub array, at least 1 element long, from another array.
     *
     * @param list  Non empty array with the elements we want a sub array from
     * @param from  Array index where the sub array starts. Between 0 (inc) and size of list (exc).
     * @param from  Array index where the sub array ends. Between from (inc) and size of list (exc).
     * @return      Non empty array with the elements from index from till index to included. 
     */
    public static int[] subArray(int[] list, int from, int to)
    {
        int i;
        int size = to - from + 1;
        int[] res = new int[size];
        
        for(i = 0; i < size; i++) {
            res[i] = list[from + i];
        }
        return res;
    }
    
    /**
     * Auxiliary method to select a sub array, at least 1 element long, from another array, using arraycopy.
     *
     * @param list  Non empty array with the elements we want a sub array from
     * @param from  Array index where the sub array starts. Between 0 (inc) and size of list (exc).
     * @param from  Array index where the sub array ends. Between from (inc) and size of list (exc).
     * @return      Non empty array with the elements from index from till index to included. 
     */
    public static int[] subArrayCopy(int[] list, int from, int to)
    {
        int size = to - from + 1;
        int[] res = new int[size];
        
        // public static void arraycopy(Object source_arr, int sourcePos, Object dest_arr, int destPos, int len)
        System.arraycopy(list, from, res, 0, size);
        return res;
    }
    
    /**
     * This is the main method.
     * All input values are assumed correct and in the expected bounds. (N > 0)
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        int[] listA, suba, subac;
        int n, st, en;
        
        System.out.print("Number of elements to read (N): ");
        n = keyboard.nextInt();     //Assuming: N > 0
        listA = readIntArray(n);
        
        System.out.print("Index start of sublist: ");
        st = keyboard.nextInt();    //Assuming st >= 0 and st < n;
        System.out.print("Index end of sublist: ");
        en = keyboard.nextInt();    //Assuming en >= st and en < n;
        keyboard.close();
        suba = subArray(listA, st, en);
        subac = subArrayCopy(listA, st, en);
        
        System.out.println("Array: " + Arrays.toString(listA));
        System.out.println("SubArray: " + Arrays.toString(suba));
        System.out.println("SubArrayCopy: " + Arrays.toString(subac));
    }
}
