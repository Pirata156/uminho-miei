import java.util.Scanner;   //Input class

/**
 * Program that reads repeatedly the radius (real number) of a circle and calculates
 * the respective area and perimeter with great precision (5 decimals).
 * Use printf() for the results.
 * The program should only end when it reads a radius = 0.0.
 *
 * @author (PIRATA)
 * @version (2012.03.28)
 */
public class Exercise10
{
    /**
     * This method calculates the perimeter of a circle given a radius.
     *
     * @param r     Radius value of the circle we wanna calculate the perimeter
     * @return      The value of the perimeter of the circle of radius r
     */
    public static double perimeterCircle(double r)
    {
        return 2 * r * Math.PI;
    }
    
    /**
     * This method calculates the surface of a circle given a radius.
     *
     * @param r     Radius value of the circle we wanna calculate the surface
     * @return      The value of the surface of the circle of radius r
     */
    public static double surfaceCircle(double r)
    {
        return Math.pow(r,2) * Math.PI;
    }

    /**
     * This is the main method.
     * In case a negative number is passed, it doesn't stop the program but doesn't calculate.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        double radius = -1;
        double perC, surC;
        
        while(radius != 0) {
            if(radius > 0) {
                perC = perimeterCircle(radius);
                surC = surfaceCircle(radius);
                System.out.printf("Perimeter: %.5f\t\tSurface: %.5f%n",perC, surC);
            }
            System.out.printf("%nCircle radius: ");
            radius = keyboard.nextFloat();
        }
        keyboard.close();
    }
}
