import java.util.Scanner;   //Input class

/**
 * Program that reads an integer N and prints all the odd numbers less than N.
 *
 * @author (PIRATA)
 * @version (2012.04.24)
 */
public class Exercise12
{
    /**
     * This is the main method.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);  //Input from keyboard
        int n = 0, i = 1;
        
        do {    //Making sure n is a positive number
            System.out.print("N: ");
            n = keyboard.nextInt();
        } while(n < 1);
        keyboard.close();
        
        System.out.print("Odd");
        //i starts at 1, and keeps adding 2 until passes N
        while(i <= n) {
            System.out.print(": " + i + " ");
            i += 2;
        }
    }
}
