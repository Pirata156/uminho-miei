import java.util.EnumSet;

/**
 * Write a description of class Truck here.
 *
 * @author PIRATA
 * @version v_002
 */
public class Truck extends Vehicle
{
    // Class variable - but instance one to allow to be changed indipendently
    private double basePrice = 400.0;

    public double getBasePrice() { return this.basePrice; }

    public void setBasePrice(double p) { this.basePrice = p; }

    // Instance variables
    private String driver;      // The driver of the truck
    private boolean trailer;    // Whether the truck has a trailer
    private double cargoHeight; // The useful loading height

    private double xCoef;
    private double yCoef;

    // Constructors
    public Truck()
    {
        super();
        this.driver = "UNDEFINED";
        this.trailer = false;
        this.cargoHeight = 0.0;
        this.xCoef = 0.0;
        this.yCoef = 0.0;
    }

    public Truck(String plate, String mod, double cpkm, double cap, String drv, boolean trl, double height)
    {
        super(plate, mod, cpkm, cap, false, EnumSet.of(CargoType.UNDIFFERENTIATED));
        this.setDriver(drv);
        this.setTrailer(trl);
        this.setHeightCapacity(height);
        this.setXCoef(0.0);
        this.setYCoef(0.0);
    }

    public Truck(Truck t)
    {
        super(t);
        this.driver = t.getDriver();
        this.trailer = t.getTrailer();
        this.cargoHeight = t.getHeightCapacity();
        this.xCoef = t.getXCoef();
        this.yCoef = t.getYCoef();
    }

    // Getters & Setters
    public String getDriver() { return this.driver; }

    public void setDriver(String drv)
    {
        // TODO: Some logic to check it's a valid name
        this.driver = drv;
    }

    public boolean getTrailer() { return this.trailer; }

    public void setTrailer(boolean tr) { this.trailer = tr; }

    public double getHeightCapacity() { return this.cargoHeight; }

    public void setHeightCapacity(double height)
    {
        // Checking height is above 0.
        this.cargoHeight = height > 0.0 ? height : 0.0;
    }

    public double getXCoef() { return this.xCoef; }

    public void setXCoef(double x) { this.xCoef = x; }

    public double getYCoef() { return this.yCoef; }

    public void setYCoef(double y) { this.yCoef = y; }

    // Instance methods
    public double getBaseCost() { return this.basePrice; };

    public double getBaseCostCargo(double weight_sum)
    {
        double res = this.getYCoef() * this.getBaseCost() + this.getXCoef() * weight_sum;
        return res;
    }

    // Complementary methods
    @Override
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o == null) || (o.getClass() != this.getClass()))
            return false;
        Truck t = (Truck) o;
        return (super.equals(t)
            && this.driver.equals(t.getDriver())
            && (this.trailer == t.getTrailer())
            && (Double.compare(this.cargoHeight, t.getHeightCapacity()) == 0));
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Truck{");
        sb.append(super.toString() + ", ");
        sb.append("Driver:" + this.driver + ", ");
        sb.append("Trailer:" + this.trailer + ", ");
        sb.append("Height:" + this.cargoHeight + "}");
        return sb.toString();
    }

    @Override
    public Truck clone() { return new Truck(this); }
}
