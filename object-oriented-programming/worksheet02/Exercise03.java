import java.util.Scanner;   //Input class

/**
 * Modify Exercise02 so that reading the N elements into an array is performed using an auxiliary method that receives the value of N as a parameter.
 *
 * @author (PIRATA)
 * @version (2012.03.05)
 */
public class Exercise03
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
        Scanner keyboard = new Scanner(System.in);  //Input from keyboard
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
     * This is the main method.
     * All input values are assumed correct and in the expected bounds.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        int[] listA;
        int n, indexBig, i = 0;
        Scanner keyboard = new Scanner(System.in);  //Input from keyboard
        
        System.out.print("Number of elements to read (N): ");
        n = keyboard.nextInt();     //Assuming: N >= 0
        keyboard.close();
        listA = readIntArray(n);
        //Looking for the largest value entered
        //AUTHOR NOTE: Could have been done same time as input the values but OK. Following the script!
        indexBig = n - 1;
        for(i = 0; i < n; i++) {
            if(listA[i] >= listA[indexBig]) {
                indexBig = i;
            }
        }
        
        if(n <= 0) {
            System.out.println("Empty list.");
        } else {
            System.out.println("Highest value out of " + n + " values is " + listA[indexBig] + " at index " + indexBig + ".");
        }
    }
}
