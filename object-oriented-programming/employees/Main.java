import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;
import java.util.Iterator;

/**
 * Write a description of class Main here.
 *
 * @author PIRATA
 * @version 2012.05.30
 */
public class Main
{
    public static int codeCounter = 1;
    
    public static void main(String[] args)
    {
        CompanyMap cmap = new CompanyMap();
        cmap.setName("Cool Company Name");
        System.out.println("CompanyMap() + setName(): " + cmap.toString());
        try {
            cmap.addEmployee(new Normal("N8", "Alice", 20));
            cmap.addEmployee(new Normal("N12", "Bob", 19));
            cmap.addEmployee(new Normal("N2", "Charlie", 18));
            cmap.addEmployee(new Normal("N16", "David", 19));
            cmap.addEmployee(new Normal("N5", "Eva", 22));
            cmap.addEmployee(new Normal("N14", "Frank", 19));
            cmap.addEmployee(new Normal("N7", "Grace", 20));
            cmap.addEmployee(new Normal("N3", "Henry", 21));
            cmap.addEmployee(new Normal("N17", "Isabel", 20));
            cmap.addEmployee(new Normal("N9", "Jack", 19));
            cmap.addEmployee(new Normal("N18", "Kate", 18));
            cmap.addEmployee(new Normal("N4", "Liam", 20));
            cmap.addEmployee(new Normal("N19", "Mia", 21));
            cmap.addEmployee(new Normal("N13", "Noah", 19));
            cmap.addEmployee(new Normal("N1", "Olivia", 20));
            cmap.addEmployee(new Normal("N15", "Peter", 18));
            cmap.addEmployee(new Normal("N10", "Quinn", 22));
            cmap.addEmployee(new Normal("N20", "Ryan", 21));
            cmap.addEmployee(new Normal("N6", "Sara", 20));
            cmap.addEmployee(new Normal("N11", "Tom", 18));
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("Adding 20 Basic employees:\n" + cmap.toString());
        System.out.println("getName(): " + cmap.getName());
        System.out.println("getEmployeeList(): " + cmap.getEmployeeMap().toString());
        System.out.print("existsEmployee(N4): " + cmap.existsEmployee("N4"));
        System.out.println("; existsEmployee(N200): " + cmap.existsEmployee("N200"));
        System.out.println("getEmployee(N8): " + cmap.getEmployee("N8").toString());
        
        ArrayList<Employee> toAdd = new ArrayList<Employee>();
        toAdd.add(new Manager("M1", "Joao", 21, 15));
        toAdd.add(new Manager("M2", "Corona", 18, 5));
        System.out.println("toAdd: " + toAdd.toString());
        cmap.addAllEmployee(new TreeSet(toAdd));
        System.out.println("addAllEmployee(toAdd): " + cmap.toString());
        
        ArrayList<Employee> mtAdd = new ArrayList<Employee>();
        mtAdd.add(new Driver("D1", "Hugo", 22, 0));
        mtAdd.add(new Driver("D2", "Diana", 21, 12));
        System.out.println("mtAdd: " + mtAdd.toString());
        cmap.addAllEmployee(new TreeSet(mtAdd));
        System.out.println("addAllEmployee(mtAdd): " + cmap.toString());
        
        System.out.println("totalSalaries(): " + cmap.totalSalaries());
        System.out.println("countManagers(): " + cmap.countManagers());
        System.out.println("countEmployeesType(Normal): " + cmap.countEmployeesType("Normal"));
        System.out.println("countEmployeesType(Manager): " + cmap.countEmployeesType("Manager"));
        System.out.println("equals(clone): " + cmap.equals(cmap.clone()) + "; equals(new()): " + cmap.equals(new CompanyList()));
        
        Iterator<Employee> tempIter = cmap.orderEmployees("code");
        ArrayList<Employee> listTemp = new ArrayList<>();
        while(tempIter.hasNext()) {
            listTemp.add(tempIter.next());
        }
        System.out.println("Order code: " + listTemp.toString());
        
        tempIter = cmap.orderEmployees("name");
        listTemp = new ArrayList<>();
        while(tempIter.hasNext()) {
            listTemp.add(tempIter.next());
        }
        System.out.println("Name code: " + listTemp.toString());
        
        tempIter = cmap.orderEmployees("salary");
        listTemp = new ArrayList<>();
        while(tempIter.hasNext()) {
            listTemp.add(tempIter.next());
        }
        System.out.println("Salary code: " + listTemp.toString());
        
        try {
            System.out.println("Saving to file: testSaveBinary.obj");
            cmap.saveToBinary("testSaveBinary.obj");
            System.out.println("Loading from file: testSaveBinary.obj");
            CompanyMap fromBin = CompanyMap.loadFromBinary("testSaveBinary.obj");
            System.out.println("Equals: " + cmap.equals(fromBin));
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
        
        try {
            System.out.println("Saving to file: testSaveTxt.txt");
            cmap.saveToText("testSaveTxt.txt");
            System.out.println("Loading from file: testSaveTxt.txt");
            CompanyMap fromTxt = CompanyMap.loadFromText("testSaveTxt.txt");
            System.out.println("Equals: " + cmap.equals(fromTxt));
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
