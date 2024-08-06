import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeSet;

/**
 * Write a description of class CommercialProduct here.
 *
 * @author PIRATA
 * @version v_002
 */
public abstract class CommercialProduct implements Serializable
{
    // Instance variables
    private String idProdut;
    private GregorianCalendar date;
    private String destination;
    private int maxCargos;
    private TreeSet<Cargo> cargos;
    private CargoType type;
    private double price;
    
    // Constructors
    public CommercialProduct()
    {
        this.idProdut = "UNDEFINED";
        GregorianCalendar temp = new GregorianCalendar();
        this.setDate(temp);
        this.destination = "UNDEFINED";
        this.maxCargos = 0;
        this.cargos = new TreeSet<>();
        this.type = CargoType.UNDIFFERENTIATED;
        this.price = 0.0;
    }

    public CommercialProduct(String id, GregorianCalendar dt, String dest, String trans, int maxC, Set<Cargo> carg, CargoType tp, double cost)
    {
        this.setCode(id);
        this.setDate(dt);
        this.setDestination(dest);
        this.setMaxCargos(maxC);
        this.setCargos(carg);
        this.setCargoType(tp);
        this.setCost(cost);
    }

    public CommercialProduct(CommercialProduct cp)
    {
        this.idProdut = cp.getCode();
        this.date = cp.getDate();
        this.destination = cp.getDestination();
        this.maxCargos = cp.getMaxCargos();
        this.cargos = cp.getCargos();
        this.type = cp.getCargoType();
        this.price = cp.getCost();
    }

    // Getters & Setters
    public String getCode() { return this.idProdut; }

    public void setCode(String code)
    {
        // TODO: Some logic to see its valid input
        this.idProdut = code;
    }

    public GregorianCalendar getDate() { return this.date; }

    public void setDate(GregorianCalendar dt)
    {
        this.date = new GregorianCalendar();
        // Only passing the year, month and day.
        this.date.set(GregorianCalendar.YEAR, dt.get(GregorianCalendar.YEAR));
        this.date.set(GregorianCalendar.MONTH, dt.get(GregorianCalendar.MONTH));
        this.date.set(GregorianCalendar.DAY_OF_MONTH, dt.get(GregorianCalendar.DAY_OF_MONTH));

        // Set hours, minutes, seconds, and milliseconds to 0
        this.date.set(GregorianCalendar.HOUR_OF_DAY, 0);
        this.date.set(GregorianCalendar.MINUTE, 0);
        this.date.set(GregorianCalendar.SECOND, 0);
        this.date.set(GregorianCalendar.MILLISECOND, 0);
    }

    public String getDestination() { return this.destination; }

    public void setDestination(String dest)
    {
        // TODO: Some logic to check its valid address info
        this.destination = dest;
    }

    public int getMaxCargos() { return this.maxCargos; }

    public void setMaxCargos(int mc)
    {
        this.maxCargos = mc > 0 ? mc : 0;
    }

    public TreeSet<Cargo> getCargos()
    {
        TreeSet<Cargo> res = new TreeSet<>();
        for(Cargo c : this.cargos) {
            res.add(c.clone());
        }
        return res;
    }

    public void setCargos(Set<Cargo> carg)
    {
        this.cargos = new TreeSet<>();
        for(Cargo c : carg) {
            this.cargos.add(c.clone());
        }
    }

    public CargoType getCargoType() { return this.type; }

    public void setCargoType(CargoType tp) { this.type = tp; }

    public double getCost() { return this.price; }

    public void setCost(double cost)
    {
        this.price = cost > 0 ? cost : 0.0;
    }

    // Instance methods
    public boolean hasCargoSlot()
    {
        return (this.cargos.size() < this.maxCargos); 
    }

    public void addCargo(Cargo c) throws CargoLimiteExceededException
    {
        if(this.cargos.size() < this.getMaxCargos()) {
            this.cargos.add(c.clone());
        } else {
            throw new CargoLimiteExceededException("Ammount of cargos this product covers exceeded!");
        }
    }

    public double totalWeight()
    {
        double res = 0.0;
        for(Cargo c : this.cargos) {
            res += c.getWeight();
        }
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
        CommercialProduct cp = (CommercialProduct) o;
        return (this.idProdut.equals(cp.getCode())
            && this.date.equals(cp.getDate())
            && this.destination.equals(cp.getDestination())
            && (this.maxCargos == cp.getMaxCargos())
            && this.cargos.equals(cp.getCargos())
            && (this.type == cp.getCargoType())
            && (Double.compare(this.price, cp.getCost()) == 0));
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("{");
        sb.append("ID:\"" + this.idProdut + "\"; ");
        sb.append("Date:" + this.date.getTime().toString() + "; ");
        sb.append("Dest:\"" + this.destination + "\"; ");
        sb.append("MaxCargos:" + this.maxCargos + "; ");
        sb.append("Cargos:" + this.cargos.toString() + "; ");
        sb.append("Type:\"" + this.type.toString() + "\"; ");
        sb.append("Price:" + this.price + "}");
        return sb.toString();
    }

    public abstract CommercialProduct clone();

    @Override
    public int hashCode() { return this.getCode().hashCode(); }
}
