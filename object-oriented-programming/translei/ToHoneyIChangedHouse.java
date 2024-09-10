import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Write a description of class ToHoneyIChangedHouse here.
 *
 * @author PIRATA
 * @version v_002
 */
public class ToHoneyIChangedHouse extends CommercialProduct
{
    // Class variables
    private static double serviceDiscount = 0.8;

    public static double getDiscount() { return ToHoneyIChangedHouse.serviceDiscount; }

    public static void setDiscount(double d) { ToHoneyIChangedHouse.serviceDiscount = d; }
    // Instance variables
    
    // Constructors
    public ToHoneyIChangedHouse()
    {
        super();
        // Specific to ToDistributionProfessionals
        this.setCargoType(CargoType.UNDIFFERENTIATED);
        this.setMaxCargos(10);
    }

    public ToHoneyIChangedHouse(String id, GregorianCalendar dt, String dest, Set<Cargo> carg)
    {
        super(id, dt, dest, null, 10, carg, CargoType.UNDIFFERENTIATED, 0.0);
    }

    public ToHoneyIChangedHouse(ToHoneyIChangedHouse tdp)
    {
        super(tdp);
    }

    // Getters & Setters

    // Instance methods

    // Complementary methods
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("ToHoneyIChangedHouse");
        sb.append(super.toString());
        return sb.toString();
    }

    @Override
    public ToHoneyIChangedHouse clone() { return new ToHoneyIChangedHouse(this); }
}
