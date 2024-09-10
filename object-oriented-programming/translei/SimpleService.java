import java.util.GregorianCalendar;
import java.util.TreeSet;

/**
 * Write a description of class SimpleService here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SimpleService extends CommercialProduct
{
    // Instance variables
    
    // Constructors
    public SimpleService()
    {
        super();
        // Specific to SimpleServices
        this.setCargoType(CargoType.UNDIFFERENTIATED);
        this.setMaxCargos(1);
    }

    public SimpleService(String id, GregorianCalendar dt, String dest, Cargo carg)
    {
        super(id,  dt, dest, null, 1, new TreeSet<Cargo>(), CargoType.UNDIFFERENTIATED, 0.0);
        try {
            this.addCargo(carg);
        } catch (CargoLimiteExceededException e) { /* Will not happen in this case */ }
    }
    
    public SimpleService(SimpleService ss)
    {
        super(ss);
    }

    // Getters && Setters

    // Instance methods

    // Complementary methods
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("SimpleService");
        sb.append(super.toString());
        return sb.toString();
    }

    @Override
    public SimpleService clone() { return new SimpleService(this); }
}
