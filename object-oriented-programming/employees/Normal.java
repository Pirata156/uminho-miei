
/**
 * Represents a normal employee.
 * This class extends the abstract class Employee and provides specific implementations for a normal employee.
 *
 * @author PIRATA
 * @version 2012.05.21
 */
public class Normal extends Employee
{
    // Instance variables
    
    // Constructors
    /**
     * Default constructor for the Normal class.
     * Initializes it with the Employee default values.
     */
    public Normal()
    {
        super();
    }
    
    /**
     * Parameterized constructor for the Normal class.
     * Initializes the Normal employee with the specified attributes.
     * 
     * @param code  The code of the normal employee.
     * @param name  The name of the normal employee.
     * @param days  The effective work days of the normal employee.
     */
    public Normal(String code, String name, int days)
    {
        super(code, name, days);
    }
    
    /**
     * Copy constructor for the Normal class.
     * Creates a new Normal employee with the same attributes as the given Normal employee.
     * 
     * @param emp   The Normal employee to copy.
     */
    public Normal(Normal emp)
    {
        super(emp);
    }
    
    // Getters & Setters
    
    // Instance methods
    /**
     * Calculates and returns the salary of the normal employee.
     * The salary is calculated by multiplying the effective work days with the daily salary of all employees.
     *
     * @return  The salary of the normal employee.
     */
    public double salary()
    {
        return this.getDays() * Employee.getDailySalary();
    }
    
    // Complementary methods
    /**
     * Checks if this Normal employee is equal to another object based on all its attributes.
     *
     * @param o The object to compare.
     * @return  true if the objects are equal, false otherwise.
     */
    public boolean equals(Object o) { return super.equals(o); }   // Not really necessary
    
    /**
     * Returns a string representation of the Normal employee in the format: "Basic;code;name;days".
     *
     * @return  A string representation of the Normal employee.
     */
    public String toString() { return "Basic{" + super.toString() + "}"; }
    
    /**
     * Creates a copy of the Normal employee.
     *
     * @return  A new Normal employee object with the same attributes.
     */
    public Normal clone() { return new Normal(this); }
}