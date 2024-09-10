import java.util.Set;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the employees in a company.
 * This class provides methods to manage and analyze the employee data.
 * It manages the Employee objects in a TreeMap.
 * It also supports saving the company instance to a text file and an object file.
 * 
 * @author PIRATA
 * @version 2012.05.30
 */
public class CompanyMap implements Serializable
{
    // Instance variables
    private String companyName;                     // The name of the company
    private TreeMap<String,Employee> employeeMap;   // The company's employees TreeMap
    // Controle variable
    private static HashMap<String,Comparator<Employee>> comparatorMap = new HashMap<>();    // Comparators map
    // Constructors
    /**
     * Default constructor for CompanyMap.
     * Initializes the company name as "-UNSET-" and creates an empty TreeMap for employees.
     * Fills the HashMap for comparators with default values.
     */
    public CompanyMap()
    {
        this.companyName = "-UNSET-";
        this.employeeMap = new TreeMap<>();
        // Optional. It can be placed in the main code.
        CompanyMap.addEmployeeComparator("code", Employee.EmployeeCodeComparator);
        CompanyMap.addEmployeeComparator("name", Employee.EmployeeNameComparator);
        CompanyMap.addEmployeeComparator("salary", Employee.EmployeeSalaryComparator);
    }

    /**
     * Parameterized constructor for CompanyMap.
     *
     * @param name  The name of the company.
     * @param emps  The TreeMap of employees to initialize the company with.
     * @param comps The HashMap of the Employee Comparators to initialize the company with.
     */
    public CompanyMap(String name, TreeMap<String, Employee> emps, HashMap<String,Comparator<Employee>> comps)
    {
        this.setName(name);
        this.setEmployeeMap(emps);
        CompanyMap.setComparatorMap(comps);
    }

    /**
     * Copy constructor for CompanyMap.
     *
     * @param cm    The CompanyMap object to copy.
     */
    public CompanyMap(CompanyMap cm)
    {
        this.companyName = cm.getName();
        this.employeeMap = cm.getEmployeeMap();
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
     * Gets a copy of the TreeMap of employees.
     *
     * @return  A new TreeMap containing a copy of the current employee TreeMap.
     */
    public TreeMap<String,Employee> getEmployeeMap()
    {
        TreeMap<String,Employee> res = new TreeMap<>();
        for(String code : this.employeeMap.keySet()) {
            res.put(code, this.employeeMap.get(code).clone());
        }
        return res;
    }

    /**
     * Sets the TreeMap of employees.
     *
     * @param emps  The TreeMap of employees to set.
     */
    private void setEmployeeMap(TreeMap<String,Employee> emps)
    {
        this.employeeMap = new TreeMap<>();
        for(String code : emps.keySet()) {
            this.employeeMap.put(code, emps.get(code).clone());
        }
    }
    
    /**
     * Gets a copy of the HashMap of Comparators.
     * 
     * @return  A new HashMap cointaining the Comparators stored in the class.
     */
    private static HashMap<String,Comparator<Employee>> getComparatorMap()
    {
        HashMap<String,Comparator<Employee>> res = new HashMap<>();
        for(String alg : CompanyMap.comparatorMap.keySet()) {
            res.put(alg, CompanyMap.comparatorMap.get(alg));
        }
        return res;
    }
    
    /**
     * Sets the HashMap of Comparators onto the class.
     * 
     * @param comps The HashMap of Comparators to set.
     */
    private static void setComparatorMap(HashMap<String,Comparator<Employee>> comps)
    {
        CompanyMap.comparatorMap = new HashMap<>();
        for(String alg : comps.keySet()) {
            CompanyMap.addEmployeeComparator(alg,comps.get(alg));
        }
    }
    
    // Class methods
    /**
     * Add a custom comparator for ordering employees.
     *
     * @param algName   The name of the algorithm.
     * @param comp      The comparator for ordering employees.
     */
    public static void addEmployeeComparator(String algName, Comparator<Employee> comp)
    {
        CompanyMap.comparatorMap.put(algName,comp);        
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
        return this.employeeMap.containsKey(code);
    }

    /**
     * Returns an employee's file given their code.
     *
     * @param code  The code of the employee to retrieve.
     * @return      The Employee object with the given code, or null if not found.
     */
    public Employee getEmployee(String code)
    {
        // same as if(get(code) != null) return get(code) else return null
        return this.employeeMap.get(code) != null ? this.employeeMap.get(code).clone() : null;
    }

    /**
     * Inserts a new employee into the company TreeMap.
     *
     * @param emp   The employee to insert.
     * @throws EmployeeAddException If the employee code already exists in the company.
     */
    public void addEmployee(Employee emp) throws EmployeeAddException
    {
        if(!this.existsEmployee(emp.getCode())) {
            this.employeeMap.put(emp.getCode(), emp.clone());
        } else {
            throw new EmployeeAddException("Employee code " + emp.getCode() + " already exists!");
        }
    }

    /**
     * Inserts all employees from a given set into the company TreeMap.
     * Handles EmployeeAddException by logging errors.
     *
     * @param empSet    The set of employees to insert.
     */
    public void addAllEmployee(Set<Employee> empSet)
    {
        for(Employee e : empSet) {
            // We can avoid add exceptions with an if(exists) here!
            try {
                this.addEmployee(e);
            } catch(EmployeeAddException ex) {
                // In this case we just need to log the error.
                ex.logError();
            }
        }
    }

    /**
     * Calculates the total salaries payable by the company.
     *
     * @return  The total amount of salaries payable by the company.
     */
    public double totalSalaries()
    {
        double sumRes = 0.0;
        for(Employee e : this.employeeMap.values()) {
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
        for(Employee e : this.employeeMap.values()) {
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
        for(Employee e : this.employeeMap.values()) {
            if(e.getClass().getSimpleName().equals(type)) {
                res++;
            }
        }
        return res;
    }
    
    /**
     * Orders employees based on the specified algorithm or by the default comparator (Employee.EmployeeCodeComparator)
     * if the specified algorithm is not found.
     *
     * @param algorithmName The name of the algorithm to use.
     * @return              An Iterator that orders employees according to the specified algorithm.
     */
    public Iterator<Employee> orderEmployees(String algorithmName)
    {
        // Update: In java 8 we can now change the default to Comparator.comparing(Employee::getCode));
        Comparator<Employee> comp = CompanyMap.comparatorMap.getOrDefault(algorithmName, Employee.EmployeeCodeComparator);
        ArrayList<Employee> orderedEmployees = new ArrayList<>(this.employeeMap.values());
        orderedEmployees.sort(comp);
        return orderedEmployees.iterator();
    }
    
    /**
     * Saves the current instance of the CompanyMap class to an Object binary file using object serialization.
     *
     * @param filename      The name of the binary file to which the company instance will be saved.
     * @throws IOException  If an I/O error occurs while writing to the file.
     * @throws Exception    If an exception occurs during object serialization.
     */
    public void saveToBinary(String filename) throws IOException, Exception
    {
        // To Object file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(this);
        oos.flush();    // Flushes any buffered output to the file
        oos.close();    // Closes the ObjectOutputStream, releasing any system resources associated
    }
    
    /**
     * Loads a company instance from an object file.
     *
     * @param filename  The name of the object file to load from.
     * @return          The loaded CompanyMap instance.
     * @throws IOException              If an I/O error occurs while reading from the file.
     * @throws ClassNotFoundException   If the class of the serialized object cannot be found.
     * @throws Exception                If an exception occurs during object serialization.
     */
    public static CompanyMap loadFromBinary(String filename) throws IOException, ClassNotFoundException, Exception
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        CompanyMap res = (CompanyMap) ois.readObject();
        ois.close();
        return res;
    }
    
    /**
     * Saves the company instance to a text file.
     *
     * @param filename      The name of the text file to save to.
     * @throws IOException  If an I/O error occurs while writing to the file.
     */
    public void saveToText(String fileName) throws IOException, Exception
    {
        PrintWriter pw = new PrintWriter(fileName);
        // Could also be: PrintWriter writer = new PrintWriter(new FileWriter(fileName));
        pw.println(this.getName());
        pw.println(this.employeeMap.size());
        for(Employee emp : this.employeeMap.values()) {
            pw.println(emp.toString());
        }
        pw.flush();
        pw.close();
    }
    
    /**
     * Parses a string representation of an employee and creates an instance of the corresponding Employee subclass.
     *
     * The method uses regular expressions to match different patterns of employee information in the input line.
     * It supports parsing lines representing Basic, Manager, and Driver types of employees and creates the appropriate objects using the extracted information.
     *
     * @param line  The string representation of an employee in the format specified for Basic, Manager, or Driver types.
     * @return      An instance of the corresponding Employee subclass based on the parsed information.
     * @throws IllegalArgumentException If the input line doesn't match any expected pattern.
     */
    private static Employee parseEmployee(String line)
    {
        // Match the line against each regex
        Matcher basicMatcher = Pattern.compile("Basic\\{([^;]+);([^;]+);(\\d+)\\}").matcher(line);
        Matcher managerMatcher = Pattern.compile("Manager\\{([^;]+);([^;]+);(\\d+);(\\d+);(\\d+\\.\\d+)\\}").matcher(line);
        Matcher driverMatcher = Pattern.compile("Driver\\{([^;]+);([^;]+);(\\d+);(\\d+\\.\\d+);(\\d+\\.\\d+)\\}").matcher(line);

        // Check which regex matches and create the corresponding employee
        if(basicMatcher.matches()) {
            String code = basicMatcher.group(1);
            String name = basicMatcher.group(2);
            int days = Integer.parseInt(basicMatcher.group(3));
            return new Normal(code, name, days);
        } else if(managerMatcher.matches()) {
            String code = managerMatcher.group(1);
            String name = managerMatcher.group(2);
            int days = Integer.parseInt(managerMatcher.group(3));
            int teamMembers = Integer.parseInt(managerMatcher.group(4));
            double bonusPercentage = Double.parseDouble(managerMatcher.group(5));
            Manager manager = new Manager(code, name, days, teamMembers);
            Manager.setBonus(bonusPercentage);
            return manager;
        } else if(driverMatcher.matches()) {
            String code = driverMatcher.group(1);
            String name = driverMatcher.group(2);
            int days = Integer.parseInt(driverMatcher.group(3));
            double kilometers = Double.parseDouble(driverMatcher.group(4));
            double valuePerKm = Double.parseDouble(driverMatcher.group(5));
            Driver driver = new Driver(code, name, days, kilometers);
            return driver;
        } else {
            // Handle the case where the line doesn't match any expected pattern
            throw new IllegalArgumentException("Invalid input line: " + line);
        }
    }
    
    /**
     * Loads a company instance from a text file.
     *
     * @param filename The name of the text file to load from.
     * @return The loaded CompanyMap instance.
     * @throws IOException            If an I/O error occurs while reading from the file.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    public static CompanyMap loadFromText(String filename) throws IOException, ClassNotFoundException, Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        CompanyMap res = new CompanyMap();  // Since the Comparators are set on the default constructor, that's automatic!
        String line;
        int nEmps, i;
        Employee temp;
        // Pattern for the Employees reading info!
        Pattern basicRegex = Pattern.compile("Basic\\{([^;]+);([^;]+);(\\d+)\\}");
        Pattern managerRegex = Pattern.compile("Manager\\{([^;]+);([^;]+);(\\d+);(\\d+);(\\d+\\.\\d+)\\}");
        Pattern driverRegex = Pattern.compile("Driver\\{([^;]+);([^;]+);(\\d+);(\\d+\\.\\d+);(\\d+\\.\\d+)\\}");
        // First line: Company name
        line = br.readLine();
        res.setName(line);
        // Second line: number of employees to read
        line = br.readLine();
        nEmps = Integer.parseInt(line);
        // Employees
        for(i = 0; i < nEmps; i++) {
            line = br.readLine();
            temp = CompanyMap.parseEmployee(line);
            res.addEmployee(temp);
        }
        return res;
    }

    // Complementary methods
    /**
     * Checks if this CompanyMap object is equal to another object.
     *
     * @param o The object to compare.
     * @return  true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (o == this)
            return true;
        if (o == null || o.getClass() != this.getClass())
            return false;
        CompanyMap cm = (CompanyMap) o;
        return (this.companyName.equals(cm.getName()) && this.employeeMap.equals(cm.getEmployeeMap()));
    }

    /**
     * Returns a string representation of the CompanyMap object.
     *
     * @return  A string representation of the object.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("CompanyMap{");
        sb.append("Name:\"" + this.getName() + "\";");
        sb.append("Employees:" + this.getEmployeeMap().values().toString() + "}");
        return sb.toString();
    }

    /**
     * Creates a copy of the CompanyMap object.
     *
     * @return  A new CompanyMap object with the same attributes.
     */
    @Override
    public CompanyMap clone() { return new CompanyMap(this); }
}
