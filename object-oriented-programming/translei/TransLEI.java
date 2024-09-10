import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Write a description of class TransLEI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TransLEI implements Serializable
{
    private VehicleHashMap fleet;
    private ClientTreeSet clientele;
    private CargoHashMap cargo_dist;
    // Management values
    private double reefer_base_price = 60.0;
    private double cargo_van_base_price = 40.0;
    private double truck_base_price = 80.0;
    private double cargo_van_cargo_coef = 0.4;
    private double truck_cargo_coef = 0.3;
    private double truck_base_price_coef = 0.2;
    private double delivery_percentage = 0.6;
    // ID company global variables
    private long id_cargo_counter = 0;
    private long id_service_counter = 0;

    public TransLEI()
    {
        this.fleet = new VehicleHashMap();
        this.clientele = new ClientTreeSet();
        this.cargo_dist = new CargoHashMap();
        this.setGlobalReeferBasePrice(this.reefer_base_price);
        this.setGlobalVanBasePrice(this.cargo_van_base_price);
        this.setGlobalTruckBasePrice(this.truck_base_price);
        this.setGlobalTruckCargoCoef(this.truck_cargo_coef);
        this.setGlobalTruckBasePriceCoef(this.truck_base_price_coef);
        this.setGlobalVanCargoCoef(this.cargo_van_cargo_coef);
    }

    public String cargoIDGetIncrement()
    {
        String res = String.format("CG%08d", this.id_cargo_counter);
        this.id_cargo_counter++;
        return res;
    }

    public String serviceIDGetIncrement()
    {
        String res = String.format("SV%08d", this.id_service_counter);
        this.id_service_counter++;
        return res;
    }

    public void addNewClient(String name, String address, String nif, boolean isBusiness) throws RepeatedClientInsertionException
    {
        Client newClient;
        Client.ClientType cliType;
        boolean flag;
        if(isBusiness) {
            cliType = Client.ClientType.BUSINESS;
        } else {
            cliType = Client.ClientType.PRIVATE;
        }
        newClient = new Client(name, address, nif, cliType, List.of());
        flag = clientele.add(newClient);
        if(!flag) {
            throw new RepeatedClientInsertionException("Client already exists in the database!");
        }
    }

    public List<Client> clientsListByName()
    {
        return this.clientele.listByName();
    }

    public List<Client> clientsListByNIF()
    {
        return this.clientele.listByNIF();
    }

    public List<Client> clientsListByValue()
    {
        return this.clientele.listByContractedValue();
    }

    public List<Client> findClientsByName(String name)
    {
        return this.clientele.findName(name);
    }

    public List<Client> findClientsByNif(String nif)
    {
        return this.clientele.findNif(nif);
    }

    public boolean addTruckVehicleFleet(String plate, String mod, double costkm, double cap, String driver, boolean trailer, double height)
    {
        boolean res;
        Truck vtruck = new Truck(plate, mod, costkm, cap, driver, trailer, height);
        vtruck.setBasePrice(this.truck_base_price);
        vtruck.setXCoef(this.truck_cargo_coef);
        vtruck.setYCoef(this.truck_base_price_coef);
        res = this.fleet.addVehicle(vtruck);
        return res;
    }

    public boolean addReeferVehicleFleet(String plate, String mod, double costkm, double cap)
    {
        boolean res;
        Vehicle vreefer = new ReeferVan(plate, mod, costkm, cap);
        res = this.fleet.addVehicle(vreefer);
        return res;
    }

    public boolean addCargoVehicleFleet(String plate, String mod, double costkm, double cap, boolean refrig, String fuel, double autonomy)
    {
        boolean res;
        Vehicle vcargo = new CargoVan(plate, mod, costkm, cap, refrig, fuel, autonomy);
        res = this.fleet.addVehicle(vcargo);
        return res;
    }

    public boolean removeVehicleFleet(String plate)
    {
        boolean res = false;
        Vehicle toRemove = this.fleet.getVehicle(plate);
        if(toRemove != null) {
            this.fleet.removeVehicle(toRemove);
            res = true;
        }
        return res;
    }

    public List<Vehicle> fleetStationed()
    {
        return this.fleet.listNotInDelivery();
    }

    public List<Vehicle> fleetDelivering()
    {
        return this.fleet.listInDelivery();
    }

    public void setGlobalTruckBasePrice(double bp)
    {
        this.truck_base_price = bp;
        this.fleet.setGlobalTruckBasePrice(bp);
    }

    public void setGlobalReeferBasePrice(double bp)
    {
        this.reefer_base_price = bp;
        ReeferVan.setBasePrice(bp);
    }

    public void setGlobalVanBasePrice(double bp)
    {
        this.cargo_van_base_price = bp;
        CargoVan.setBasePrice(bp);
    }

    public void setGlobalVanCargoCoef(double coef)
    {
        this.cargo_van_cargo_coef = coef;
        CargoVan.setCoefX(coef);
    }

    public void setGlobalTruckCargoCoef(double coef)
    {
        this.truck_cargo_coef = coef;
        this.fleet.setGlobalTruckXCoef(coef);
    }

    public void setGlobalTruckBasePriceCoef(double coef)
    {
        this.truck_base_price_coef = coef;
        this.fleet.setGlobalTruckYCoef(coef);
    }

    public ArrayList<Vehicle> getTransportersOfType(CargoType cargo_type)
    {
        ArrayList<Vehicle> transporters = new ArrayList<>();
        for(Vehicle v : this.fleet.getVehicles()) {
            if(v.getCargoTypes().contains(cargo_type)) {
                transporters.add(v.clone());
            }
        }
        return transporters;
    }

    public double addCheckCargosPrice(ArrayList<Cargo> cargos)
    {
        double res = 0.0;
        boolean flag;
        int i;
            // Just to return and avoid errors in case list is empty
        if(!cargos.isEmpty()) {
            Vehicle temp = null;
            for(Cargo c : cargos) {
                flag = false;
                while(!flag) {
                    temp = this.cargo_dist.addCargo(c);
                    if(temp != null) {
                        res += temp.getBaseCostCargo(this.cargo_dist.getCurrentCargoWeight(temp));
                        flag = true;
                    } else {
                        // if(temp == null)
                        ArrayList<Vehicle> transp = this.getTransportersOfType(c.getCargoType());
                        for(Vehicle v : transp) {
                            if(v.isInDelivery() || this.cargo_dist.getVehicles().contains(v)) {
                                transp.remove(v);
                            }
                        }
                        if(transp.size() > 0) {
                            i = 0;
                            while(i < transp.size()) {
                                if(transp.get(i).getCapacity() > c.getWeight()) {
                                    this.cargo_dist.addVehicle(transp.get(i));
                                }
                                i++;
                            }
                        } else {
                            if(c.getCargoType() == CargoType.TOXIC) {
                                ArrayList<Vehicle> transp_t = this.getTransportersOfType(CargoType.UNDIFFERENTIATED);
                                for(Vehicle v : transp_t) {
                                    if(v.isInDelivery() || this.cargo_dist.getVehicles().contains(v)) {
                                        transp_t.remove(v);
                                    }
                                }
                                if(transp_t.size() > 0) {
                                    i = 0;
                                    while(i < transp_t.size()) {
                                        if(transp_t.get(i).getCapacity() > c.getWeight()) {
                                            Vehicle temp_vehi = transp_t.get(i);
                                            this.fleet.removeVehicle(temp_vehi);
                                            TreeSet<CargoType> cgtps = new TreeSet<CargoType>(temp_vehi.getCargoTypes());
                                            cgtps.add(CargoType.TOXIC);
                                            temp_vehi.setCargoTypes(cgtps);
                                            this.cargo_dist.addVehicle(temp_vehi);
                                            this.fleet.addVehicle(temp_vehi);
                                        }
                                        i++;
                                    }
                                } else {
                                    return -1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    public void removeCanceledCargos(ArrayList<Cargo> cargos)
    {
        for (Cargo c : cargos) {
            if (this.cargo_dist.removeCargo(c)) {
                this.id_cargo_counter--;
            }
        }
        this.id_service_counter--;
    }

    public void addNewService(CommercialProduct service, String client_nif)
    {
        //TODO: Add new service to the database
        this.clientele.addNewService(service, client_nif);
    }

    public void updateVehicles()
    {
        for(Vehicle v : this.fleet.getVehicles()) {
            if(v.isInDelivery()) {
                v.setDelivering(false);
            }
        }

        for(Vehicle v : this.cargo_dist.getVehicles()) {
            if(cargo_dist.getCurrentCargoWeight(v) / v.getCapacity() > this.delivery_percentage) {
                this.fleet.getVehicle(v.getPlate()).setDelivering(true);
                this.cargo_dist.removeVehicle(v);
            }
        }
    }

    public static void saveToFile(String filename, TransLEI company) throws Exception
    {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(company);
        oos.flush();
        oos.close();
        fos.close();
    }

    public static TransLEI loadFromFile(String filename) throws Exception
    {
        TransLEI res = new TransLEI();
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        res = (TransLEI) ois.readObject();
        ois.close();
        fis.close();
        return res;
    }
}
