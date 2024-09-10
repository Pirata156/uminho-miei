import java.util.HashMap;   // Class
import java.util.HashSet;   // Class
import java.util.ArrayList; // Class
import java.util.Map;       // Interface
import java.util.Set;       // Interface - Contains no duplicates
import java.util.List;      // Interface

/**
 * The Countries class establishes a correspondence between the name of a country and information about its capital (CapitalSheet),
 * including the name of the city, population, number of vehicles, average salary, and cost of living average monthly.
 *
 * @author PIRATA
 * @version 2013.05.02
 */
public class Countries
{
    // Instance variables
    private HashMap<String,CapitalSheet> countryCapitals;   // Relation <Country,CapitalSheet>
    
    // Constructors
    /**
     * Default constructor for the Countries class.
     * Initializes the countryCapitals with an empty hashmap.
     */
    public Countries()
    {
        this.countryCapitals = new HashMap<String,CapitalSheet>();
    }
    
    /**
     * Copy constructor for the Countries class.
     * Creates a new Countries object with the same attribute as the given Countries object.
     *
     * @param cts   The Countries object to be copied.
     */
    public Countries(Countries cts)
    {
        this.countryCapitals = cts.getCountryCapitals();
    }
    
    /**
     * Parameterized constructor for the Countries class.
     * Initializes the countryCapitals with the specified map of country names to CapitalSheet objects.
     *
     * @param cCaps The initial map of country names to CapitalSheet objects.
     */
    public Countries(HashMap<String,CapitalSheet> cCaps)
    {
        this.setCountryCapitals(cCaps);
    }
    
    // Getters & Setters
    /**
     * Get the map of country names to CapitalSheet objects.
     *
     * @return  The map of country names to CapitalSheet objects.
     */
    public HashMap<String,CapitalSheet> getCountryCapitals()
    {
        HashMap<String,CapitalSheet> res = new HashMap<>();
        for(String ct : this.countryCapitals.keySet()) {
            res.put(ct,this.countryCapitals.get(ct).clone());
        }
        return res;
    }

    /**
     * Set the map of country names to CapitalSheet objects.
     *
     * @param cCaps The new map of country names to CapitalSheet objects.
     */
    public void setCountryCapitals(HashMap<String,CapitalSheet> cCaps)
    {
        this.countryCapitals = new HashMap<>();
        for(String ct : cCaps.keySet()) {
            this.countryCapitals.put(ct,cCaps.get(ct).clone());
        }
    }
    
    // Instance methods
    /**
     * Determine the total number of countries.
     *
     * @return  The total number of countries.
     */
    public int getTotalCountries() { return this.countryCapitals.size(); }
    
    /**
     * Return the names of countries with capitals with populations above a given value.
     *
     * @param popThresh The population threshold.
     * @return          A set of country names with capitals having populations above the threshold.
     */
    public Set<String> countriesPopulationOver(int popThresh)
    {
        Set<String> res = new HashSet<String>();
        /* Same as the for each in get or set, but this one takes the pair <String,CapitalSheet> in one go.
         * This entrySet() returns the references to the HashMap, so editions affect both ways */
        for(HashMap.Entry<String, CapitalSheet> entry : this.countryCapitals.entrySet()) {
            if(entry.getValue().getPopul() > popThresh) {
                res.add(entry.getKey());    // String doesn't need be cloned.
            }
        }
        return res;
    }
    
    /**
     * Given the name of a country, return a complete form of its capital.
     *
     * @param name  The name of the country.
     * @return      A copy of the CapitalSheet object for the given country.
     */
    public CapitalSheet getCapital(String name) { return this.countryCapitals.get(name).clone(); }

    /**
     * Change the population of the capital of a given country.
     *
     * @param name      The name of the country.
     * @param newPop    The new population for the capital.
     */
    public void updateCapitalPopulation(String name, int newPop)
    {
        CapitalSheet aux;
        if(this.countryCapitals.containsKey(name)) {
            aux = this.countryCapitals.get(name);
            aux.setPopul(newPop);
        }
    }
    
    /**
     * Insert information about a new country.
     *
     * @param name  The name of the new country.
     * @param cap   The CapitalSheet object containing information about the capital.
     */
    public void addCountry(String name, CapitalSheet cap)
    {
        this.countryCapitals.put(name, cap.clone());
    }
    
    /**
     * Create a list with the names of all registered capitals.
     *
     * @return  A set of all capital names.
     */
    public List<String> getCapitalNames()
    {
        ArrayList<String> res = new ArrayList<>();
        for(CapitalSheet cs : this.countryCapitals.values()) {
            res.add(cs.getName());
        }
        return res;
    }
    
    /**
     * Determine the sum of all capital populations.
     *
     * @return  The sum of all capital populations.
     */
    public int getTotalPopulation()
    {
        int sum = 0;
        for(CapitalSheet cs : this.countryCapitals.values()) {
            sum += cs.getPopul();
        }
        return sum;
    }
    
    /**
     * Given a Map of country name for CapitalSheet, for each country that exists in the list of countries,
     * change its capital form, and for each new country, insert its information.
     *
     * @param cCaps The update map of country names to CapitalSheet objects.
     */
    public void updateCountryCapitals(Map<String,CapitalSheet> cCaps)
    {
        for(Map.Entry<String,CapitalSheet> entry : cCaps.entrySet()) {
            if(this.countryCapitals.containsKey(entry.getKey())) {
                // Update existing country
                this.countryCapitals.put(entry.getKey(), entry.getValue().clone());
            } else {
                // Insert information about a new country
                this.countryCapitals.put(entry.getKey(), entry.getValue().clone());
            }
        }
    }
    
    /**
     * Given a set of country names, remove their capital entries.
     *
     * @param toRem The set of country names to be removed.
     */
    public void removeCapitals(Set<String> toRem)
    {
        for(String name : toRem) {
            this.countryCapitals.remove(name);
        }
    }
    
    // Complementary methods
    /**
     * Check if two Countries objects are equal based on their attributes.
     * Two Countries objects are considered equal if they have the same countryCapitals.
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

        Countries cs = (Countries) o;
        return countryCapitals.equals(cs.getCountryCapitals());
        // TODO: Does this Map equals actually checks all the values inside?
    }

    /**
     * Returns a string representation of the Countries object.
     *
     * @return  A string representation of the Countries object.
     */
    public String toString()
    {
        return this.getCountryCapitals().toString();
    }

    /**
     * Creates a new Countries object that is a deep copy of this Countries object.
     *
     * @return  A new Countries object with the same attributes as the original.
     */
    public Countries clone() { return new Countries(this); }
}
