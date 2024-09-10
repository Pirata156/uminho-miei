import java.util.GregorianCalendar; //DateTime class

/**
 * Program that determines the date and time of the system,
 * performs a cycle with 10 million single increments of a given variable,
 * determines the time after that cycle, and
 * calculates the total ammount of milliseconds that such a cycle took to execute.
 *
 * @author (PIRATA)
 * @version (2012.03.04)
 */
public class Exercise06
{
    /**
     * This is the main method.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        int counter = 0;
        long difMillis;
        
        GregorianCalendar beforeTime = new GregorianCalendar();
        while(counter < 10000000) {
            counter++;
        }
        GregorianCalendar afterTime = new GregorianCalendar();
        /*
         * For date and time formatting: %t or %T + suffix
         * Suffixes:
         * T - time
         * H, M, S - Hour, Minute, Second
         * L, N - represents time in millisecond and nanoseconds
         * p - adds a.m. or p.m.
         * z - Prints out the timezone offset
         * (1,2,..)$ - To avoid multiple repeated arguments - use index reference (%1$tH)
         * A - full week day
         * d - two digit day of month
         * B - full month name
         * m - two digit month
         * Y - year in four digits
         * y - year in two digits
         */
        difMillis = afterTime.getTimeInMillis() - beforeTime.getTimeInMillis();
        System.out.printf("Time start: %tT%n", beforeTime);
        System.out.printf("Time start: %1$tH:%1$tM:%1tS%n", afterTime);
        System.out.println("Cycle took: " + difMillis + " milliseconds");
    }
}
