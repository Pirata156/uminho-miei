import java.util.ArrayList;

/**
 * Represents a list of CountrySheet objects. This class allows you to create lists of CountrySheet in
 * any order and provides various methods for managing and retrieving information about the countries.
 *
 * @author PIRATA
 * @version 2012.04.21
 */
public class CountryList
{
    // Instance variables
    private ArrayList<CountrySheet> countries;

    /**
     * Default constructor for objects of class CountryList.
     * Initializes the list of countries as an empty ArrayList.
     */
    public CountryList()
    {
        this.countries = new ArrayList<CountrySheet>();
    }
    
    /**
     * Constructs a new CountryList by copying the attributes of the given CountryList.
     * 
     * @param cl    The CountryList to copy the data from.
     */
    public CountryList(CountryList cl)
    {
        this.countries = cl.getCountries(); // Already makes a deep copy of the list.
    }
    
    /**
     * Constructs a new CountryList with the list of CountrySheet.
     * 
     * @param lst   The list of CountrySheet as an ArrayList<CountrySheet>.
     */
    public CountryList(ArrayList<CountrySheet> lst)
    {
        this.setCountries(lst);
    }
    
    // Getters & Setters
    /**
     * Get the list of CountrySheet of the CountryList as an ArrayList.
     * 
     * @return  The list of CountrySheet as a deep cloned ArrayList object.
     */
    public ArrayList<CountrySheet> getCountries()
    {
        ArrayList<CountrySheet> res = new ArrayList<>(this.countries.size());
        for(CountrySheet cs : this.countries) {
            res.add(cs.clone());
        }
        return res;
    }
    
    /**
     * Set the list of CountrySheet of the CountryList. Only the non repeating elements get cloned in.
     * 
     * @param cts   The list of CountrySheet to be stored.
     */
    public void setCountries(ArrayList<CountrySheet> cts)
    {   // Checks that the countries being inserted aren't repeated. If there's repeated, they are skipped.
        this.countries = new ArrayList<>(cts.size());
        for(CountrySheet cs : cts) {
            if(!this.countries.contains(cs)) {
                this.countries.add(cs.clone());
            }
        }
    }
    
    // Instance methods
    /**
     * Determine the total number of countries in the list.
     *
     * @return  The total number of countries.
     */
    public int numOfCountries()
    {
        return this.countries.size();
    }
    
    /**
     * Determine the number of countries on a given continent.
     *
     * @param cont  The continent to count countries for.
     * @return      The number of countries on the given continent.
     */
    public int numCountriesOnCont(String cont)
    {
        int count = 0;
        for(CountrySheet ct : this.countries) {
            if(ct.getCont().equals(cont)) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Given the name of a country, return its complete form, if any.
     *
     * @param cName The name of the country to search for.
     * @return      The CountrySheet object if found, otherwise null.
     */
    public CountrySheet getCountrySheet(String cName)
    {
        CountrySheet res = null;
        int i;
        for(i = 0; (i < this.countries.size()) && (res == null); i++) {
            if(this.countries.get(i).getName().equals(cName)) {
                res = this.countries.get(i).clone();    // Deep clone
            }
        }
        return res;
    }
    
    /**
     * Create a list with the names of countries with a population greater than a given value.
     *
     * @param popThresh The population threshold to filter countries.
     * @return          A list of country names with a population greater than the threshold.
     */
    public ArrayList<String> getCountriesGreaterPop(double popThresh) {
        ArrayList<String> res = new ArrayList<>();
        for(CountrySheet ct : this.getCountries()) {
            if(ct.getPop() > popThresh) {
                res.add(ct.getName());
            }
        }
        return res;
    }
    
    /**
     * Determine the list with the names of the continents of countries with a population greater than a given value.
     *
     * @param popThresh The population threshold to filter countries.
     * @return          A list of continent names with countries having a population greater than the threshold.
     */
    public ArrayList<String> getContsGreaterPop(double popThresh) {
        ArrayList<String> res = new ArrayList<>();
        // Could use the previous function but seems useless cycles at the end
        for(CountrySheet cs : this.getCountries()) {
            if(cs.getPop() > popThresh) {
                if(!res.contains(cs.getCont())) {
                    res.add(cs.getCont());
                }
            }
        }
        return res;
    }
    
    /**
     * Determine the sum of populations on a given continent.
     *
     * @param cont  The continent to calculate the sum of populations for.
     * @return      The sum of populations on the given continent.
     */
    public double popContinent(String cont)
    {
        double sum = 0;
        for(CountrySheet cs : this.getCountries()) {
            if(cs.getCont().equals(cont)) {
                sum += cs.getPop();
            }
        }
        return sum;
    }
    
    /**
     * Given a list of CountrySheet, for each country that exists in the list of countries,
     * change its population with the value in the card; if it does not exist, insert the form in the list.
     *
     * @param update    A list of CountrySheet to update or insert into the current list.
     */
    public void updateCountries(ArrayList<CountrySheet> update)
    {
        int index;
        for(CountrySheet cs : update) {
            index = this.countries.indexOf(cs);
            if(index == -1) {
                // Country doesn't exist.
                this.countries.add(cs.clone());
            } else {
                // Country exists - Update
                this.countries.get(index).setPop(cs.getPop());
                // It could also be: this.countries.set(index, cs.clone()); to change the whole CountrySheet
            }
        }
    }
    
    /**
     * Given a list of country names, remove their entries from the list.
     *
     * @param remList   The list of country names to remove.
     */
    public void removeCountries(ArrayList<String> remList)
    {
        int i, j;
        boolean found;
        
        for(i = 0; i < remList.size(); i++) {
            found = false;
            for(j = 0; j < this.countries.size() && !found; j++) {
                if(this.countries.get(j).getName().equals(remList.get(i))) {
                    found = true;
                    this.countries.remove(j);
                    // or this.countries.remove(this.countries.get(j); which sounds redundant.
                }
            }
        }
        /* ArrayList also has a neat method called: removeIf which would result in the method beind:
           this.countries.removeIf(country -> remList.contains(country.getName())); 
           but we haven't been "taught" about predicates and such*/
    }
    
    // Complementary methods
    /**
     * Check if two CountryList objects are equal based on their attributes.
     *
     * @param o The object to compare to.
     * @return  true if the CountryLists are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass())) 
            return false;       
        else {
            CountryList cl = (CountryList) o;
            return (this.countries.equals(cl.getCountries()));   // Compares elements and their order.
        }
    }
    
    /**
     * Returns a string representation of the CountryList in the format "CountryList [Country { ... }, ... ]".
     * 
     * @return  A string representation of the CountryList.
     */
    public String toString()
    {
        int i;
        StringBuffer sb = new StringBuffer("CountryList [");
        
        for(i = 0; i < this.countries.size() - 1; i++) {
            sb.append(this.countries.get(i).toString());
            if(i + 1 < this.countries.size()) {
                sb.append("; ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Creates a new CountryList object that is a deep copy of this CountryList.
     * 
     * @return  A new CountryList object with the same attributes as the original.
     */
    public CountryList clone()
    {
        return new CountryList(this);
    }
}
