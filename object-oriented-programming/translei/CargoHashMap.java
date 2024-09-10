import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class CargoHashMap implements Serializable
{
    private HashMap<Vehicle, ArrayList<Cargo>> cargo_list;

    public CargoHashMap()
    {
        this.cargo_list = new HashMap<>();
    }

    public List<Vehicle> getVehicles()
    {
        List<Vehicle> res = new ArrayList<Vehicle>(this.cargo_list.keySet());
        return res;
    } 

    public ArrayList<Vehicle> getTransportersOfType(CargoType cargo_type)
    {
        ArrayList<Vehicle> transporters = new ArrayList<>();
        for(Vehicle v : cargo_list.keySet())
        {
            if(v.getCargoTypes().contains(cargo_type)) {
                transporters.add(v.clone());
            }
        }
        transporters.sort(new VehicleFillComparator(this));
        return transporters;
    }

    public double getCurrentCargoWeight(Vehicle v)
    {
        double res = 0;
        for(Cargo c : this.cargo_list.get(v)) {
            res += c.getWeight();
        }
        return res;
    }

    public Vehicle addCargo(Cargo c)
    {
        Vehicle res = null;
        ArrayList<Vehicle> transporters = this.getTransportersOfType(c.getCargoType());
        for(int i = 0; (res == null) && (i < transporters.size()); i++) {
            Vehicle v = transporters.get(i);
            if(this.getCurrentCargoWeight(v) + c.getWeight() <= v.getCapacity()) {
                res = v;
            }
        }
        if(res != null) {
            this.cargo_list.get(res).add(c.clone());
        } 
        return res;
    }

    public boolean removeCargo(Cargo c)
    {
        boolean res = false;
        for (ArrayList<Cargo> alc : this.cargo_list.values()) {
            res = alc.remove(c);
            if(res) {
                return res;
            }
        }
        return res;
    }

    void addVehicle(Vehicle v)
    {
        this.cargo_list.put(v, new ArrayList<Cargo>());
    }

    void removeVehicle(Vehicle v)
    {
        this.cargo_list.remove(v);
    }

    @Override
    public CargoHashMap clone()
    {
        CargoHashMap res = new CargoHashMap();
        for(Vehicle v : this.cargo_list.keySet()) {
            ArrayList<Cargo> temp = new ArrayList<Cargo>();
            for(Cargo c : this.cargo_list.get(v)) {
                temp.add(c.clone());
            }
            res.cargo_list.put(v.clone(), temp);
        }
        return res;
    }

    public class VehicleFillComparator implements Comparator<Vehicle>
    {
        private CargoHashMap cargos_trans;

        public VehicleFillComparator(CargoHashMap listing)
        {
            this.cargos_trans = listing.clone();
        }

        public int compare(Vehicle vehi1, Vehicle vehi2)
        {
            double v1 = cargos_trans.getCurrentCargoWeight(vehi1) / vehi1.getCapacity();
            double v2 = cargos_trans.getCurrentCargoWeight(vehi2) / vehi2.getCapacity();
            return Double.compare(v2,v1);   // Inversed to give the biggest first
        }
    }
}
