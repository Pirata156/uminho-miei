import java.util.HashMap;   // HashMap class
import java.util.List;      // List interface
import java.util.ArrayList; // ArrayList class - to suplement List

/**
 * The Company class represents a company and its stock of products in a warehouse.
 * It manages the company's name and the stock of products associated with unique codes.
 *
 * @author PIRATA
 * @version 2013.03.23
 */
public class Company
{
    // Instance variables
    private String name;                    // Company's name.
    private HashMap<String,Product> stock;  // Stock <Code,Product>

    // Constructors
    /**
     * Default constructor for the Company class.
     * Initializes the company with default values.
     */
    public Company()
    {
        this.name = "UNSET";
        this.stock = new HashMap<String,Product>();
    }
    
    /**
     * Copy constructor for the Company class.
     * Creates a new company with the same attributes as the given company.
     *
     * @param c The company to be copied.
     */
    public Company(Company c)
    {
        this.name = c.getName();
        this.stock = c.getStock();  // getStock() already makes a deep copy!
    }
    
    /**
     * Parameterized constructor for the Company class.
     * Initializes the company with the specified name and stock.
     *
     * @param nm    The name of the company.
     * @param st    The initial stock of the company.
     */
    public Company(String nm, HashMap<String,Product> st)
    {
        this.setName(nm);
        this.setStock(st);
    }

    // Getters & Setters
    /**
     * Get the name of the company.
     *
     * @return  The name of the company.
     */
    public String getName() { return this.name; }
    
    /**
     * Sets the name of the company.
     *
     * @param nm    The new name for the company.
     */
    public void setName(String nm) { this.name = nm; }
    
    /**
     * Get a deep copy of the stock of products associated with unique codes.
     *
     * @return  A deep copy of the stock of products.
     */
    public HashMap<String,Product> getStock()
    {
        HashMap<String,Product> res = new HashMap<>();
        for(String cod : this.stock.keySet()) {
            res.put(cod,this.stock.get(cod).clone());
        }
        return res;
    }
    
    /**
     * Sets the stock of products associated with unique codes.
     *
     * @param st    The new stock of products.
     */
    public void setStock(HashMap<String,Product> st)
    {
        this.stock = new HashMap<>();
        
        for(String cod : st.keySet()) {
            this.stock.put(cod,st.get(cod).clone());
        }
    }
    
    // Instance methods
    /**
     * Retrieves a list of all product codes in the company's stock.
     *
     * @return  A list of all product codes.
     */
    public List<String> getCodes() { return new ArrayList<String>(this.stock.keySet()); }
    
    /**
     * Adds a new product to the company's stock.
     * If the product code already exists, it overwrites the existing product.
     *
     * @param prod  The product to be added.
     */
    public void addProduct(Product prod) { this.stock.put(prod.getCode(), prod.clone()); }
    
    /**
     * Removes a product with the specified code from the company's stock.
     *
     * @param code  The code of the product to be removed.
     * @return      The removed product, or null if the code doesn't exist in the stock.
     */
    public Product remove(String code) { return this.stock.remove(code); }
    
    /**
     * Modifies the quantity of a product in the company's stock onto the specified amount.
     *
     * @param code  The code of the product.
     * @param quant The amount by which to modify the quantity.
     */
    public void modifyQuantity(String code, int quant)
    {
        if(this.stock.containsKey(code)) {
            this.stock.get(code).setStock(quant);
        }
    }
    
    /**
     * Calculates and returns the total quantity of all products in the company's stock.
     *
     * @return  The total quantity of all products in the stock.
     */
    public int totalStock()
    {
        int res = 0;
        for(Product prod : this.stock.values()) {
            res += prod.getStock();
        }
        return res;
    }
    
    /**
     * Checks if the company's stock contains a product with the specified code.
     *
     * @param code  The code of the product.
     * @return      true if the code exists in the stock, false otherwise.
     */
    public boolean containsCode(String code) { return this.stock.containsKey(code); }
    
    /**
     * Retrieves a list of product codes for products with quantities below or equal to their minimum alert levels.
     *
     * @return  A list of product codes with low quantities.
     */
    public List<String> alarmCodes()
    {
        List<String> res = new ArrayList<>();   // TODO: Understand better this dependancy/interface stuff.
        for(Product prod : this.stock.values()) {
            if(prod.getStock() <= prod.getMinAlarm()) {
                res.add(prod.getCode());
            }
        }
        return res;
    }
    
    /**
     * Retrieves a deep copy of the product information associated with the specified code.
     *
     * @param code  The code of the product.
     * @return      A deep copy of the product information.
     */
    public Product productInfo(String code) { return this.stock.get(code).clone(); }
    
    // Complementary methods
    /**
     * Check if two Company objects are equal based on their attributes.
     * Two companies are considered equal if they have the same name and stock of products.
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
        else {
            Company c = (Company) o;
            return (this.name.equals(c.getName()) && this.stock.equals(c.getStock()));
        }
    }
    
    /**
     * Returns a string representation of the company, including its stock of products.
     *
     * @return  A string representation of the company.
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer("Company{");
        for (Product prod : this.stock.values()) {
            sb.append(prod.toString());
            sb.append("; ");
        }
        sb.setLength(sb.length() - 2); // Remove the trailing comma and space
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Creates and returns a deep copy of this company.
     *
     * @return  A new company with the same attributes as this company.
     */
    public Company clone() { return new Company(this); }
}
