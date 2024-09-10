
/**
 * An interface for employees that can be insured.
 *
 * @author PIRATA
 * @version 2012.05.30
 */
public interface Insurable
{
    /**
     * Calculates the health insurance discount for an employee.
     *
     * @return  The health insurance discount for the employee.
     */
    double discountForHealthInsurance();
}
