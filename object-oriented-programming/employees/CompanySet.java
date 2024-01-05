import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.NavigableSet;

/**
 * Represents the employees in a company.
 * This class provides methods to manage and analyze the employee data.
 * It manages the Employee objects in a TreeSet.
 * 
 * @author PIRATA
 * @version 2012.05.21
 */
public class CompanySet
{
    // Instance variables
    private String companyName;                  // The name of the company
    private TreeSet<Employee> employeeSet;       // The company's employees TreeSet

    // Constructors
    /**
     * Default constructor for CompanySet.
     */
    public CompanySet()
    {
        this.companyName = "-UNSET-";
        this.employeeSet = new TreeSet<Employee>(Employee.EmployeeCodeComparator);
    }

    /**
     * Parameterized constructor for CompanySet.
     *
     * @param name  The name of the company.
     * @param emps  The TreeSet of employees to initialize the company with.
     */
    public CompanySet(String name, TreeSet<Employee> emps)
    {
        this.setName(name);
        this.setEmployeeSet(emps);
    }

    /**
     * Copy constructor for CompanySet.
     *
     * @param cs    The CompanySet object to copy.
     */
    public CompanySet(CompanySet cs)
    {
        this.companyName = cs.getName();
        this.employeeSet = cs.getEmployeeSet();
    }

    // Getters & Setters
    /**
     * Gets the name of the company.
     *
     * @return  The name of the company.
     */
    public String getName()
    {
        return this.companyName;
    }

    /**
     * Sets the name of the company.
     *
     * @param name  The new name of the company.
     */
    public void setName(String name)
    {
        this.companyName = name;
    }

    /**
     * Gets a copy of the TreeSet of employees.
     *
     * @return  A new TreeSet containing a copy of the current employee TreeSet.
     */
    public TreeSet<Employee> getEmployeeSet()
    {
        TreeSet<Employee> res = new TreeSet<>(this.employeeSet.comparator());
        for(Employee e : this.employeeSet) {
            res.add(e.clone());
        }
        return res;
    }

    /**
     * Sets the TreeSet of employees.
     *
     * @param emps  The TreeSet of employees to set.
     */
    private void setEmployeeSet(TreeSet<Employee> emps)
    {   // Assuming emps is not null
        this.employeeSet = new TreeSet<>(emps.comparator());
        for(Employee e : emps) {
            this.employeeSet.add(e.clone());
        }
    }

    // Instance methods
    /**
     * Checks if an employee with the given code exists in the company.
     *
     * @param code  The code of the employee to check.
     * @return      true if an employee with the given code exists, false otherwise.
     */
    public boolean existsEmployee(String code)
    {   // Only if using code comparation/ordering
        Employee fake = new Normal(code,"-",0);
        return this.employeeSet.contains(fake);
        // According to the API, it uses the compareTo or compare to search
    }

    /**
     * Returns an employee's file given their code.
     *
     * @param code  The code of the employee to retrieve.
     * @return      The Employee object with the given code, or null if not found.
     */
    public Employee getEmployee(String code)
    {   // Only if using code comparation/ordering
        Employee fake = new Normal(code,"-",0);
        NavigableSet<Employee> temp = this.employeeSet.subSet(fake,true,fake,true);
        if(temp.isEmpty()) {
            return null;
        } else {
            return temp.first();
        }
        // Comparator/Comparable changes the search engines.
    }

    /**
     * Inserts a new employee into the company TreeSet.
     *
     * @param emp   The employee to insert.
     * @return      true if the employee was added successfully, false otherwise.
     */
    public boolean addEmployee(Employee emp)
    {
        return this.employeeSet.add(emp.clone());
    }

    /**
     * Inserts all employees from a given set into the company TreeSet.
     *
     * @param empSet    The set of employees to insert.
     */
    public void addAllEmployee(Set<Employee> empSet)
    {
        for(Employee e : empSet) {
            this.addEmployee(e);
        }
    }

    /**
     * Calculates the total salaries payable by the company.
     *
     * @return  The total amount of salaries payable.
     */
    public double totalSalaries()
    {
        double sumRes = 0.0;
        for(Employee e : this.employeeSet) {
            sumRes += e.salary();
        }
        return sumRes;
    }

    /**
     * Calculates the total number of company managers.
     *
     * @return  The total number of managers in the company.
     */
    public int countManagers()
    {
        int counter = 0;
        for(Employee e : this.employeeSet) {
            if(e instanceof Manager) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Calculates the total number of employees of the given type.
     *
     * @param type  The type of employee to count (e.g., "Normal", "Manager").
     * @return      The total number of employees of the specified type.
     */
    public int countEmployeesType(String type)
    {
        int res = 0;
        for(Employee e : this.employeeSet) {
            if(e.getClass().getSimpleName().equals(type)) {
                res++;
            }
        }
        return res;
    }
    
    /**
     * Returns a list of employees ordered by name.
     *
     * @return  A list of employees ordered by name.
     */
    public List<Employee> getEmployeesOrderedByName()
    {   // Since names might be repetitive
        ArrayList<Employee> res = new ArrayList<>();
        res.addAll(this.getEmployeeSet());
        res.sort(Employee.EmployeeNameComparator);
        return new ArrayList<>(res);
    }

    /**
     * Returns a list of employees ordered by code.
     *
     * @return  A list of employees ordered by code.
     */
    public List<Employee> getEmployeesOrderedByCode()
    {   // There are no repeated codes. Set is ok to use.
        TreeSet<Employee> sorted = new TreeSet<>(Employee.EmployeeCodeComparator);
        sorted.addAll(this.getEmployeeSet());
        return new ArrayList<>(sorted);
    }

    /**
     * Returns a list of employees ordered by salary.
     *
     * @return  A list of employees ordered by salary.
     */
    public List<Employee> getEmployeesOrderedBySalary()
    {   // Same as with names, might be repetitive salaries
        ArrayList<Employee> res = new ArrayList<>();
        res.addAll(this.getEmployeeSet());
        res.sort(Employee.EmployeeSalaryComparator);
        return new ArrayList<>(res);
    }

    // Complementary methods
    /**
     * Checks if this CompanySet object is equal to another object.
     *
     * @param o The object to compare.
     * @return  true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        CompanySet cs = (CompanySet) o;
        return (this.companyName.equals(cs.getName())
            && this.employeeSet.equals(cs.getEmployeeSet()));
    }

    /**
     * Returns a string representation of the CompanySet object.
     *
     * @return  A string representation of the object.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("CompanySet{");
        sb.append("Name:\"" + this.getName() + "\";");
        sb.append("Employees:" + this.getEmployeeSet().toString() + "}");
        return sb.toString();
    }

    /**
     * Creates a copy of the CompanySet object.
     *
     * @return  A new CompanySet object with the same attributes.
     */
    public CompanySet clone() { return new CompanySet(this); }

    // Comparator implementations pushed onto the Employee class
}