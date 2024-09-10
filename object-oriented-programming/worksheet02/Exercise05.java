import java.util.Scanner;   //Input class
import java.util.Arrays;    //Arrays class

/**
 * Program that reads N integer elements into an array, but inserts them in such a way that the array always remains sorted in ascending order.
 *
 * @author (PIRATA)
 * @version (2012.04.25)
 */
public class Exercise05
{
    /**
     * This is the main method.
     * All input values are assumed correct and in the expected bounds.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        int[] ordList;
        int n, i = 0, j, inValue;
        
        System.out.print("Number of elements to read (N): ");
        n = keyboard.nextInt();     //Assuming: N >= 0
        ordList = new int[n];
        
        while(i < n) {
            System.out.print("Value #" + (i+1) + ": ");
            inValue = keyboard.nextInt();
            j = i;
            while(j > 0 && inValue < ordList[j-1]) {
                ordList[j] = ordList[j-1];
                j--;
            }
            ordList[j] = inValue;
            i++;
        }
        
        if(n <= 0) {
            System.out.println("Empty list.");
        } else {
            //Printing ordered array
            System.out.println(Arrays.toString(ordList));
        }
    }
}
