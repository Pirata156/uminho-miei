import java.util.Scanner;   //Input class

/**
 * Modify Exercise03 so that both the reading of the N elements and the determination of the maximum element of the array are carried out in auxiliary methods of the main() method.
 *
 * @author (PIRATA)
 * @version (2012.03.05)
 */
public class Exercise04
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
     * Auxiliary method to search the highest value from an integer array.
     *
     * @param list  List of elements to search from
     * @param n     Number of elements to be read
     * @return      Array index of the highest value or -1 if empty list.
     */
    public static int indexMaxValue(int[] list, int n)
    {
        int i;
        int index = n - 1;   //Avoids me if statement to check if list is empty
        for(i = 0; i < n; i++) {
            if(list[i] >= list[index]) {
                index = i;
            }
        }
        return index;
    }
    
    /**
     * This is the main method.
     * All input values are assumed correct and in the expected bounds.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        int[] listA;
        int n, indexBig;
        Scanner keyboard = new Scanner(System.in);  //Input from keyboard
        
        System.out.print("Number of elements to read (N): ");
        n = keyboard.nextInt();     //Assuming: N >= 0
        keyboard.close();
        
        listA = readIntArray(n);
        indexBig = indexMaxValue(listA, n);
        
        if(n <= 0) {   //Or indexBig < 0;
            System.out.println("Empty list.");
        } else {
            System.out.println("Highest value out of " + n + " values is " + listA[indexBig] + " at index " + indexBig + ".");
        }
    }
}
