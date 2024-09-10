import java.util.Scanner;   //Input class
import static java.lang.System.out;     //Static import - eliminates prefixes

/**
 * Being N and EXP inputted by the user, read N numbers (real) and return the result of their EXPonent powers.
 *
 * @author (PIRATA)
 * @version (2012.02.27)
 */
public class Exercise03
{
    /**
     * This method is used to return the value of the first argument raised to the power of the second argument.
     *
     * @param base  base
     * @param exp   exponent
     * @return      the value of base raised to the power of exp
     */
    public static double powDouble(double base, int exp)
    {
        return Math.pow(base, exp);
    }
    
    /**
     * This is the main method.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);  //Input from keyboard
        double inValue, outValue;     //Input and output value storage
        int n, exp, i;
        
        out.print("Number of inputs: ");
        n = keyboard.nextInt();
        out.print("Exponent: ");
        exp = keyboard.nextInt();
        
        //for(<counter>; <condition>; <counter update>) { }
        for(i = 1; i <= n; i++) {
            out.print("Value #" + i + ": ");
            inValue = keyboard.nextDouble();
            outValue = powDouble(inValue, exp);
            out.println("Value " + inValue + " to the power of " + exp + ": " + String.format("%.4e",outValue));    //e - real number, floating point
            //Using String.format to print 4 decimals. Could use printf instead
        }
        keyboard.close();
    }
}
