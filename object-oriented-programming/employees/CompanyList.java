import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.Serializable;

/**
 * Represents the employees in a company.
 * This class provides methods to manage and analyze the employee data.
 * It manages the Employee objects in a List.
 * 
 * @author PIRATA
 * @version 2012.05.21
 */
public class CompanyList implements Serializable
{
    // Instance variables
    private String companyName;             // The name of the company
    private List<Employee> employeeList;    // The company's employees list

    // Constructors
    /**
     * Default constructor for the CompanyList class.
     * Initializes the name as with a default value and empty ArrayList of employees. 
     */
    public CompanyList()
    {
        this.companyName = "-UNSET-";
        this.employeeList = new ArrayList<>();
    }
    
    /**
     * Parameterized constructor for CompanyList.
     *
     * @param name  The name of the company.
     * @param emps  The list of employees to initialize the company with.
     */
    public CompanyList(String name, List<Employee> emps)
    {
        this.setName(name);
        this.setEmployeeList(emps);
    }

    /**
     * Copy constructor for CompanyList.
     *
     * @param cl    The CompanyList object to copy.
     */
    public CompanyList(CompanyList cl)
    {
        this.companyName = cl.getName();
        this.employeeList = cl.getEmployeeList();
    }

    // Getters & Setters
    /**
     * Gets the name of the company.
     *
     * @return  The name of the company.
     */
    public String getName() { return this.companyName; }
    
    /**
     * Sets the name of the company.
     *
     * @param name  The new name of the company.
     */
    public void setName(String name) { this.companyName = name; }
    
    /**
     * Gets a copy of the list of employees.
     *
     * @return  A new list containing a copy of the current employee list.
     */
    public List<Employee> getEmployeeList()
    {
        List<Employee> res = new ArrayList<Employee>(this.employeeList.size());
        for(Employee e : this.employeeList) {
            res.add(e.clone());
        }
        return res;
    }
    
    /**
     * Sets the list of employees.
     *
     * @param emps  The list of employees to set.
     */
    private void setEmployeeList(List<Employee> emps)
    {
        this.employeeList = new ArrayList<Employee>(emps.size());
        for(Employee e : emps) {
            this.employeeList.add(e.clone());
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
    {
        boolean flag = false;
        int i;
        for(i = 0; (!flag) && (i < this.employeeList.size()); i++) {
            if(this.employeeList.get(i).getCode().equals(code)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Returns an employee's file given their code.
     *
     * @param code  The code of the employee to retrieve.
     * @return      The Employee object with the given code, or null if not found.
     */
    public Employee getEmployee(String code)
    {
        Employee e = null;
        int i;
        for(i = 0; (e == null) && (i < this.employeeList.size()); i++) {
            if(this.employeeList.get(i).getCode().equals(code)) {
                e = this.employeeList.get(i).clone();
            }
        }
        // Could just put a return or a break inside the for each cycle if it was ok academically! It isn't
        return e;
    }

    /**
     * Inserts a new employee into the company list.
     *
     * @param emp   The employee to insert.
     * @return      true if the employee was added successfully, false otherwise.
     */
    public boolean addEmployee(Employee emp)
    {
        if(!this.existsEmployee(emp.getCode())) {
            return this.employeeList.add(emp.clone());
        }
        return false;
    }

    /**
     * Inserts all new employees from a given set into the company list.
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
        for(Employee e : this.employeeList) {
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
        for(Employee e : this.employeeList) {
            if(e instanceof Manager) {
                // Not all languages have this easy.
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
        for(Employee e : employeeList) {
            if(e.getClass().getSimpleName().equals(type)) {
                res++;
            }
        }
        return res;
    }
    
    // Complementary methods
    /**
     * Checks if this CompanyList object is equal to another object.
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
        CompanyList cl = (CompanyList) o;
        return (this.companyName.equals(cl.getName())
            && this.employeeList.equals(cl.getEmployeeList()));
    }
    
    /**
     * Returns a string representation of the CompanyList object.
     *
     * @return  A string representation of the object.
     */
    public String toString()
    {   // ArrayList<>().toString() applies the toString() to all elements
        StringBuilder sb = new StringBuilder("Company{");
        sb.append("Name:\"" + this.getName() + "\";");
        sb.append("Employees:" + this.getEmployeeList().toString() + "}");
        return sb.toString();
    }
    
    /**
     * Creates a copy of the CompanyList object.
     *
     * @return  A new CompanyList object with the same attributes.
     */
    public CompanyList clone() { return new CompanyList(this); }
}
