import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Write a description of class ToDistributionProfessionals here.
 *
 * @author PIRATA
 * @version v_002
 */
public class ToDistributionProfessionals extends CommercialProduct
{
    // Class variables
    private static double serviceDiscount = 0.8;

    public static double getDiscount() { return ToDistributionProfessionals.serviceDiscount; }

    public static void setDiscount(double d) { ToDistributionProfessionals.serviceDiscount = d; }
    // Instance variables
    
    // Constructors
    public ToDistributionProfessionals()
    {
        super();
        // Specific to ToDistributionProfessionals
        this.setCargoType(CargoType.PERISHABLE);
        this.setMaxCargos(10);
    }

    public ToDistributionProfessionals(String id, GregorianCalendar dt, String dest, Set<Cargo> carg)
    {
        super(id, dt, dest, null, 10, carg, CargoType.PERISHABLE, 0.0);
    }

    public ToDistributionProfessionals(ToDistributionProfessionals tdp)
    {
        super(tdp);
    }

    // Getters & Setters

    // Instance methods

    // Complementary methods
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("ToDistributionProfessionals");
        sb.append(super.toString());
        return sb.toString();
    }

    @Override
    public ToDistributionProfessionals clone() { return new ToDistributionProfessionals(this); }
}
