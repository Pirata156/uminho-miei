import java.util.Scanner;   //Input class

/**
 * Read 10 numbers (int) and determine the highest value inputted.
 *
 * @author (PIRATA)
 * @version (2012.02.27)
 */
public class Exercise02
{
    /**
     * This is the main method.
     * Each value is being compared with the highest as they are inputted.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);  //Input from the keyboard
        int inValue;    //Input value storage
        int highest = Integer.MIN_VALUE;    //Starting it with the lowest value an Integer can take
        int i = 1;
        //Input processing
        while (i <= 10) {
            System.out.print("Value #" + i + ": ");
            inValue = keyboard.nextInt();
            
            if (inValue > highest) {
                highest = inValue;
            }
            i++;
        }
        keyboard.close();   //Input close (optional)
        //Output processing
        System.out.println("Highest value inputted: " + highest);
    }
}
