import java.util.EnumSet;

/**
 * Write a description of class CargoVan here.
 *
 * @author PIRATA
 * @version v_002
 */
public class CargoVan extends Vehicle
{
    // Class variables
    private static double basePrice = 40.0;
    private static double xCoef = 0.3;

    public static double getBasePrice() { return CargoVan.basePrice; }
    public static double getCoefX() { return CargoVan.xCoef; }

    public static void setBasePrice(double p) { CargoVan.basePrice = p; }
    public static void setCoefX(double x) { CargoVan.xCoef = x; }
    // Instance variables
    private String fuelType;    // The type of fuel used
    private double autonomy;    // The autonomy in kilometers

    // Constructors
    public CargoVan()
    {
        super();
        this.fuelType = "UNDEFINED";
        this.autonomy = 0.0;
    }

    public CargoVan(String plate, String mod, double cpkm, double cap, boolean ref, String fuel, double auto)
    {
        super(plate, mod, cpkm, cap, ref, EnumSet.noneOf(CargoType.class));
        if(ref) {
            this.setCargoTypes(EnumSet.of(CargoType.PERISHABLE, CargoType.UNDIFFERENTIATED));
        } else {
            this.setCargoTypes(EnumSet.of(CargoType.UNDIFFERENTIATED));
        }
        this.setFuelType(fuel);
        this.setAutonomy(auto);
    }

    public CargoVan(CargoVan cv)
    {
        super(cv);
        this.fuelType = cv.getFuelType();
        this.autonomy = cv.getAutonomy();
    }

    // Getters & Setters
    public String getFuelType() { return this.fuelType; }

    public void setFuelType(String fuel)
    {
        // TODO: Some logic to check for the fuel type to be a valid one
        this.fuelType = fuel;
    }

    public double getAutonomy() { return this.autonomy; }

    public void setAutonomy(double auto)
    {
        this.autonomy = auto > 0.0 ? auto : 0.0;
    }

    // Instance methods
    public double getBaseCost() { return CargoVan.getBasePrice(); }

    public double getBaseCostCargo(double weight_sum)
    {
        double res = this.getBaseCost() + CargoVan.getCoefX() * (this.getCapacity() - weight_sum);
        return res;
    }

    // Complementary methods
    @Override
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass()))
            return false;
        CargoVan cv = (CargoVan) o;
        return (super.equals(cv)
            && this.fuelType.equals(cv.getFuelType())
            && (Double.compare(this.autonomy, cv.getAutonomy()) == 0));
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("CargoVan{");
        sb.append(super.toString() + ", ");
        sb.append("Fuel:\"" + this.getFuelType() + "\", ");
        sb.append("Autonomy:" + this.autonomy + "}");
        return sb.toString();
    }

    @Override
    public CargoVan clone() { return new CargoVan(this); }
}
