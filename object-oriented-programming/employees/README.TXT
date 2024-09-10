------------------------------------------------------------------------
This is the project README file for Employees Project.
Here, you'll find information about the Object-Oriented Programming (OOP)practical exercises related to managing employees in a company.

PROJECT TITLE: Employees Project (Projecto Empregados)
PURPOSE OF PROJECT: OOP practical exercises for managing company employees.
VERSION or DATE: 2012.05.30
AUTHORS: PIRATA (https://github.com/Pirata156)
JAVA VERSION: Java 7

HOW TO START THIS PROJECT:
1. Open the project in BlueJ.
2. Compile each Java class.
3. Compile Javadoc for documentation.
4. Use BlueJ tools for testing.
5. Run the Main class.

USER INSTRUCTIONS:

**Project Overview:**

The "Employees" project is a collection of Java classes and interfaces that simulate managing employees in a company.
The project covers a range of OOP concepts, including inheritance hierarchy, polymorphism, generic programming, interfaces, exceptions, and file handling.

The main classes and interfaces in the project include:
- Interface Insurable
- Abstract class Employee
- Classes Normal, Manager, and Driver extending Employee
- EmployeeAddException class
- CompanySet, CompanyList, and CompanyMap classes
- Main class for testing the CompanyMap class methods

**Project Features:**

1. **CompanyList Class:**
   - Represents a list of all company employees.
   - Implements functionalities such as checking employee existence, returning an employee's file, inserting new employees, and more.

2. **CompanyMap Class:**
   - Similar to CompanyList but uses TreeMap<String, Employee>.
   - Additional features include checking preconditions before making changes, storing different Employee comparators, ordering employees, and saving/loading company status in text and object files.

3. **CompanySet Class:**
   - Represents a list of employees using TreeSet<Employee>.
   - Supports sorting employees using different comparators and provides functionalities similar to CompanyList.

4. **Employee Hierarchy:**
   - Abstract class Employee with subclasses Normal, Manager, and Driver.
   - Manager class includes a management bonus, and Driver class introduces km-related attributes for salary calculation.

5. **Insurable Interface:**
   - Interface implemented by all employees, allowing the calculation of health insurance discounts.

**How to Test:**
- Open Main class and run the main method to test the functionality of the CompanyMap class.

**Project Evolution:**
The project evolved through several steps:
1. Initial classes and interfaces.
2. Addition of the Driver class and changes to the hierarchy.
3. Enhanced CompanyMap with precondition checks.
4. Incorporation of Employee comparators.
5. Implementation of health insurance discounts.

**Notes:**
Ensure that Javadoc documentation is generated for a detailed explanation of each class, method, and interface.

**Author's Notes:**
Feel free to reach out to the author (PIRATA) on GitHub for any clarifications or assistance.

**Project complete description:**

The “Employees” project will be used to practice OOP subjects about inheritance hierarchy, polymorphism, generic programming, interfaces, exceptions and text and object streams.
The project will use already known notions about collections and in each practical class a very specific set of subjects will be covered.

A company's employees are characterized by the following information: code, name and actual working days in the month. All normal employees have a fixed daily salary. However, managers have a management bonus that multiplies their normal salary (example 1.25).
The related Company class must have the following functionalities:
- Check if an employee with the given code exists;
- Return an employee's file given its code;
- Insert a new employee;
- Return a copy of the list of employees;
- Insert all employees from a given set;
- Calculate the total salaries payable;
- Calculate the total number of company managers;
- Calculate the total number of employees of the given type (provided as a parameter in the form of a String);

1. Create a CompanyList class that represents the list of all the company's employees, and has implemented, in addition to the usual methods, the methods mentioned above.

2. Create a CompanyMap class with the same requirements but using TreeMap<String, Employee>.

3. Create a CompanySet class with the same requirements but using TreeSet<Employee>, and several Comparator<Employee> that allow employees to be sorted according to different orders given. Note: These Comparator<Employee> can be built on Employee class or as different classes.

4. Consider now that a new type of employee has been added, drivers. Drivers have defined a value per km traveled, and have associated the number of km traveled that month to calculate their final salary. Change the class hierarchy to accommodate this new class.

5. Change the CompanyMap class so that it is possible to check whether the preconditions are valid before making changes to the data structure (cf. use of Exception)

6. Change the EmpresaMap class so that it is possible to store the different types of Employee comparators that you want to implement.
- Create a method that returns an Employee iterator and orders according to the desired algorithm:
public Iterator<Employee> ordersEmployees(String algorithmname)

7. Change the CompanyMap class so that the company status can be saved in a file.
Create features in the CompanyMap class that allow:
- Save the company instance in a text file;
- Save the company instance in an object file;

8. Create an Insurable interface, which must be implemented by all employees and which allows responding to the method that calculates the health insurance discount that each Employee must make.
The signature of this method is:
public double discountForHealthInsurance()

------------------------------------------------------------------------