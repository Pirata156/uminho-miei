import java.util.Scanner;   //Input class
import java.util.GregorianCalendar;     //Date/Time class

/**
 * Program that reads the year, month and day of a person's birth and calculates their current age.
 * Prints the date of birth read, today's day and the calculated age.
 *
 * @author (PIRATA)
 * @version (2012.04.25)
 */
public class Exercise15
{
    /**
     * This method checks if a year is valid.
     * Year is valid if positive and not larger than Final year.
     *
     * @param yA    Year that we check its valid
     * @param yF    Year of the final date (or today's date)
     * @return      A boolean confirming if it's a valid year or not
     */
    public static boolean validYear(int yA, int yF)
    {
        boolean res = false;
        if(yA >= 0 && yA <= yF) {
            res = true;
        }
        return res;
    }
    
    /**
     * This method checks if a month is valid.
     * Month is valid if its between 0 and 11 (months in Calendar are indexedfrom 0)
     * Month becomes invalid if above the final month on the final year.
     *
     * @param mA    Month that we are checking its validity
     * @param yA    Year of the month we are checking its validity - already valid
     * @param mF    Month of the final date (or today's date)
     * @param yF    Year of the final date (or today's date)
     * @return      A boolean confirming if it's a valid month or not
     */
    public static boolean validMonth(int mA, int yA, int mF, int yF)
    {
        boolean res = false;
        if((mA >= 0) && (mA < 12)) {
            res = true;
            if((yA == yF) && (mA > mF)) {
                res = false;
            }
        }
        return res;
    }
    
    /**
     * This method checks if a day is valid.
     * Day is valid if its between 1 and 28, 29, 30 or 31 depending on the month.
     * Day becomes invalid if for the same year and month the day is bigger than the final day.
     *
     * @param dA    Day that we are checking its validity
     * @param mA    Month of the day we are checking its validity - already valid
     * @param yA    Year of the day we are checking its validity - already valid
     * @param dF    Day of the final date (or today's date)
     * @param mF    Month of the final date (or today's date)
     * @param yF    Year of the final date (or today's date)
     * @return      A boolean confirming if it's a valid day or not
     */
    public static boolean validDay(int dA, int mA, int yA, int dF, int mF, int yF)
    {
        boolean res = false;
        if(dA > 0) {
            //Usual months days
            if((mA == 0) || (mA == 2) || (mA == 4) || (mA == 6) || (mA == 7) || (mA == 9) || (mA == 11)) {
                if(dA <= 31) {
                    res = true;
                }
            } else if((mA == 3) || (mA == 5) || (mA == 8) || (mA == 10)) {
                if(dA <= 30) {
                    res = true;
                }
            } else {
                // if (mA == 1)
                if((yA - 1584) % 4 == 0) {
                    //Leap year - first leap recorded in 1582
                    if (dA <= 29) {
                        res = true;
                    }
                } else {
                    if(dA <= 28) {
                        res = true;
                    }
                }
            }
            //In case same year and same month day can't be higher than Final day
            if(yA == yF && mA == mF && dA > dF) {
                res = false;
            }
        }
        return res;
    }
    
    /**
     * This method calculates the age, in years, between two dates.
     * Both dates are assumed as valid and dA came before dB.
     *
     * @param dA    Birth date
     * @param dB    Final date (or today's date)
     * @return      Age in years between the two dates
     */
    public static int getAge(GregorianCalendar dA, GregorianCalendar dB)
    {
        //Another option could have been to get the difference in milliseconds and calculate the years it represents.
        int diff = dB.get(GregorianCalendar.YEAR) - dA.get(GregorianCalendar.YEAR);
        
        if(dA.get(GregorianCalendar.MONTH) > dB.get(GregorianCalendar.MONTH) ||
            (dA.get(GregorianCalendar.MONTH) == dB.get(GregorianCalendar.MONTH) && dA.get(GregorianCalendar.DAY_OF_MONTH) > dB.get(GregorianCalendar.DAY_OF_MONTH))) {
            diff--;
        }
        return diff;
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
        GregorianCalendar birthDay;
        GregorianCalendar today = new GregorianCalendar();
        int bYear,bMon,bDay, inValue = 0, age;
        
        System.out.println("Input the birth date");
        do {
            System.out.print("Year: ");
            inValue = keyboard.nextInt();
        } while(!validYear(inValue, today.get(GregorianCalendar.YEAR)));
        bYear = inValue;
        
        do {
            System.out.print("Month: ");
            inValue = keyboard.nextInt();
        } while(!validMonth(inValue - 1, bYear, today.get(GregorianCalendar.MONTH), today.get(GregorianCalendar.YEAR)));
        bMon = inValue - 1;
        
        do {
            System.out.print("Day: ");
            inValue = keyboard.nextInt();
        } while(!validDay(inValue, bMon, bYear, today.get(GregorianCalendar.DAY_OF_MONTH), today.get(GregorianCalendar.MONTH), today.get(GregorianCalendar.YEAR)));
        bDay = inValue;
        keyboard.close();
        
        birthDay = new GregorianCalendar(bYear, bMon, bDay, 00, 00, 00);

        age = getAge(birthDay, today);
        //System.out.printf("Birth date: %tD%nToday's date: %tD%n", birthDay, today); //%tD - prints Date - MM/DD/YY
        System.out.printf("Birth date: %1$td/%1$tm/%1$tY%nToday's date: %2$td/%2$tm/%2$tY%n", birthDay, today); //Prints Date - DD/MM/YYYY
        System.out.printf("Age: %d%n", age);
    }
}
