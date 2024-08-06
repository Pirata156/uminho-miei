import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Write a description of class VehicleHashMap here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class VehicleHashMap implements Serializable
{
    // instance variables - replace the example below with your own
    private HashMap<String,Vehicle> vehicles;

    /**
     * Constructor for objects of class VehicleHashMap
     */
    public VehicleHashMap()
    {
        this.vehicles = new HashMap<String,Vehicle>();
    }

    /**
     * Copy constructor
     */
    public VehicleHashMap(VehicleHashMap vhm)
    {
        this.vehicles = new HashMap<String,Vehicle>();
        for(Vehicle v : vhm.vehicles.values()) {
            this.vehicles.put(v.getPlate(), v.clone());
        }
    }

    // Getters e Setters
    Set<Vehicle> getVehicles()
    {
        Set<Vehicle> res = new HashSet<Vehicle>();
        for(Vehicle v : this.vehicles.values()) {
            res.add(v);
        };
        return res;
    }

    void setVehicles(Set<Vehicle> vs) {
        for(Vehicle v : vs) {
            this.vehicles.put(v.getPlate(), v.clone());
        }
    }

    // Instance methods
    public boolean addVehicle(Vehicle vehi)
    {
        if(!this.vehicles.containsKey(vehi.getPlate())) {
            this.vehicles.put(vehi.getPlate(), vehi);
            return true;
        }
        return false;
    }

    public boolean removeVehicle(Vehicle vehi)
    {
        if(this.vehicles.containsKey(vehi.getPlate())) {
            this.vehicles.remove(vehi.getPlate(), vehi);
            return true;
        }
        return false;
    }

    public Vehicle getVehicle(String plate)
    {
        Vehicle res = null;
        if(this.vehicles.containsKey(plate)) {
            res = this.vehicles.get(plate).clone();
        }
        return res;
    }

    public List<Vehicle> listNotInDelivery()
    {
        List<Vehicle> res = new ArrayList<Vehicle>();
        for(Vehicle v : this.vehicles.values()) {
            if(!v.isInDelivery()) {
                res.add(v.clone());
            }
        }
        return res;
    }

    public List<Vehicle> listInDelivery()
    {
        List<Vehicle> res = new ArrayList<Vehicle>();
        for(Vehicle v : this.vehicles.values()) {
            if(v.isInDelivery()) {
                res.add(v.clone());
            }
        }
        return res;
    }

    public void setGlobalTruckBasePrice(double bp)
    {
        for(Vehicle v : this.vehicles.values()) {
            if(v instanceof Truck) {
                Truck tmp = (Truck) v;
                tmp.setBasePrice(bp);
            }
        }
    }

    public void setGlobalTruckXCoef(double coef)
    {
        for(Vehicle v : this.vehicles.values()) {
            if(v instanceof Truck) {
                Truck tmp = (Truck) v;
                tmp.setXCoef(coef);
            }
        }
    }

    public void setGlobalTruckYCoef(double coef)
    {
        for(Vehicle v : this.vehicles.values()) {
            if(v instanceof Truck) {
                Truck tmp = (Truck) v;
                tmp.setYCoef(coef);
            }
        }
    }

    // Complementary methods
    @Override
    public boolean equals(Object o)
    {
        boolean flag = true;
        if(this == o)
            return true;
        if((o == null) || (o.getClass() != this.getClass()))
            return false;
        VehicleHashMap vhm = (VehicleHashMap) o;
        ArrayList<Vehicle> oVehicles = new ArrayList<Vehicle>(vhm.vehicles.values());
        for(int i = 0; flag && i < oVehicles.size(); i++) {
            if(this.vehicles.containsKey(oVehicles.get(i).getPlate())) {
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Vehicles{");
        for(Vehicle v : this.vehicles.values()) {
            sb.append(v.toString() + ", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public VehicleHashMap clone() { return new VehicleHashMap(this); }
}
