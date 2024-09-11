import java.util.Scanner;   //Input class

/**
 * Program that reads N integer values into an array and determines the largest value entered and its position in the array.
 *
 * @author (PIRATA)
 * @version (2012.03.05)
 */
public class Exercise02
{
    public static int MAXLENGTH = 100;  //Maximum ammount of elements an array can have
    
    /**
     * This is the main method.
     * All input values are assumed correct and in the expected bounds.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        int[] listA = new int[MAXLENGTH];
        int n, indexBig, i = 0;
        Scanner keyboard = new Scanner(System.in);  //Input from keyboard
        
        System.out.print("Number of elements to read (N): ");
        n = keyboard.nextInt();     //Assuming: N >= 0 and N <= MAXLENGTH
        //Reading array from input
        i = 0;
        while(i < n) {
            System.out.print("list[" + i + "]: ");
            listA[i] = keyboard.nextInt();
            i++;
        }
        keyboard.close();
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
