import java.util.Scanner;   //Input class

/**
 * Read a sequence of positive integers (terminated by the value -1) and
 * find the difference between the largest and the smallest integers read.
 * Print the difference as well as the largest and the smallest.
 *
 * @author (PIRATA)
 * @version (2012.03.04)
 */
public class Exercise04
{
    /**
     * This is the main method.
     * Assuming at least 1 integer will be read before the -1.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);  //Input from keyboard
        int inValue;    //Input value storage
        int biggest = 0;    //Starts with the smallest value it can take
        int smallest = Integer.MAX_VALUE;   //Starts with the largest value it can take
        
        System.out.println("Write sequence of positive integers (-1 to end)");
        //do { <instructions> } while (<condition>)
        //runs instructions (at least) once before checking the condition
        do {
            inValue = keyboard.nextInt();
            if(inValue > 0) {
                if(smallest > inValue) {
                    smallest = inValue;
                }
                if(biggest < inValue) {
                    biggest = inValue;
                }
            }
        } while(inValue != -1);
        //Only when inValue == -1 ends reading.
        //If inValue < -1, just skips it and reads another number.
        keyboard.close();
        System.out.println("Smallest: " + smallest + ";\nLargest: " + biggest + ";\nDifference: " + (biggest - smallest) + ";");
    }
}
