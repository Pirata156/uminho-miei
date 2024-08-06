import java.util.EnumSet;

/**
 * Write a description of class ReeferVan here.
 *
 * @author PIRATA
 * @version v_002
 */
public class ReeferVan extends Vehicle
{
    // Class variables
    private static double basePrice = 60.0;

    public static double getBasePrice() { return ReeferVan.basePrice; }

    public static void setBasePrice(double p) { ReeferVan.basePrice = p; }
    // Instance variables
    
    // Constructors
    public ReeferVan()
    {
        super();
        this.setRefrigeration(true);
        this.setCargoTypes(EnumSet.of(CargoType.PERISHABLE, CargoType.UNDIFFERENTIATED));
    }

    public ReeferVan(String plate, String mod, double cpkm, double cap)
    {
        super(plate, mod, cpkm, cap, true, EnumSet.of(CargoType.PERISHABLE, CargoType.UNDIFFERENTIATED));
    }

    public ReeferVan(ReeferVan rv)
    {
        super(rv);
    }
    
    // Getters & Setters

    // Instance methods
    public double getBaseCost() { return ReeferVan.getBasePrice(); }

    public double getBaseCostCargo(double weight_sum)
    {
        double res = this.getBaseCost();
        return res;
    }

    // Complementary methods
    // equals doesn't change compared to Vehicle one
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Reefer{");
        sb.append(super.toString() + "}");
        return sb.toString();
    }

    @Override
    public ReeferVan clone() { return new ReeferVan(this); }
}
