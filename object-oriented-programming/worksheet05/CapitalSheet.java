
/**
 * The CapitalSheet class represents information about the capital of a country, including
 * the name of the city, population, number of vehicles, average salary, and cost of living average monthly.
 *
 * @author PIRATA
 * @version 2013.05.02
 */
public class CapitalSheet
{
    // Instance variables
    private String cityName;        // Name of the city
    private int population;         // Population of the city
    private int numberVehicles;     // Number of vehicles in the city
    private double averageSalary;   // Average salary in the city
    private double costOfLiving;    // Average monthly cost of living in the city
    
    // Constructors
    /**
     * Default constructor for the CapitalSheet class.
     * Initializes the capital with default values.
     */
    public CapitalSheet()
    {
        this.cityName = "-UNKNOWN-";
        this.population = 0;
        this.numberVehicles = 0;
        this.averageSalary = 0.0;
        this.costOfLiving = 0.0;
    }
    
    /**
     * Copy constructor for the CapitalSheet class.
     * Creates a new CapitalSheet with the same attributes as the given CapitalSheet.
     *
     * @param cap   The CapitalSheet copy the data from.
     */
    public CapitalSheet(CapitalSheet cap)
    {
        this.cityName = cap.getName();
        this.population = cap.getPopul();
        this.numberVehicles = cap.getVehicles();
        this.averageSalary = cap.getSalary();
        this.costOfLiving = cap.getLifeCost();
    }
    
    /**
     * Parameterized constructor for the CapitalSheet class.
     * Initializes the capital with the specified attributes.
     *
     * @param nm    The name of the city.
     * @param pop   The population of the city.
     * @param nv    The number of vehicles in the city.
     * @param avs   The average salary in the city.
     * @param col   The average monthly cost of living in the city.
     */
    public CapitalSheet(String nm, int pop, int nv, double avs, double col)
    {
        this.setName(nm);
        this.setPopul(pop);
        this.setVehicles(nv);
        this.setSalary(avs);
        this.setLifeCost(col);
    }
    
    // Getters & Setters
    /**
     * Get the name of the city.
     *
     * @return  The name of the city.
     */
    public String getName() { return this.cityName; }

    /**
     * Set the name of the city.
     *
     * @param name  The new name for the city.
     */
    public void setName(String name) { this.cityName = name; }

    /**
     * Get the population of the city.
     *
     * @return  The population of the city.
     */
    public int getPopul() { return this.population; }

    /**
     * Set the population of the city.
     *
     * @param pop   The new population for the city.
     */
    public void setPopul(int pop) { this.population = pop; }

    /**
     * Get the number of vehicles in the city.
     *
     * @return  The number of vehicles in the city.
     */
    public int getVehicles() { return this.numberVehicles; }

    /**
     * Set the number of vehicles in the city.
     *
     * @param vehicles  The new number of vehicles for the city.
     */
    public void setVehicles(int vehicles) { this.numberVehicles = vehicles; }

    /**
     * Get the average salary in the city.
     *
     * @return  The average salary in the city.
     */
    public double getSalary() { return this.averageSalary; }

    /**
     * Set the average salary in the city.
     *
     * @param avgS  The new average salary for the city.
     */
    public void setSalary(double avgS) { this.averageSalary = avgS; }

    /**
     * Get the cost of living average monthly in the city.
     *
     * @return  The cost of living average monthly in the city.
     */
    public double getLifeCost() { return this.costOfLiving; }

    /**
     * Set the cost of living average monthly in the city.
     *
     * @param col   The new cost of living for the city.
     */
    public void setLifeCost(double col) { this.costOfLiving = col; }
    
    // Complementary methods
    /**
     * Check if two CapitalSheet objects are equal based on their attributes.
     * Two CapitalSheet objects are considered equal if they have the same city name,
     * population, number of vehicles, average salary, and cost of living.
     *
     * @param o The object to compare with.
     * @return  true if the objects are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;

        CapitalSheet cs = (CapitalSheet) o;
        return (this.cityName.equals(cs.getName()) &&
                (this.population == cs.getPopul()) &&
                (this.numberVehicles == cs.getVehicles()) &&
                (Double.compare(this.averageSalary, cs.getSalary()) == 0) &&
                (Double.compare(this.costOfLiving, cs.getLifeCost()) == 0));
    }

    /**
     * Returns a string representation of the CapitalSheet.
     *
     * @return  A string representation of the CapitalSheet.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder("CapitalSheet:");
        sb.append("Name='" + this.getName() + "';");
        sb.append("Population=" + this.getPopul() + ";");
        sb.append("Vehicles=" + this.getVehicles() + ";");
        sb.append("Salary=" + this.getSalary() + ";");
        sb.append("LifeCost=" + this.getLifeCost());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Creates a new CapitalSheet object that is a deep copy of this CapitalSheet.
     *
     * @return  A new CapitalSheet object with the same attributes as the original.
     */
    public CapitalSheet clone() { return new CapitalSheet(this); }

    /**
     * Returns a hash code value for the CapitalSheet.
     *
     * @return  A hash code value for the CapitalSheet.
     */
    public int hashCode() { return this.toString().hashCode(); }
}
