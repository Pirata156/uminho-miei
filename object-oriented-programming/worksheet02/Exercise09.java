import java.util.Scanner;   //Input class
import java.lang.Math;      //For the random method
import java.util.Arrays;    //To use the sort and toString methods

/**
 * Program that simulates the Euromillions game.
 * The program randomly generates a key containing 5 numbers (from 1 to 50) and two stars (1 to 9).
 * The user is then asked for 5 numbers and two stars (the bet).
 * The program should then present the generated key and the number of correct numbers and stars for the user's bet.
 * Naturally, arrays must be used to store the data
 *
 * @author (PIRATA)
 * @version (2012.03.12)
 */
public class Exercise09
{
    /**
     * This method checks if a specified value is repeated within an integer array.
     * It returns true if the value is found at least once within the first 'size' elements
     * of the array; otherwise, it returns false.
     *
     * @param array     The integer array to search within.
     * @param size      The number of elements to consider within the array.
     * @param value     The value to check for repetition.
     * @return          true if the 'value' is repeated in the array, false otherwise.
     */
    public static boolean repeatedIn(int[] array, int size, int value)
    {
        int i;
        boolean flag = false;
        
        for(i = 0; i < size && !flag; i++) {
            if(array[i] == value) {
                flag = true;
            }
        }
        return flag;
    }
    
    /**
     * This method creates an array of random non repeated integers with a specified size,
     * where each integer falls within the inclusive range [min, max].
     *
     * @param size  The size of the array to generate.
     * @param min   The minimum value for the generated integers (inclusive).
     * @param max   The maximum value for the generated integers (inclusive).
     * @return      An array of non repeated random integers of the specified size within the given range.
     */
    public static int[] genIntsArray(int size, int min, int max)
    {   //This method could have been done with the min being 1 specifically for this program.
        int i, calc;
        int[] res = new int[size];
        
        for(i = 0; i < size; i++) {
            do {
                calc = (int) (Math.random() * (max - min + 1)) + min;
            } while(repeatedIn(res, i, calc));
            res[i] = calc;
        }
        return res;
    }
    
    /**
     * This method reads and checks user input to create an array of 'size' distinct integers
     * within the range [min, max] (inclusive). It ignores the repeated values.
     *
     * @param size  The number of distinct integers to input.
     * @param min   The minimum value for the input integers (inclusive).
     * @param max   The maximum value for the input integers (inclusive).
     * @return      An array of distinct integers within the specified range, as entered by the user.
     */
    public static int[] inputIntsArray(int size, int min, int max)
    {
        Scanner keyboard = new Scanner(System.in);
        int[] res = new int[size];
        int i, inValue = 0;
        
        System.out.println("Enter " + size + " numbers (repeated numbers are ignored):");
        for(i = 0; i < size; i++) {
            do {
                inValue = keyboard.nextInt();
            } while(inValue < min || inValue > max || repeatedIn(res, i, inValue));
            res[i] = inValue;
        }
        keyboard.close();
        return res;
    }
    
    /**
     * This is the main method.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        int[] genNumbers = new int[5], inNumbers = new int[5];  //Generated numbers and input numbers
        int[] genStars = new int[2], inStars = new int[2];      //Generated stars and input stars
        int[] hitNumbers = new int[5], hitStars = new int[2];   //List for the correctly guessed numbers and stars
        int i;
        
        //Generating the winning values
        genNumbers = genIntsArray(5, 1, 50);
        genStars = genIntsArray(2, 1, 9);
        //Read user guesses
        System.out.println("---- Fill your bet ----");
        inNumbers = inputIntsArray(5, 1, 50);
        inStars = inputIntsArray(2, 1, 9);
        //Sorting
        Arrays.sort(genNumbers);
        Arrays.sort(genStars);
        Arrays.sort(inNumbers);
        Arrays.sort(inStars);
        //Checking for the correctly guessed numbers
        for(i = 0; i < 5; i++) {
            if(i < 2) {
                if(repeatedIn(genStars, 2, inStars[i])) {
                    hitStars[i] = inStars[i];
                } else {
                    hitStars[i] = 0;    //Redundant! In java. A new array has all its values as 0 by default for integers.
                }
            }
            if(repeatedIn(genNumbers, 5, inNumbers[i])) {
                hitNumbers[i] = inNumbers[i];
            } else {
                hitNumbers[i] = 0;      //Redundant! In java. A new array has all its values as 0 by default for integers.
            }
        }
        //Printing out stuffs!
        System.out.println("<><><><><> EUROMILHOES <><><><><>");
        System.out.println("Chave: " + Arrays.toString(genNumbers) + " -- " + Arrays.toString(genStars));
        System.out.println("<><><><><><><><> <><><><><><><><>");
        System.out.print("Acertou:");
        for(i = 0; i < 5; i++) {
            if(hitNumbers[i] != 0) System.out.print(" " + hitNumbers[i] + " -");
        }
        System.out.print("--");
        for(i = 0; i < 2; i++) {
            if(hitStars[i] != 0) System.out.print("- " + hitStars[i] + " ");
        }
        System.out.println("\n<><><><><><><><> <><><><><><><><>");  //So, System.lineSeparator() is a thing! Same as %n for printf
    }
}
