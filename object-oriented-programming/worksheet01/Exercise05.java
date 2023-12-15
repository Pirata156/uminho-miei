/**
 * Program that calculates the factorial of an integer, given as an argument of the main() method through its args.
 *
 * @author (PIRATA)
 * @version (2012.02.27)
 */
public class Exercise05
{
    /**
     * This function calculates the factorial of a positive (long) integer.
     * Assumes value n is non negative.
     *
     * @param n     Value to calculate its factorial.
     * @return      Factorial of the value given in n.
     */
    public static long factorial(long n)
    {
        if(n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
    
    /**
     * This is the main method.
     *
     * @param args  Value of the integer to calculate its factorial.
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        long value = Integer.parseInt(args[0]);
        
        System.out.println(value + "! = " + factorial(value) + ";");
    }
}