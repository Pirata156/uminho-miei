import java.util.GregorianCalendar;
import java.util.TreeSet;

/**
 * Write a description of class ToSecretServices here.
 *
 * @author PIRATA
 * @version v_002
 */
public class ToSecretServices extends CommercialProduct
{
    // Class variables
    private static double discretionTax = 1.8;

    public static double getTax() { return ToSecretServices.discretionTax; }

    public static void setTax(double t) { ToSecretServices.discretionTax = t; }
    // Instance variables
    
    // Constructors
    public ToSecretServices()
    {
        super();
        // Specific to ToDistributionProfessionals
        this.setCargoType(CargoType.TOXIC);
        this.setMaxCargos(1);
    }

    public ToSecretServices(String id, GregorianCalendar dt, String dest, Cargo carg)
    {
        super(id, dt, dest, null, 1, new TreeSet<Cargo>(), CargoType.TOXIC, 0.0);
        try {
            this.addCargo(carg);
        } catch(CargoLimiteExceededException e) { }
    }

    public ToSecretServices(ToSecretServices tdp)
    {
        super(tdp);
    }

    // Getters & Setters

    // Instance methods

    // Complementary methods
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("ToSecretServices");
        sb.append(super.toString());
        return sb.toString();
    }

    @Override
    public ToSecretServices clone() { return new ToSecretServices(this); }
}
