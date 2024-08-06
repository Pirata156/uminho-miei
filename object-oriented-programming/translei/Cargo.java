import java.io.Serializable;
import java.lang.Comparable;

/**
 * Write a description of class Cargo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cargo implements Comparable<Cargo>, Serializable
{
    // Instance variables
    private String cargoID;     // The ID code of the cargo
    private String description; // Description or name of the cargo
    private CargoType type;     // Type of cargo from the Enumerated ones
    private double weight;      // Cargo weight in kilograms

    // Constructors
    public Cargo()
    {
        this.cargoID = "UNDEFINED";
        this.description = "UNDEFINED";
        this.type = CargoType.UNDIFFERENTIATED;
        this.weight = 0.0;
    }

    public Cargo(String id, String desc, CargoType type, double weight)
    {
        this.setCode(id);
        this.setDescription(desc);
        this.setCargoType(type);
        this.setWeight(weight);
    }

    public Cargo(Cargo c)
    {
        this.cargoID = c.getCode();
        this.description = c.getDescription();
        this.type = c.getCargoType();
        this.weight = c.getWeight();
    }

    // Getters & Setters
    public String getCode() { return this.cargoID; }

    public void setCode(String code)
    {
        // TODO: Some logic to pattern match the validity of the code
        this.cargoID = code;
    }

    public String getDescription() { return this.description; }

    public void setDescription(String desc)
    {
        // TODO: Some logic to check validity of description
        this.description = desc;
    }

    public CargoType getCargoType() { return this.type; }

    public void setCargoType(CargoType type)
    {
        // As long its CargoType, its valid.
        this.type = type;
    }

    public double getWeight() { return this.weight; }

    public void setWeight(double weight)
    {
        // TODO: Some checking that the weight is valid, for now as long as > 0.
        this.weight = weight > 0.0 ? weight : 0.0;
    }

    // Instance methods
    public boolean isType(CargoType ct)
    {
        return (this.getCargoType() == ct);
    }

    // Comparable
    @Override
    public int compareTo(Cargo c)
    {
        return this.getCode().compareTo(c.getCode());
    }

    // Complementary methods
    @Override
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o == null) || (o.getClass() != this.getClass()))
            return false;
        Cargo c = (Cargo) o;
        return (this.cargoID.equals(c.getCode())
            && this.description.equals(c.getDescription())
            && (this.type == c.getCargoType())
            && (Double.compare(this.weight, c.getWeight()) == 0));
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Cargo{");
        sb.append("Code:\"" + this.cargoID + "\", ");
        sb.append("Description:\"" + this.description + "\", ");
        sb.append("Type:\"" + this.type.toString() + "\", ");
        sb.append("Weight:" + this.weight + "}");
        return sb.toString();
    }

    @Override
    public Cargo clone() { return new Cargo(this); }

    @Override
    public int hashCode() { return this.cargoID.hashCode(); }
}
