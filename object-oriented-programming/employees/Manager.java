
/**
 * Represents a manager employee.
 * This class extends the abstract class Employee and provides specific implementations for a manager employee.
 *
 * @author PIRATA
 * @version 2012.05.21
 */
public class Manager extends Employee
{
    // Class variables
    private static double bonus = 1.25;     // Manager's prize multiplier

    // Getter & Setter
    /**
     * Gets the bonus multiplier for managers.
     *
     * @return  The bonus multiplier.
     */
    public static double getBonus() { return Manager.bonus; }
    
    /**
     * Sets the bonus multiplier for managers.
     *
     * @param mult  The new bonus multiplier to set. Must be greater than 0.
     */
    public static void setBonus(double mult) { Manager.bonus = mult > 0 ? mult : 0; }
    
    // Instance variables
    private int teamSize;   // The size of team it manages (random instance variable)
    
    // Constructors
    /**
     * Default constructor for the Manager class.
     * Initializes the Manager with default values from Employee and the size of the team at 0.
     */
    public Manager()
    {
        super();
        this.teamSize = 0;
    }
    
    /**
     * Parameterized constructor for the Manager class.
     * Initializes the Manager with the specified attributes.
     * 
     * @param code  The code of the manager employee.
     * @param name  The name of the manager employee.
     * @param days  The effective work days of the manager employee.
     * @param team  The size of the team the manager manages.
     */
    public Manager(String code, String name, int days, int team)
    {
        super(code, name, days);
        this.setTeamSize(team);
    }
    
    /**
     * Copy constructor for the Manager class.
     * Creates a new Manager with the same attributes as the given Manager.
     * 
     * @param man   The Manager employee to copy.
     */
    public Manager(Manager man)
    {
        super(man);
        this.teamSize = man.getTeamSize();
    }
    
    // Getters & Setters
    /**
     * Gets the size of the team managed by the manager.
     *
     * @return  The size of the team.
     */
    public int getTeamSize() { return this.teamSize; }

    /**
     * Sets the size of the team managed by the manager.
     *
     * @param ts    The new team size to set. Must be greater than 0.
     */
    public void setTeamSize(int ts) { this.teamSize = ts > 0 ? ts : 0; }    // Could also throw an exception, but I don't wanna

    // Instance methods
    /**
     * Calculates and returns the salary of the manager employee.
     * The salary is calculated by multiplying the effective work days with
     * the daily salary of all employees and the bonus multiplier for managers.
     *
     * @return  The salary of the manager employee.
     */
    public double salary()
    {
        return super.getDays() * Employee.getDailySalary() * Manager.getBonus();
    }
    
    // Complementary methods
    /**
     * Checks if this Manager employee is equal to another object.
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
        Manager m = (Manager) o;
        return (super.equals(o)
            && this.teamSize == m.getTeamSize());
    }
    
    /**
     * Returns a string representation of the Manager employee.
     *
     * @return  A string representation in the format: "Manager;code;name;days;teamSize;bonus".
     */
    public String toString()
    {
        return "Manager{" + super.toString() + ";" + this.teamSize + ";" + Manager.bonus + "}";
    }
    
    /**
     * Creates a copy of the Manager employee.
     *
     * @return  A new Manager employee object with the same attributes.
     */
    public Manager clone() { return new Manager(this); }
}