
/**
 * The Vehicle class represents a motor vehicle with various attributes and methods to manage and
 * interact with the vehicle's data.
 * It keeps track of the vehicle's license number, total mileage, tank capacity, current fuel level,
 * reserve size, average consumption per 100 km, and trip counter.
 *
 * @author (PIRATA)
 * @version (2012.06.04)
 */
public class Vehicle
{
    // Instance variables
    private String license;                 // The license plate number of the vehicle.
    private double kilometers;              // The total mileage of the vehicle in kilometers.
    private double tankCapacity;            // The total tank capacity of the vehicle in liters.
    private double currentFuel;             // The current fuel level in the tank in liters.
    private final double reserve = 10.0;    // The reserve fuel capacity in liters (fixed at 10 liters).
    private double avgConsumption;          // The average fuel consumption per 100 kilometers.
    private int tripCounter;                // The trip counter, keeping track of the number of trips taken.

    /**
     * Default constructor for objects of class Vehicle.
     */
    public Vehicle()
    {
        // Initialise instance variables
        this.license = "NA-NA-NA";
        this.kilometers = 0.0;
        this.tankCapacity = 0.0;
        this.currentFuel = 0.0;
        this.avgConsumption = 0.0;
        this.tripCounter = 0;
    }
    
    /**
     * Constructs a new Vehicle by copying the attributes from the given Vehicle.
     *
     * @param v The Vehicle to copy data from.
     */
    public Vehicle(Vehicle v)
    {
        this.license = v.getLicense();
        this.kilometers = v.getKms();
        this.tankCapacity = v.getTank();
        this.currentFuel = v.getFuelLevel();
        this.avgConsumption = v.getAvgConsumption();
        this.tripCounter = v.getTripCounter();
    }
    
    /**
     * Parameterized constructor to create a new Vehicle object with specified attributes.
     *
     * @param mat       The license plate number.
     * @param kms       The total mileage (Km).
     * @param tank      The tank capacity.
     * @param fuel      The current fuel level.
     * @param avgCon    The average consumption per 100 km.
     * @param ctr       The trip counter.
     */
    public Vehicle(String mat, double kms, double tank, double fuel, double avgCon, int ctr)
    {
        this.setLicense(mat);
        this.setKms(kms);
        this.setTank(tank);
        this.setFuelLevel(fuel);
        this.setAvgConsumption(avgCon);
        this.setTripCounter(ctr);
    }
    
    // Getters & Setters
    /**
     * Get the license plate of the vehicle.
     *
     * @return  The license plate of the vehicle.
     */
    public String getLicense() { return this.license; }
    
    /**
     * Get the total mileage of the vehicle in kilometers.
     *
     * @return  The total mileage of the vehicle.
     */
    public double getKms() { return this.kilometers; }
    
    /**
     * Get the tank capacity of the vehicle in liters.
     *
     * @return  The tank capacity of the vehicle.
     */
    public double getTank() { return this.tankCapacity; }
    
    /**
     * Get the current fuel level in the tank in liters.
     *
     * @return  The current fuel level in the tank.
     */
    public double getFuelLevel() { return this.currentFuel; }
    
    /**
     * Get the size of the reserve fuel in liters (constant value).
     *
     * @return  The size of the reserve fuel.
     */
    public double getReserveSize() { return this.reserve; }
    
    /**
     * Get the average fuel consumption per 100 kilometers.
     *
     * @return  The average fuel consumption per 100 kilometers.
     */
    public double getAvgConsumption() { return this.avgConsumption; }
    
    /**
     * Get the trip counter, indicating the number of recorded trips.
     *
     * @return  The trip counter value.
     */
    public int getTripCounter() { return this.tripCounter; }
    
    /**
     * Set the license plate of the vehicle.
     *
     * @param l The new license plate to set.
     */
    public void setLicense(String l) { this.license = l; }
    
    /**
     * Set the total mileage of the vehicle. Negative values are capped at 0.
     *
     * @param k The new total mileage to set.
     */
    public void setKms(double k) { this.kilometers = (k < 0) ? 0.0 : k; }
    
    /**
     * Set the tank capacity of the vehicle. Negative values are capped at 0.
     *
     * @param t The new tank capacity to set.
     */
    public void setTank(double t) { this.tankCapacity = (t < 0) ? 0.0 : t; }    // Correctly, it should check its at least bigger than the reserve!
    
    /**
     * Set the current fuel level in the tank. Negative values are capped at 0.
     *
     * @param f The new fuel level to set.
     */
    public void setFuelLevel(double f) { this.currentFuel = (f < 0) ? 0.0 : ((f > this.getTank()) ? this.getTank() : f); }
    
    /**
     * Set the average fuel consumption per 100 kilometers.
     *
     * @param c The new average consumption value to set.
     */
    public void setAvgConsumption(double c) { this.avgConsumption = (c < 0) ? 0.0 : c; }
    
    /**
     * Set the trip counter to update the number of recorded trips.
     *
     * @param c The new trip counter value to set.
     */
    public void setTripCounter(int c) { this.tripCounter = (c < 0) ? 0 : c; }   // This one should prolly be private in a RL scenario?!

    // Instance methods
    /**
     * Calculate the average mileage left based on the current fuel level and consumption.
     *
     * @return  The average mileage left in kilometers.
     */
    public double avgMileageLeft()
    {
        double res, avg = this.getAvgConsumption();
        if(!(avg > 0)) {
            avg = Double.MIN_VALUE; // To avoid divisions by 0.
        }
        res = (this.getFuelLevel() / avg) * 100;
        return res;
    }
    
    /**
     * Record a trip of a specified distance and update vehicle data.
     *
     * @param k The distance of the trip in kilometers.
     */
    public void recordTrip(double k)
    {   // I'm just seriously going to ignore non-positive kms inputs - Didn't walk, its not a trip!
        if(k > 0) {
            this.setFuelLevel(this.getFuelLevel() - ((k / 100) * this.getAvgConsumption()));
            //this.currentFuel -= (kms / 100) * this.avgConsumption; But the set checks for value going under 0 already;
            this.tripCounter++;
            this.kilometers += k;
        }
    }
    
    /**
     * Check if the vehicle has entered the reserve zone.
     *
     * @return  true if the fuel level is in the reserve, false otherwise.
     */
    public boolean inReserve()
    {
        return this.getFuelLevel() <= this.getReserveSize();
    }
    
    /**
     * Calculate the total cost spent on fuel based on the average cost per liter.
     *
     * @param costLiter The average cost per liter of fuel.
     * @return          The total cost of fuel for the recorded mileage.
     */
    public double totalFuelCost(double costLiter)
    {
        double res = this.getKms() * (this.getAvgConsumption() / 100) * costLiter;
        return res;
    }
    
    /**
     * Calculate the average cost per kilometer based on the average cost per liter.
     *
     * @param costLiter The average cost per liter of fuel.
     * @return          The average cost per kilometer driven.
     */
    public double costPerKm(double costLiter)
    {
        double res = (this.getAvgConsumption() / 100) * costLiter;
        return res;
    }
    
    /**
     * Refuel the vehicle with a specified number of liters, filling the tank without overflowing.
     *
     * @param liters    The number of liters to refuel.
     * @return          The actual number of liters added to the tank.
     */
    public double refuel(double liters)
    {
        double left2fill = this.getTank() - this.getFuelLevel();
        double res;
        if(liters > left2fill) {
            //this.currentFuel = this.tankCapacity; could avoid minimalist doubles errors
            res = left2fill;
        } else {    //this.currentFuel += liters;
            res = liters;
        }
        this.currentFuel += res;
        return res;
    }
    
    // Complementary methods
    /**
     * Check if two Vehicle objects are equal based on their attributes.
     *
     * @param o The object to compare with.
     * @return  true if the objects are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass()))
            return false;
        Vehicle v = (Vehicle) o;
        return ((this.getLicense().equals(v.getLicense())) &&
                (this.getKms() == v.getKms()) &&
                (this.getTank() == v.getTank()) &&
                (this.getFuelLevel() == v.getFuelLevel()) &&
                (this.getAvgConsumption() == v.getAvgConsumption()) &&
                (this.getTripCounter() == v.getTripCounter()));
    }
    
    /**
     * Generate a string representation of the Vehicle object.
     *
     * @return A string representation of the vehicle's attributes.
     */
    public String toString()
    {
        StringBuilder s = new StringBuilder("Vehicle{");
        s.append("License=\'" + this.getLicense() + "\'");
        s.append(", Mileage=" + this.getKms());
        s.append(", Tank=" + this.getTank());
        s.append(", Fuel=" + this.getFuelLevel());
        s.append(", Average100Km=" + this.getAvgConsumption());
        s.append(", Trips=" + this.getTripCounter());
        s.append("}");
        return s.toString();
    }
    
    /**
     * Creates a new Vehicle object that is a deep copy of this Segment.
     *
     * @return  A new Vehicle object with the same attributes as the original.
     */
    public Vehicle clone() { return new Vehicle(this); }
}
