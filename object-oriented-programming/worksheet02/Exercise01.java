import java.util.Arrays;    //Arrays class

/**
 * Declare, initialize and print the elements of an integer array.
 *
 * @author (PIRATA)
 * @version (2012.04.25)
 */
public class Exercise01
{
    /**
     * This method uses only methods from JAVA's standard library.
     *
     * @return      Nothing
     */
    public static void option01()
    {
        int[] list = {12, 2, 45, 66, 7, 23, 99};
        System.out.println("---------- ARRAY ELEMENTS ----------");
        for(int i = 0; i < list.length; i++) {
            System.out.println("Element " + i + "= " + list[i]);
        }
        System.out.println("------------------------------------");
    }
    
    /**
     * This method uses methods from the java.util.Arrays library.
     *
     * @return      Nothing
     */
    public static void option02()
    {
        int[] list = {12, 2, 45, 66, 7, 23, 99};
        System.out.println("---------- ARRAY ELEMENTS ----------");
        System.out.println(Arrays.toString(list));
        System.out.println("------------------------------------");
    }
    
    /**
     * This is the main method.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        System.out.println("Option 01");
        option01();
        System.out.println("\nOption 02");
        option02();
    }
}
