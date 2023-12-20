
/**
 * Represents an information sheet for a country.
 * This class includes details about the name, continent, and population of a country.
 *
 * @author PIRATA
 * @version 2012.04.21
 */
public class CountrySheet
{
    // Instance variables
    private String name;        // The name of the country.
    private String continent;   // The continent of the country.
    private double population;  // The population of the country in millions.

    /**
     * Default constructor for objects of class CountrySheet.
     * Initializes the country as "UNDEFINED", the continent as "UNSET" and population at 0.
     */
    public CountrySheet()
    {
        this.name = "UNDEFINED";
        this.continent = "UNSET";
        this.population = 0;
    }
    
    /**
     * Constructs a new CountrySheet by copying the attributes of the given CountrySheet.
     * 
     * @param other The CountrySheet to copy the data from.
     */
    public CountrySheet(CountrySheet other)
    {
        this.name = other.getName();
        this.continent = other.getCont();
        this.population = other.getPop();
    }
    
    /**
     * Constructs a new CountrySheet using all the attributes.
     * 
     * @param aName The name of the country.
     * @param cont  The continent of the country.
     * @param pop   The population of the country (in millions).
     */
    public CountrySheet(String aName, String cont, double pop)
    {
        this.setName(aName);
        this.setCont(cont);
        this.setPop(pop);
    }
    
    // Getters & Setters
    /**
     * Get the name of the country.
     *
     * @return  The name of the country.
     */
    public String getName() { return this.name; }
    
    /**
     * Get the continent of the country.
     *
     * @return  The continent of the country.
     */
    public String getCont() { return this.continent; }
    
    /**
     * Get the population of the country (in millions).
     *
     * @return  The population of the country.
     */
    public double getPop() { return this.population; }

    /**
     * Set the name of the country, validating that it is a valid name.
     *
     * @param aName The name to set for the country.
     * @throws IllegalArgumentException if the name is invalid.
     */
    public void setName(String aName)
    {
        if(isValidName(aName)) {
            this.name = aName.trim();
        } else {
            throw new IllegalArgumentException("Attribute is an invalid country name");
        }
    }
    
    /**
     * Set the continent of the country, validating that it is a valid continent name.
     *
     * @param cont  The continent to set for the country.
     * @throws IllegalArgumentException if the continent is invalid.
     */
    public void setCont(String cont)
    {
        if(isValidName(cont)) {
            this.continent = cont.trim();
        } else {
            throw new IllegalArgumentException("Attribute is an invalid continent name");
        }
    }
    
    /**
     * Set the population of the country, assuming it can be close to 0 millions.
     *
     * @param pop   The population to set for the country.
     * @throws IllegalArgumentException if the population is negative.
     */
    public void setPop(double pop)
    {   // Assuming it can actually have values close to 0 millions
        if(pop >= 0) {
            this.population = pop;
        } else {
            throw new IllegalArgumentException("Attribute must be non-negative");
        }
    }
    
    // Instance methods
    /**
     * Check if a given string is a valid name for a country or continent.
     * A valid name is not null, not an empty string, and at least 2 characters long.
     * Additionally, the characters must be valid (letters, whitespaces, apostrophes, hyphens, or dots).
     *
     * @param str   The string to check for validity.
     * @return      true if the string is a valid name, false otherwise.
     */
    private boolean isValidName(String str)
    {
        boolean flag = true;
        // Check its not null, not empty string and at least 2 chars long.
        if(str == null || str.trim().isEmpty() || str.trim().length() < 2) {
            flag = false;
        } else {    // Checks that the characters are valid ones.
            for(char c : str.trim().toCharArray()) {
                if(!(Character.isLetter(c) || Character.isWhitespace(c) || c == '\'' || c == '-' || c == '.')) {
                    flag = false;
                }
            }
        }
        return flag;
    }
    
    // Complementary methods
    /**
     * Check if two CountrySheet objects are equal based on their name and continent.
     *
     * @param o The object to compare to.
     * @return  true if the CountrySheets are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        // It's the same CountrySheet if the country's name and continent is the same.
        CountrySheet cs = (CountrySheet) o;
        return (this.getName().equals(cs.getName()) && this.getCont().equals(cs.getCont()));
    }
    
    /**
     * Returns a string representation of the CountrySheet in the format "CountrySheet{Country:"__", Continent:"__", Population:__}".
     * 
     * @return  A string representation of the CountrySheet.
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer("CountrySheet{");
        sb.append("Country:\"" + this.getName() + "\"");
        sb.append(", Continent:\"" + this.getCont() + "\"");
        sb.append(", Population:" + this.getPop() + "}");
        return sb.toString();
    }
    
    /**
     * Creates a new CountrySheet object that is a deep copy of this CountrySheet.
     * 
     * @return  A new CountrySheet object with the same attributes as the original.
     */
    public CountrySheet clone() { return new CountrySheet(this); }
}
