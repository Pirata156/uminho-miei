import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Write a description of class ToAirport here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ToAirport extends CommercialProduct
{
    // Class Variable
    private static double serviceDiscount = 0.50;

    public static double getDiscount() { return ToAirport.serviceDiscount; }
    
    public static void setDiscount(double d) { ToAirport.serviceDiscount = d; }

    // Instance variables
    
    // Constructors
    public ToAirport()
    {
        super();
        // Specific to ToAirport
        this.setCargoType(CargoType.UNDIFFERENTIATED);
        this.setMaxCargos(3);
    }

    public ToAirport(String id, GregorianCalendar dt, String dest, Set<Cargo> carg)
    {
        super(id, dt, dest, null, 3, carg, CargoType.UNDIFFERENTIATED, 0.0);
    }

    public ToAirport(ToAirport ta)
    {
        super(ta);
    }

    // Getters & Setters

    // Instance methods

    // Complementary methods
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("ToAirport");
        sb.append(super.toString());
        return sb.toString();
    }

    @Override
    public ToAirport clone() { return new ToAirport(this); }
}
