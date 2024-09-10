
/**
 * The Product class represents a product in a stock of products.
 * It contains information such as code, name, quantity in stock, minimum quantity,
 * purchase price, and retail price.
 * 
 * @author (PIRATA)
 * @version (2012.03.26)
 */
public class Product
{
    // Instance variables
    private String code;        // The unique code identifying the product.
    private String name;        // The name of the product.
    private int quantStock;     // The quantity of this product currently in stock.
    private int minStock;       // The minimum quantity required to maintain in stock.
    private float sellPrice;    // The retail price of the product.
    private float buyPrice;     // The purchase price of the product.

    // Constructors
    /**
     * Default constructor for objects of class <classname>.
     * Initializes the code as "UNSET" and name as "UNINDENTIFIED" and all other values at 0.
     */
    public Product()
    {
        this.code = "UNSET";
        this.name = "UNIDENTIFIED";
        this.quantStock = 0;
        this.minStock = 0;
        this.sellPrice = 0;
        this.buyPrice = 0;
    }

    /**
     * Constructs a new Product by copying the attributes of the given Product.
     * 
     * @param p The Product to copy the data from.
     */
    public Product(Product p)
    {
        this.code = p.getCode();
        this.name = p.getName();
        this.quantStock = p.getQtStock();
        this.minStock = p.getQtMin();
        this.sellPrice = p.getSellPrice();
        this.buyPrice = p.getBuyPrice();
    }
    
    /**
     * Constructs a new Product using specified attributes.
     * 
     * @param n     The name of the product.
     * @param c     The code of the product.
     * @param qs    The quantity in stock of the product.
     * @param ms    The value of the Product in stock that sets as low stock.
     * @param sp    The sell price per Product in stock.
     * @param bp    The purchase price of the Product.
     */
    public Product(String n, String c, int qs, int ms, float sp, float bp)
    {
        this.setName(n);
        this.setCode(c);
        this.setQtStock(qs);
        this.setQtMin(ms);
        this.setSellPrice(sp);
        this.setBuyPrice(bp);
    }
    
    // Getters & Setters
    /**
     * Get the code of the Product.
     * 
     * @return  The code of the Product.
     */
    public String getCode() { return this.code; }
    
    /**
     * Get the name of the Product.
     * 
     * @return  The name of the Product.
     */
    public String getName() { return this.name; }
    
    /**
     * Get the quantity in stock of the Product.
     * 
     * @return  The quantity in stock of the Product.
     */
    public int getQtStock() { return this.quantStock; }
    
    /**
     * Get the minimum stock quantity of the Product.
     * 
     * @return  The minimum stock quantity of the Product.
     */
    public int getQtMin() { return this.minStock; }
    
    /**
     * Get the selling price of the Product.
     * 
     * @return  The selling price of the Product.
     */
    public float getSellPrice() { return this.sellPrice; }
    
    /**
     * Get the buying price of the Product.
     * 
     * @return  The buying price of the Product.
     */
    public float getBuyPrice() { return this.buyPrice; }
    
    /**
     * Set the code of the Product.
     * 
     * @param c The code of the Product.
     */
    private void setCode(String c) { this.code = c; }
    
    /**
     * Set the name of the Product.
     * 
     * @param n The name of the Product.
     */
    private void setName(String n) { this.name = n; }
    // Add a name/string validation on bigger projects
    
    /**
     * Set the stock quantity of the Product.
     * 
     * @param qs    The stock quantity of the Product.
     */
    public void setQtStock(int qs) { this.quantStock = (qs < 0) ? 0 : qs; }
    
    /**
     * Set the minimum stock quantity of the Product.
     * 
     * @param ms    The minimum stock quantity of the Product.
     */
    public void setQtMin(int ms) { this.minStock = (ms < 0) ? 0 : ms; }
    
    /**
     * Set the selling price of the Product.
     * 
     * @param sp    The selling price of the Product.
     */
    private void setSellPrice(float sp) { this.sellPrice = (sp < 0) ? 0 : sp; }
    
    /**
     * Set the buying price of the Product.
     * 
     * @param bp    The buying price of the Product.
     */
    public void setBuyPrice(float bp) { this.buyPrice = (bp < 0) ? 0 : bp; }
    
    // Instance methods
    /**
     * Increase the stock quantity by a given amount.
     * 
     * @param qt    The amount to increase the stock quantity.
     */
    public void incStock(int qt)
    {
        if(qt > 0) {
            this.setQtStock(this.getQtStock() + qt);
        }
    }
    
    /**
     * Decrease the stock quantity by a given amount.
     * 
     * @param qt    The amount to decrease the stock quantity.
     */
    public void decStock(int qt)
    {
        if((qt > 0) && (this.getQtStock() - qt >= 0)) {
            this.setQtStock(this.getQtStock() - qt);
        }
    }
    
    /**
     * Change the name of the Product.
     * 
     * @param name  The new name for the Product.
     */
    public void changeName(String name)
    {
        this.setName(name);
    }
    
    /**
     * Change the selling price of the Product.
     * 
     * @param price The new selling price for the Product.
     */
    public void changeSellPrice(float price)
    {
        this.setSellPrice(price);
    }
    
    /**
     * Calculate the total value of the Product in stock.
     * 
     * @return  The total value of the Product in stock.
     */
    public float valueStock()
    {
        return this.getQtStock() * this.getSellPrice();
    }
    
    /**
     * Calculate the profit of the Product in stock.
     * 
     * @return  The profit of the Product in stock.
     */
    public float profitStock()
    {
        return this.getQtStock() * (this.getSellPrice() - this.getBuyPrice());
    }
    
    /**
     * Calculate the total price for a given order quantity.
     * 
     * @param qt    The quantity for the order.
     * @return      The total price for the order.
     */
    public float orderTotal(int qt)
    {
        float res;
        if(qt < 0) {
            res = 0;
        } else {
            res = this.getSellPrice() * qt;
        }
        return res;
    }
    
    /**
     * Check if the Product's stock quantity is below the minimum stock level.
     * 
     * @return  True if the stock quantity is below the minimum level, false otherwise.
     */
    public boolean lowStock()
    {
        return this.getQtStock() < getQtMin();
    }
    
    // Complementary methods
    /**
     * Check if two Product objects are equal based on their attributes.
     * It compares all the variables, not only the code.
     *
     * @param o The object to compare to.
     * @return  true if the Products are equal, false otherwise.
     */
    public boolean equals(Object o)
    {   // Comparing the code should be more than enough!
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass()))
            return false;
        Product p = (Product) o;
        return ((this.getName().equals(p.getName())) &&
                (this.getCode().equals(p.getCode())) &&
                (this.getQtStock() == p.getQtStock()) &&
                (this.getQtMin() == p.getQtMin()) &&
                (this.getBuyPrice() == p.getBuyPrice()) &&
                (this.getSellPrice() == p.getSellPrice()));
    }
    
    /**
     * Returns a string representation of the Product in the format "Product {<code>, <name>, <QuantityStock>, <MinStock>, <SellPrice>, <BuyPrice>}".
     * 
     * @return  A string representation of the Product.
     */
    public String toString()
    {
        StringBuilder s = new StringBuilder("Product {");
        s.append("\'" + this.getCode() + "\'");
        s.append(", \'" + this.getName() + "\'");
        s.append(", " + this.getQtStock());
        s.append(", " + this.getQtMin());
        s.append(", " + this.getSellPrice());
        s.append(", " + this.getBuyPrice() + "}");
        return s.toString();
    }
    
    /**
     * Creates a new Product object that is a deep copy of this Product.
     * 
     * @return  A new Product object with the same attributes as the original.
     */
    public Product clone() { return new Product(this); }
}
