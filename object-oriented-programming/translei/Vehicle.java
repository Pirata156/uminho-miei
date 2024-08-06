import java.util.Set;
import java.io.Serializable;
import java.util.EnumSet;

/**
 * Abstract class representing a vehicle used for transportation.
 * This class serves as a blueprint for specific types of vehicles and
 * includes common characteristics shared among different vehicle types.
 *
 * @author PIRATA
 * @version v_002
 */
public abstract class Vehicle implements Serializable
{
    // Instance variables
    private String idPlate;                 // ID Plate of the vehicle
    private String brandModel;              // Brand and model of the vehicle
    private double costPerKm;               // Cost per kilometer, considering fuel and vehicle wear
    private double cargoCapacity;           // Useful load capacity in kilograms

    private boolean refrigeration;          // Whether the vehicle is equipped with refrigeration
    private boolean inDelivery;             // Whether the vehicle is currently in a delivery
    private EnumSet<CargoType> validCargo;  // Set of valid cargo types that this vehicle can transport

    // Constructors
    public Vehicle()
    {
        this.idPlate = "UNDEFINED";
        this.brandModel = "UNDEFINED";
        this.costPerKm = 0.0;
        this.cargoCapacity = 0.0;
        this.refrigeration = false;
        this.inDelivery = false;
        this.validCargo = EnumSet.noneOf(CargoType.class);  // Can't use the new with EnumSet
    }

    public Vehicle(String plate, String mod, double cpkm, double cap, boolean ref, Set<CargoType> types)
    {
        this.setPlate(plate);
        this.setModel(mod);
        this.setCostKm(cpkm);
        this.setCapacity(cap);
        this.setRefrigeration(ref);
        this.setDelivering(false);
        this.setCargoTypes(types);
    }

    public Vehicle(Vehicle v)
    {
        this.idPlate = v.getPlate();
        this.brandModel = v.getModel();
        this.costPerKm = v.getCostKm();
        this.cargoCapacity = v.getCapacity();
        this.refrigeration = v.getRefrigeration();
        this.inDelivery = v.getDelivering();
        this.validCargo = EnumSet.copyOf(v.getCargoTypes());
    }

    // Getters & Setters
    public String getPlate() { return this.idPlate; }

    public void setPlate(String p)
    {
        // TODO: Some logic that checks its a valid plate
        this.idPlate = p;
    }

    public String getModel() { return this.brandModel; }

    public void setModel(String mod)
    {
        // TODO: Some logic that checks its valid Brand and Model
        this.brandModel = mod;
    }

    public double getCostKm() { return this.costPerKm; }

    public void setCostKm(double val)
    {
        // Checks value above 0 otherwise makes it 0.
        this.costPerKm = val > 0.0 ? val : 0.0;
    }

    public double getCapacity() { return this.cargoCapacity; }

    public void setCapacity(double cap)
    {
        // To not be restrictive, only checking its above 0.
        this.cargoCapacity = cap > 0.0 ? cap : 0.0;
    }

    public boolean getRefrigeration() { return this.refrigeration; }

    public void setRefrigeration(boolean ref) { this.refrigeration = ref; }

    public boolean getDelivering() { return this.inDelivery; }

    public void setDelivering(boolean del) { this.inDelivery = del; }

    public Set<CargoType> getCargoTypes()
    {
        // EnumSet.CopyOf makes a deep copy.
        Set<CargoType> res = EnumSet.copyOf(this.validCargo);
        return res;
    }

    public void setCargoTypes(Set<CargoType> cts)
    {
        this.validCargo = EnumSet.copyOf(cts);
    }

    // Instance methods
    public boolean isRefrigerated() { return this.getRefrigeration(); }

    public boolean isInDelivery() { return this.getDelivering(); }

    public abstract double getBaseCostCargo(double weight_sum);

    // Complementary methods
    @Override
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o == null) || (o.getClass() != this.getClass()))
            return false;
        Vehicle v = (Vehicle) o;
        // we compare all attributes of a Vehicle, but in some cases, only plate comparison might suffice
        return (this.idPlate.equals(v.getPlate())
            && this.brandModel.equals(v.getModel())
            && (Double.compare(this.costPerKm, v.getCostKm()) == 0)
            && (Double.compare(this.cargoCapacity, v.getCapacity()) == 0)
            && (this.refrigeration == v.getRefrigeration())
            && (this.inDelivery == v.getDelivering())
            && this.validCargo.equals(v.getCargoTypes()));
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Plate:\"" + this.idPlate + "\", ");
        sb.append("Model:\"" + this.brandModel + "\", ");
        sb.append("CostKm:" + this.costPerKm + ", ");
        sb.append("Capacity:" + this.cargoCapacity + ", ");
        sb.append("Reefer:" + this.refrigeration + ", ");
        sb.append("OnRoute:" + this.inDelivery + ", ");
        sb.append("Valid:" + this.validCargo.toString());
        return sb.toString();
    }

    @Override
    public abstract Vehicle clone();

    @Override
    public int hashCode() { return this.idPlate.hashCode(); }
}