import java.util.Scanner;   //Input class
import java.util.GregorianCalendar; //DateTime class
import static java.lang.String.valueOf;    //String class method that passes a simple type to a String

/**
 * Program that uses an auxiliary method that accepts two dates and determines their difference in days, hours, minutes and seconds.
 * The result of this method should be a String.
 *
 * @author (PIRATA)
 * @version (2012.03.04)
 */
public class Exercise07
{
    /**
     * This method accepts two dates and determines their difference in days, hours, mins, seconds and milliseconds.
     *
     * @param dA    First of the date arguments to determine the difference
     * @param dB    Second of the date arguments to determine the difference
     * @return      String with the difference between the two dates
     */
    public static String dateDiff(GregorianCalendar dA, GregorianCalendar dB)
    {
        long diff, millis, secs, mins, hours, days, aux;
        String res;
        
        diff = Math.abs(dA.getTimeInMillis() - dB.getTimeInMillis());
        millis = diff % 1000;
        aux = diff / 1000;
        secs = aux % 60;
        aux = aux / 60;
        mins = aux % 60;
        aux = aux / 60;
        hours = aux % 24;
        days = aux / 24;
        
        res = valueOf(days) + " days, " + valueOf(hours) + " hours, " + valueOf(mins) + " minutes, " + valueOf(secs) + " seconds and " + valueOf(millis) + " milliseconds";
        return res;
    }
    /**
     * This is the main method.
     * Since inputting dates only down to the minute,
     * the difference at the seconds and milliseconds level will always be 0;
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        String[] inDate = new String[5];    //To keep the split date.
        int year, mon, day, hour, min;

        System.out.println("Write dates in this format: DD-MM-YYYY-HH-MM");
        System.out.print("1st Date: ");
        inDate = keyboard.nextLine().split("-");    //split method splits a str by token
        year = Integer.parseInt(inDate[2]);
        mon = Integer.parseInt(inDate[1]) - 1;
        day = Integer.parseInt(inDate[0]);
        hour = Integer.parseInt(inDate[3]);
        min = Integer.parseInt(inDate[4]);
        GregorianCalendar fstDate = new GregorianCalendar(year, mon, day, hour, min, 0);
        System.out.print("2nd Date: ");
        inDate = keyboard.nextLine().split("-");
        year = Integer.parseInt(inDate[2]);
        mon = Integer.parseInt(inDate[1]) - 1;
        day = Integer.parseInt(inDate[0]);
        hour = Integer.parseInt(inDate[3]);
        min = Integer.parseInt(inDate[4]);
        GregorianCalendar sndDate = new GregorianCalendar(year, mon, day, hour, min, 0);
        keyboard.close();
        
        System.out.println("Difference: " + dateDiff(fstDate, sndDate));
    }
}
