
/**
 * The EmployeeAddException class represents an exception that can occur when
 * attempting to add an employee with an existing code in a company.
 * This exception is a subclass of the standard Java Exception class.
 *
 * @author PIRATA
 * @version 2012.05.28
 */
public class EmployeeAddException extends Exception
{
    /**
     * Constructs a new EmployeeAddException with no detail message.
     */
    public EmployeeAddException()
    {
        super();
    }
    
    /**
     * Constructs a new EmployeeAddException with the specified detail message.
     *
     * @param message   The detail message (saved for later retrieval by the getMessage() method).
     */
    public EmployeeAddException(String message)
    {
        super(message);
    }
    
    public void logError()
    {
        System.err.println("Error: " + this.getMessage());
    }
}
