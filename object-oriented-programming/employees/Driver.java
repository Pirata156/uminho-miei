
/**
 * The Driver class represents a driver employee, extending the Employee class.
 * It includes information about the distance driven and methods to calculate salary.
 *
 * @author PIRATA
 * @version 2012.05.21
 */
public class Driver extends Employee
{
    // Class variables
    private static double valuePerKm = 5;
    
    // Getter & Setter
    /**
     * Gets the value per kilometer.
     * 
     * @return  The value per kilometer.
     */
    private static double getValuePerKm() { return Driver.valuePerKm; }
    
    /**
     * Sets the value per kilometer.
     * 
     * @param val   The new value per kilometer.
     */
    private static void setValuePerKm(double val) { Driver.valuePerKm = val; }
    
    // Instance variables
    private double monthsKms;
    
    // Constructors
    /**
     * Default constructor for the Driver class.
     * Initializes the instance variables.
     */
    public Driver()
    {
        super();
        this.monthsKms = 0;
    }
    
    /**
     * Copy constructor for the Driver class.
     * 
     * @param d The Driver object to copy.
     */
    public Driver(Driver d)
    {
        super(d);
        this.monthsKms = d.getKms();
    }
    
    /**
     * Parameterized constructor for the Driver class.
     * 
     * @param code  The code of the driver.
     * @param name  The name of the driver.
     * @param days  The number of days worked.
     * @param kms   The number of kilometers driven.
     */
    public Driver(String code, String name, int days, double kms)
    {
        super(code,name,days);
        this.setKms(kms);
    }
    
    // Getters & Setters
    /**
     * Gets the number of kilometers driven.
     * 
     * @return  The number of kilometers driven.
     */
    public double getKms() { return this.monthsKms; }
    
    /**
     * Sets the number of kilometers driven.
     * 
     * @param kms   The number of kilometers to set.
     */
    public void setKms(double kms) { this.monthsKms = kms > 0.0 ? kms : 0.0; }
    
    // Instance methods
    /**
     * Calculates the salary of the driver.
     * 
     * @return  The calculated salary.
     */
    public double salary()
    {
        double res = super.getDays() * Employee.getDailySalary();
        res += this.getKms() * Driver.valuePerKm;
        return res;
    }
    
    // Complementary methods
    /**
     * Checks if two Driver objects are equal.
     * 
     * @param o The object to compare.
     * @return  true if the objects are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        Driver d = (Driver) o;
        return (super.equals(o)
            && this.monthsKms == d.getKms());
    }
    
    /**
     * Returns a string representation of the Driver object.
     * 
     * @return  A string representation of the Driver object.
     */
    public String toString()
    {
        return "Driver{" + super.toString() + ";" + this.monthsKms + ";" + Driver.valuePerKm + "}";
    }
    
    /**
     * Creates and returns a copy of the Driver object.
     * 
     * @return  A copy of the Driver object.
     */
    public Driver clone() { return new Driver(this); }
}