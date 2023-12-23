import java.util.GregorianCalendar; // DateTime class

/**
 * The TermAccount class represents a term bank account with various attributes and operations.
 * It allows you to calculate interest, change account information, and close the account.
 *
 * @author (PIRATA)
 * @version (2012.06.04)
 */
public class TermAccount
{
    // AUTHOR NOTE: Arrr! Lost me way in this class description, mate! Apologies if me code be a bit off-course – might I navigated a different sea of instructions!
    // Instance variables
    private String code;                    // The code for the account.
    private String holder;                  // The account holders name.
    private GregorianCalendar startDate;    // The starting date of interest counting.
    private double capital;                 // The deposited capital currenty.
    private int period;                     // The period for calculation interest.
    private GregorianCalendar interestDate; // The date to calculate the interest.
    private double interestRate;            // The interest rate.

    /**
     * Default constructor for objects of class TermAccount.
     * Initializes the code as "UNSET", name as "UNDEFINED", starting date as today's at midnight,
     * interest date as 'null' and capital, period and rate as 0.
     */
    public TermAccount()
    {
        this.code = "UNSET";
        this.holder = "UNDEFINED";
        GregorianCalendar rNow = new GregorianCalendar();
        this.startDate = new GregorianCalendar(rNow.get(GregorianCalendar.YEAR), rNow.get(GregorianCalendar.MONTH), rNow.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
        this.capital = 0;
        this.period = 0;
        this.interestDate = null;
        this.interestRate = 0;
    }
    
    /**
     * Constructs a new TermAccount by copying the attributes of the given TermAccount.
     * 
     * @param t The TermAccount to copy the data from.
     */
    public TermAccount(TermAccount t)
    {
        this.code = t.getCode();
        this.holder = t.getHolder();
        this.startDate = t.getStartDate();
        this.capital = t.getCapital();
        this.period = t.getInterestPeriod();
        this.interestDate = t.getInterestDate();
        this.interestRate = t.getInterestRate();
    }
    
    /**
     * Constructor for creating a TermAccount with all attributes.
     *
     * @param c     The account code.
     * @param h     The account holder's name.
     * @param sd    The start date of interest counting.
     * @param cp    The initial capital deposited.
     * @param pd    The period for calculating interest.
     * @param id    The date for calculating and updating interest and capital.
     * @param ir    The interest rate.
     */
    public TermAccount(String c, String h, GregorianCalendar sd, double cp, int pd, GregorianCalendar id, double ir)
    {
        this.setCode(c);
        this.setHolder(h);
        this.setStartDate(sd.get(GregorianCalendar.YEAR), sd.get(GregorianCalendar.MONTH), sd.get(GregorianCalendar.DAY_OF_MONTH));
        this.setCapital(cp);
        this.setInterestPeriod(pd);
        this.setInterestDate(id.get(GregorianCalendar.YEAR), id.get(GregorianCalendar.MONTH), id.get(GregorianCalendar.DAY_OF_MONTH));
        this.setInterestRate(ir);
    }
    
    // Getters & Setters
    /**
     * Get the account code.
     *
     * @return  The account code.
     */
    public String getCode() { return this.code; }
    
    /**
     * Get the account holder's name.
     *
     * @return  The account holder's name.
     */
    public String getHolder() { return this.holder; }
    
    /**
     * Get the start date of interest counting.
     *
     * @return  The start date of interest counting as a GregorianCalendar.
     */
    public GregorianCalendar getStartDate() { return (GregorianCalendar) this.startDate.clone(); }  // GC clone returns an Object that needs conversion?!
    
    /**
     * Get the initial capital deposited in the account.
     *
     * @return  The initial capital.
     */
    public double getCapital() { return this.capital; }
    
    /**
     * Get the period for calculating interest.
     *
     * @return  The period in days.
     */
    public int getInterestPeriod() { return this.period; }
    
    /**
     * Get the date when to calculate interests.
     * 
     * @return  The date of interest counting as a GregorianCalendar.
     */
    public GregorianCalendar getInterestDate() { return (GregorianCalendar) this.interestDate.clone(); }
    
    /**
     * Get the interest rate for the account.
     *
     * @return  The interest rate as a percentage.
     */
    public double getInterestRate() { return this.interestRate; }
    
    /**
     * Set the account code.
     *
     * @param c The account code.
     */
    public void setCode(String c) { this.code = c; }
    
    /**
     * Set the account holder's name.
     *
     * @param h The account holder's name.
     */
    public void setHolder(String h) { this.holder = h; }
    
    /**
     * Set the start date of interest counting.
     *
     * @param y The year of the start date.
     * @param m The month of the start date.
     * @param d The day of the start date.
     */
    public void setStartDate(int y, int m, int d) { this.startDate = new GregorianCalendar(y,m,d); }
    
    /**
     * Set the initial capital deposited in the account.
     *
     * @param cp    The initial capital.
     */
    public void setCapital(double cp) { this.capital = cp; }
    
    /**
     * Set the period for calculating interest.
     *
     * @param p The period in days.
     */
    public void setInterestPeriod(int p) { this.period = p; }
    
    /**
     * Set the date of interest counting.
     *
     * @param y The year of the date.
     * @param m The month of the date.
     * @param d The day of the date.
     */
    public void setInterestDate(int y, int m, int d) { this.interestDate = new GregorianCalendar(y,m,d); }
    
    /**
     * Set the interest rate for the account.
     *
     * @param ir    The interest rate as a percentage.
     */
    public void setInterestRate(double ir) { this.interestRate = (ir < 0) ? 0 : ir; }

    // Instance methods
    /**
     * Calculate the number of days passed since the account opening.
     *
     * @return  The number of days passed.
     */
    public int daysPassed()
    {
        int res = 0;
        GregorianCalendar today = new GregorianCalendar();
        long diffMillis = today.getTimeInMillis() - this.getStartDate().getTimeInMillis();
        if(diffMillis > 0) {
            // 1 day = 24 hour = 24 * 60 mins = 1440 * 60 secs = 86400 * 1000 millis = 86400000
            res = (int) (diffMillis / 86400000);
        }
        return res;
    }
    
    /**
     * Change the account holder's name.
     *
     * @param newh  The new account holder's name.
     */
    public void changeHolder(String newh) {
        this.setHolder(newh);
    }
    
    /**
     * Change the interest rate for the account.
     *
     * @param newir The new interest rate as a percentage.
     */
    public void changeRate(double newir) {
        this.setInterestRate(newir);
    }
    
    /**
     * Check if today is the day to calculate interest.
     *
     * @return  true if today is the interest calculation day, false otherwise.
     */
    public boolean isInterestDay() {
        GregorianCalendar today = new GregorianCalendar();
        GregorianCalendar date = this.getInterestDate();
        boolean res = false;
        if((date.get(GregorianCalendar.YEAR) == today.get(GregorianCalendar.YEAR)) &&
            (date.get(GregorianCalendar.MONTH) == today.get(GregorianCalendar.MONTH)) &&
            (date.get(GregorianCalendar.DAY_OF_MONTH) <= today.get(GregorianCalendar.DAY_OF_MONTH))) {
            res = true;
        }
        return res;
    }
    
    /**
     * Calculate and add interest to the account capital.
     */
    public void calculateAddInterest()
    {
        double interest = 0;
        GregorianCalendar date = new GregorianCalendar();
        if(this.isInterestDay()) {
            interest = this.getCapital() * (this.getInterestRate() / 100);
            this.setCapital(this.getCapital() + interest);
            date.add(GregorianCalendar.DAY_OF_MONTH, this.getInterestPeriod());
            this.setInterestDate(date.get(GregorianCalendar.YEAR), date.get(GregorianCalendar.MONTH), date.get(GregorianCalendar.DAY_OF_MONTH));
        }
    }
    
    /**
     * Close the account and calculate the total amount payable to the holder (initial capital + interest).
     *
     * @return  The total amount payable to the holder.
     */
    public double closeAccount()
    {   // I'm super confused about this one. Just assumed that the capital doesn't change if its not the time yet!
        this.calculateAddInterest();
        double cap = this.getCapital();
        this.interestDate = new GregorianCalendar();    // Closing time.
        this.setInterestPeriod(0);
        this.setInterestRate(0);
        this.setCapital(0);
        return cap;
    }
    
    // Complementary methods
    /**
     * Check if two TermAccount objects are equal based on their attributes.
     *
     * @param o The object to compare to.
     * @return  true if the accounts are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass())) {
            // if (!(o instanceof TermAccount)) return false; is another option of doing the same comparison of classes!
            return false;
        } else {
            TermAccount tc = (TermAccount) o;
            return((this.getCode().equals(tc.getCode())) && (this.getHolder().equals(tc.getHolder())) &&
                    (this.getStartDate().equals(tc.getStartDate())) && (this.getCapital() == tc.getCapital()) &&
                    (this.getInterestRate() == tc.getInterestRate()) && (this.getInterestPeriod() == tc.getInterestPeriod()));
        }
    }
    
    /**
     * Returns a string representation of the TermAccount in the format "TermAccount { <data> }".
     *
     * @return  A string representation of the TermAccount.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder("TermAccount {");
        sb.append("code:\'" + this.getCode() + "\'");
        sb.append(", Holder:\'" + this.getHolder() + "\'");
        sb.append(", Start:" + this.getStartDate().getTime().toString());
        sb.append(", Capital:" + this.getCapital());
        sb.append(", Period:" + this.getInterestPeriod());
        sb.append(", Date:" + this.getInterestDate().toString());
        sb.append(", Rate:" + this.getInterestRate() + "}");
        return sb.toString();
    }
    
    /**
     * Creates a new TermAccount object that is a deep copy of this TermAccount.
     *
     * @return  A new TermAccount object with the same attributes as the original.
     */
    public TermAccount clone() { return new TermAccount(this); }
    
    /**
     * Returns a hash code value for the TermAccount.
     *
     * @return  A hash code value for the TermAccount.
     */
    public int hashCode() { return this.toString().hashCode(); }
}
