import java.util.Comparator;    // Interface for comparation/ordering
import java.io.Serializable;    // Interface to stream to/from files and more

/**
 * Abstract class representing an Employee.
 * This class provides a common structure for various types of employees.
 * Just a translation/copy of the portuguese version started by ANR and completed in class.
 *
 * @author anr
 * @author PIRATA
 * @version 2012.05.30
 */
public abstract class Employee implements Insurable, Serializable, Comparable<Employee>
{
    // Class variables
    private static double dailySalary = 50.00;  // Fixed daily salary
    
    // Getter & Setter
    /**
     * Gets the fixed daily salary for all employees.
     *
     * @return  The fixed daily salary.
     */
    public static double getDailySalary() { return Employee.dailySalary; }
    
    /**
     * Sets the fixed daily salary for all employees.
     *
     * @param sal   The new daily salary to set.
     */
    public static void setDailySalary(double sal) { Employee.dailySalary = sal; }

    // Instance variables
    private String code;    // Code of the employee
    private String name;    // Name of the employee
    private int days;       // Efective work days in the month
    
    // Constructors
    /**
     * Default constructor for the Employee class.
     * Initializes the employee's code and name as '-' and workdays at 0.
     */
    public Employee()
    {
        this.code = "-";
        this.name = "-";
        this.days = 0;
    }
    
    /**
     * Parameterized constructor for the Employee class.
     * Initializes the Employee with the specified attributes.
     * 
     * @param code  The code of the employee.
     * @param name  The name of the employee.
     * @param days  The effective work days in the month.
     */
    public Employee(String code, String name, int days)
    {
        this.setCode(code);
        this.setName(name);
        this.setDays(days);
    }

    /**
     * Copy constructor for the Employee class.
     * Creates a new Employee with the same attributes as the given Employee.
     * 
     * @param emp   The Employee to copy the data from.
     */
    public Employee(Employee emp)
    {
        this.code = emp.getCode();
        this.name = emp.getName();
        this.days = emp.getDays();
    }
    
    // Getters & Setters
    /**
     * Gets the code of the employee.
     *
     * @return  The code of the employee.
     */
    public String getCode() { return this.code; }
    
    /**
     * Gets the name of the employee.
     *
     * @return  The name of the employee.
     */
    public String getName() { return this.name; }
    
    /**
     * Gets the effective work days in the month.
     *
     * @return  The effective work days.
     */
    public int getDays() { return this.days; }
    
    /**
     * Sets the code of the employee.
     *
     * @param code  The new code to set.
     */
    public void setCode(String code) { this.code = code; }
    
    /**
     * Sets the name of the employee.
     *
     * @param name  The new name to set.
     */
    public void setName(String name) { this.name = name; }
    
    /**
     * Sets the effective work days in the month.
     *
     * @param days  The new effective work days to set.
     */
    public void setDays(int days) { this.days = days; }
    
    // Instance methods
    /**
     * Calculates and returns the salary of the employee.
     *
     * @return  The salary of the employee.
     */
    public abstract double salary();
    
    // Complementary methods
    /**
     * Checks if this Employee is equal to another object.
     *
     * @param o The object to compare.
     * @return  true if the objects are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        Employee e = (Employee) o;
        return this.code.equals(e.getCode())
            && this.name.equals(e.getName())
            && this.days == e.getDays();
    }
    
    /**
     * Returns a string representation of the employee in the format: code;name;days..
     *
     * @return  A string representation of the employee.
     */
    public String toString()
    {
        return this.code + ";" + this.name + ";" + this.days;
    }
    
    /**
     * Creates a copy of the employee.
     *
     * @return  A new Employee object with the same attributes.
     */
    public abstract Employee clone();   // Abstracts - need be implemented by the concrete subclasses
    
    /**
     * Generates a hash code for the employee based on the code.
     *
     * @return  The hash code of the employee.
     */
    public int hashCode() { return this.code.hashCode(); }
    
    // Implement the Insurable interface
    @Override
    public double discountForHealthInsurance()
    {
        // You can return a fixed percentage or amount or this can vary for different types of employees
        return 0.10;    // 10% health insurance discount as an example
    }
    
    // Comparable - works for comparing/sorting Arrays and Lists without the need for Comparator
    /**
     * Compares this employee with another employee based on their codes.
     *
     * @param emp   The employee to compare.
     * @return      A negative integer, zero, or a positive integer as this employee
     *              is less than, equal to, or greater than the specified employee.
     */
    public int compareTo(Employee emp)
    {
        if(this.code.compareTo(emp.getCode()) < 0)
            return -1;
        if(this.code.compareTo(emp.getCode()) > 0)
            return 1;
        //if(this.code.compareTo(emp.getCode()) == 0)
        //    return 0;
        return 0;
        // Ya'll could just be: return this.code.compareTo(emp.getCode()),
        // but yah gotta make it fancy for class.
    }
    
    // Comparator - could be created in a separated file!
    // public class EmployeeCodeComparator implements Comparator<Employee> { ... } with the override of compare in it
    /**
     * Comparator for comparing employees based on their codes.
     */
    public static Comparator<Employee> EmployeeCodeComparator = new Comparator<Employee>()
    {
        /**
         * Compares two employees based on their codes.
         *
         * @param emp1  The first employee.
         * @param emp2  The second employee.
         * @return      A negative integer, zero, or a positive integer as the first
         *              employee is less than, equal to, or greater than the second employee.
         */
        @Override
        public int compare(Employee emp1, Employee emp2)
        {
            String s1 = emp1.getCode();
            String s2 = emp2.getCode();
            
            return s1.compareTo(s2);
        }
    };
    
    /**
     * Comparator for sorting employees by name in ascending order.
     */
    public static Comparator<Employee> EmployeeNameComparator = new Comparator<Employee>()
    {
        /**
         * Compares two employees based on their names.
         *
         * @param emp1  The first employee.
         * @param emp2  The second employee.
         * @return      A negative integer, zero, or a positive integer as the first
         *              employee is less than, equal to, or greater than the second employee.
         */
        @Override
        public int compare(Employee emp1, Employee emp2)
        {
            String nm1 = emp1.getName();
            String nm2 = emp2.getName();
            return nm1.compareTo(nm2);
        }
    };

    /**
     * Comparator for sorting employees by salary in ascending order.
     */
    public static Comparator<Employee> EmployeeSalaryComparator = new Comparator<Employee>()
    {
        /**
         * Compares two employees based on their salary.
         *
         * @param emp1  The first employee.
         * @param emp2  The second employee.
         * @return      A negative integer, zero, or a positive integer as the first
         *              employee earns less than, equal to, or greater than the second employee.
         */
        @Override
        public int compare(Employee emp1, Employee emp2)
        {
            double sal1 = emp1.salary();
            double sal2 = emp2.salary();
            return Double.compare(sal1, sal2);
        }
    };
}
