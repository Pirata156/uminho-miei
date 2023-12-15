import java.util.Scanner;   //Input class

/**
 * Program that accepts N integer temperatures (at least two) and determines the average temperature,
 * the day (2,3,..) on which there's the greatest variation in value compared to the previous day and its value.
 * Results must be presented in the form:
 * "The average of the <N> temperatures was ___ degrees."
 * "The greatest temperature variation was recorded between days ___ and ___ and was of ___ degrees."
 * "The temperature between day ___ and day ___ increased/decreased ___ degrees."
 *
 * @author (PIRATA)
 * @version (2012.03.28)
 */
public class Exercise09
{
    /**
     * This is the main method.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        int n = 0, i, inValue, previous = 0, sumTemps = 0, bigDay = 1, bigTemp, bigVar = 0;
        float average;
        
        do {
            System.out.print("Number temperatures to input (at least 2): ");
            n = keyboard.nextInt();
        } while(n < 2);
        for(i = 1; i <= n; i++) {
            System.out.print("Day " + i + ": ");
            inValue = keyboard.nextInt();
            if(i < 2) {
                previous = inValue; //There's no previous on the first round
            }
            if(Math.abs(bigVar) <= Math.abs(inValue - previous)) {
                bigDay = i;
                bigVar = inValue - previous;
                bigTemp = inValue;
            }
            sumTemps += inValue;    //Adding them for the average at the end
            previous = inValue;     //Today's temp is tomorrows previous
        }
        keyboard.close();
        average = (float)sumTemps / n;  //Casting to float so it isn't an integer division.
        
        System.out.printf("The average of the %d temperatures was %.2f degrees.%n", n, average);
        System.out.println("The greatest temperature variation was recorded between days " + (bigDay - 1) + " and " + bigDay + " and was of " + Math.abs(bigVar) + " degrees.");
        System.out.print("The temperature between day " + (bigDay - 1) + " and day " + bigDay);
        if(bigVar < 0) {
            System.out.println(" decreased " + Math.abs(bigVar) + " degrees.");
        } else {
            System.out.println(" increased " + Math.abs(bigVar) + " degrees.");
        }
    }
}
