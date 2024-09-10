
/**
 * The Product class represents information about a product in a company's warehouse.
 * It includes details such as code, name, quantity in stock, and minimum alert level.
 * Instances of this class can be used to manage and manipulate product data.
 *
 * @author PIRATA
 * @version 2013.03.23
 */
public class Product
{
    // Instance variables
    private String code;    // Code of the product
    private String name;    // Description of the product
    private int quantity;   // Quantity of the product in stock
    private int alarm;      // Alert minimum - minimum value aceptable in stock

    // Constructors
    /**
     * Default constructor for the Product class.
     * Initializes the product with default values.
     */
    public Product()
    {
        this.code = "UNSET";
        this.name = "UNDEFINED";
        this.quantity = 0;
        this.alarm = 0;
    }
    
    /**
     * Copy constructor for the Product class.
     * Creates a new product with the same attributes as the given product.
     *
     * @param p The product to be copied.
     */
    public Product(Product p)
    {
        this.code = p.getCode();
        this.name = p.getName();
        this.quantity = p.getStock();
        this.alarm = p.getMinAlarm();
    }
    
    /**
     * Parameterized constructor for the Product class.
     * Initializes the product with the specified code, name, quantity, and minimum alert level.
     *
     * @param cd    The code of the product.
     * @param nm    The name or description of the product.
     * @param qtt   The initial quantity of the product in stock.
     * @param ma    The minimum alert level for the product in stock.
     */
    public Product(String cd, String nm, int qtt, int ma)
    {
        this.setCode(cd);
        this.setName(nm);
        this.setStock(qtt);
        this.setMinAlarm(ma);
    }
    
    // Getters & Setters
    /**
     * Get the code of the product.
     *
     * @return  The code of the product.
     */
    public String getCode() { return this.code; }
    
    /**
     * Get the name or description of the product.
     *
     * @return  The name or description of the product.
     */
    public String getName() { return this.name; }
    
    /**
     * Get the quantity of the product in stock.
     *
     * @return  The quantity of the product in stock.
     */
    public int getStock() { return this.quantity; }
    
    /**
     * Get the minimum alert level for the product.
     *
     * @return  The minimum alert level for the product.
     */
    public int getMinAlarm() { return this.alarm; }
    
    /**
     * Sets the code of the product.
     *
     * @param cd    The new code for the product.
     */
    public void setCode(String cd) { this.code = cd; }
    
    /**
     * Sets the name or description of the product.
     *
     * @param nm    The new name or description for the product.
     */
    public void setName(String nm) { this.name = nm; }
    
    /**
     * Sets the quantity of the product in stock.
     *
     * @param qt    The new quantity for the product in stock.
     */
    public void setStock(int qt) { this.quantity = qt; }
    
    /**
     * Sets the minimum alert level for the product.
     *
     * @param ma    The new minimum alert level for the product.
     */
    public void setMinAlarm(int ma) { this.alarm = ma; }

    // Complementary methods
    /**
     * Check if two Product objects are equal based on their attributes.
     * Two products are considered equal if they have the same name, code, quantity, and minimum alert level.
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
            Product p = (Product) o;
            return(this.name.equals(p.getName()) && this.code.equals(p.getCode()) &&
                    this.quantity == p.getStock() && this.alarm == p.getMinAlarm());
        }
    }
    
    /**
     * Returns a string representation of the Product in the format "Product{Code: ... }".
     * 
     * @return  A string representation of the Product.
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer("Product{");
        sb.append("Code:" + this.getCode() + ";");
        sb.append("Name:" + this.getName() + ";");
        sb.append("Stock:" + this.getStock() + ";");
        sb.append("Alarm:" + this.getMinAlarm() + "}");
        return sb.toString();
    }
    
    /**
     * Creates a new Product object that is a deep copy of this Product.
     * 
     * @return  A new Product object with the same attributes as the original.
     */
    public Product clone() { return new Product(this); }
    
    /**
     * Returns a hash code value for the product.
     *
     * @return  A hash code value for the product.
     */
    public int hashCode() { return this.toString().hashCode(); }
}
