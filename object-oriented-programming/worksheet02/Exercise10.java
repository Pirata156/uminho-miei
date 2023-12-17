import java.util.Scanner;   //Input class

/**
 * Modified version of Exercise 8 that prints at the end of the program the sum
 * of all incomes and all taxes taken from the employees.
 *
 * @author (PIRATA)
 * @version (2012.05.22)
 */
public class Exercise10
{
    public static final int[] LIMITS = {500, 1000, 2000, 4000, Integer.MAX_VALUE};  //Higher limits for the salaries echelons
    public static final int[] RETENPERCE = {5, 10, 20, 30, 40};  //IRS retenction percentage taxe for the respective echelon
    public static final int NUMEMPL = 20;   //The number of employees

    /**
     * Method that reads the salaries for the indicated number of employees.
     *
     * @param size  Number of employees or salaries to read.
     * @return      Array with the salaries read.
     */
    public static double[] readSalariesArray(int size)
    {   //Assuming values input are positive doubles. Not making error checks
        Scanner keyboard = new Scanner(System.in);  //Input from keyboard
        double[] res = new double[size];
        double inValue;
        int i;
        
        for(i = 0; i < size; i++) {
            System.out.print("Salary employee #" + (i+1) + ": ");
            inValue = keyboard.nextFloat();
            res[i] = inValue;
        }
        keyboard.close();
        return res;
    }
    
    /**
     * Method that checks whats the retention percentage associated to a salary passed as an argument.
     *
     * @param lims      Top limits of each echelon of the IRS tax value.
     * @param retper    IRS retention percentages for the echelon the salary belongs to.
     * @param salary    Salary value to check which tax value is associated with it.
     * @return          Retention percentage associated to the salary received as argument.
     */
    public static int getsTaxPercent(int[] lims, int[] retper, double salary)
    {
        int ec = 0;
        //Find which echelon it belongs too
        while((ec < lims.length) && (salary > lims[ec])) {
            ec++;
        }
        return retper[ec];
    }
    
    /**
     * This is the main method.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        double[] salaries;
        int[] taxes = new int[NUMEMPL];
        double netcalc;
        int i;
        //MODIFIED: New variables
        int sumGross = 0, sumNet = 0, totalTaxes;
        
        salaries = readSalariesArray(NUMEMPL);
        for(i = 0; i < NUMEMPL; i++) {
            taxes[i] = getsTaxPercent(LIMITS, RETENPERCE, salaries[i]);
        }
        //Printing out the values
        System.out.println("  EMPLOYEE\t|\tGROSS\t|\tIRS\t|\tNET");
        for(i = 0; i < NUMEMPL; i++) {
            netcalc = salaries[i] * (1.00 - ((float) taxes[i] / 100));
            System.out.printf("Employee #%d\t| %.2f\t|\t%d%%\t|\t%.2f\n", (i+1), salaries[i], taxes[i], netcalc);
            //MODIFIED: Adding the sum to the already existing cycle
            sumGross += salaries[i];
            sumNet += netcalc;
        }
        //MODIFIED: Printing the totals
        totalTaxes = sumGross - sumNet;
        System.out.println("----- TOTALS -----");
        System.out.println("Total salaries: " + sumGross);
        System.out.println("Total taxes retained: " + totalTaxes);
    }
}
