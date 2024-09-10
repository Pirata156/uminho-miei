import java.util.Scanner;   //Input class

/**
 * Program that reads a non-empty sequence of real numbers ending in 0.0 and calculates
 * its sum (∑) and its product (∏) with a precision of 4 decimal places in the result.
 *
 * @author (PIRATA)
 * @version (2012.04.21)
 */
public class Exercise11
{
    /**
     * This method receives an array and a length of values in the array
     * and calculates the sum of the values in that array
     *
     * @param a     Array of numbers to sum
     * @param len   Length of the array of numbers passed
     * @return      Sum of the numbers in the array received
     */
    public static double sum(float[] a, int len)
    {
        int i;
        double sum = 0;
        for(i = 0; i < len; i++) {
            sum += a[i];
        }
        return sum;
    }
    
    /**
     * This method receives an array and a length of values in the array
     * and calculates the product of the values in that array
     *
     * @param a     Array of numbers to product
     * @param len   Length of the array of numbers passed
     * @return      Product of the numbers in the array received
     */
    public static double prod(float[] a, int len)
    {
        int i;
        double prd = 1;
        for(i = 0; i < len; i++) {
            prd *= a[i];
        }
        return prd;
    }
    
    /**
     * This is the main method.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);  //Input from the keyboard
        float inValue = 0;
        float[] seq = new float[50];
        int index = 0, i;
                
        System.out.println("Sequence of at least 1 number (0 to end). Max 50 numbers:");
        //Only reading numbers sequence and storing them
        while(index < 1 || inValue != 0) {
            if(inValue != 0) {
                seq[index] = inValue;
                index++;
            }
            inValue = keyboard.nextFloat();
        }
        keyboard.close();
        //Output
        System.out.printf("%nSum - ∑: %.4f%nProd - ∏: %.4f%nArray: [",sum(seq, index), prod(seq, index));
        for(i = 0; i < (index-1); i++) {
            System.out.printf("%.2f, ", seq[i]);
        }
        System.out.printf("%.2f]%n", seq[i]);
    }
}
