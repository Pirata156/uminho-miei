
/**
 * Represents a pixel with integer x and y coordinates associated with a color value ranging from 0 to 255.
 *
 * A pixel is a fundamental element of an image, defined by its position in a 2D space with x and y coordinates,
 * along with its color value represented as an integer within the range of 0 to 255.
 *
 * @author (PIRATA)
 * @version (2012.05.23)
 */
public class Pixel
{
    // Instance variables - Must be private - Encapsulation
    private int x;      // X coordinate
    private int y;      // Y coordinate
    private int color;  // Pixel color [0..255]

    // Default constructor - Mandatory
    /**
     * Default constructor for objects of class Pixel.
     * Initializes the Pixel with the color white (0) at point (0;0).
     */
    public Pixel()
    {
        // Initialise instance variables
        this.x = 0;
        this.y = 0;
        this.color = 0;
    }
    
    // Copy constructor - Mandatory
    /**
     * Constructs a new Pixel by copying the attributes of the given Pixel.
     * 
     * @param p The Pixel to copy the data from.
     */
    public Pixel(Pixel p)
    {   // By using the getters, it keeps the encapsulation. (Using the values is slightly faster though!)
        this.x = p.getX();
        this.y = p.getY();
        this.color = p.getColor();
    }
    
    // Custom constructors
    /**
     * Constructs a new Pixel with the specified x and y coordinates and color value.
     * If the color value is out of bounds [0;255], it's adjusted to the nearest valid value.
     * 
     * @param x The x-coordinate of the Pixel.
     * @param y The y-coordinate of the Pixel.
     * @param c The color value of the Pixel (ranging from 0 to 255).
     */
    public Pixel(int x, int y, int c)
    {
        this.x = x;
        this.y = y;
        // Forcing an adjust on the color in case its out of bounds [0;255]
        if(c < 0) {
            this.color = 0;
        } else if(c > 255) {
            this.color = 255;
        } else {
            this.color = c;
        }
    }
    
    // Getters & Setters - Mandatory for each instance variable - Encapsulation
    /**
     * Get the x-coordinate of the Pixel.
     * 
     * @return  The x-coordinate of the Pixel.
     */
    public int getX() { return this.x; }
    
    /**
     * Get the y-coordinate of the Pixel.
     * 
     * @return  The y-coordinate of the Pixel.
     */
    public int getY() { return this.y; }
    
    /**
     * Get the color value of the Pixel.
     * 
     * @return  The color value of the Pixel.
     */
    public int getColor() { return this.color; }
    
    /**
     * Set the x-coordinate of the Pixel.
     * 
     * @param newX  The new x-coordinate to set.
     */
    public void setX(int newX) { this.x = newX; }
    
    /**
     * Set the y-coordinate of the Pixel.
     * 
     * @param newY  The new y-coordinate to set.
     */
    public void setY(int newY) { this.y = newY; }
    
    /**
     * Set the color value of the Pixel.
     * 
     * @param newColor  The new color value to set (ranging from 0 to 255).
     */
    private void setColor(int newColor) { this.color = newColor; }
    // There's the public recolor method. This setter has no control of the value passed.
    
    // Complementary methods - equals, clone, toString - Mandatory when not too complex
    
    /**
     * Check if two Pixel objects are equal based on their attributes.
     * 
     * @param o The object to compare to.
     * @return  true if the objects are equal; false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)   // Checking if they are the same object
            return true;
        if((o == null) || (this.getClass() != o.getClass()))    // Checking if the object is Null or different classes
            return false;
        // Casting Object to Pixel and compares the 2 pixels - by whatever rules we decide on
        Pixel p = (Pixel) o;
        return ((this.getX() == p.getX()) &&
                (this.getY() == p.getY()) &&
                (this.getColor() == p.getColor()));
    }
    
    /**
     * Returns a string representation of the Pixel in the format "Pixel [X = x; Y = y; Color = color]".
     * 
     * @return  A string representation of the Pixel.
     */
    public String toString()
    {
        String res;
        StringBuilder s = new StringBuilder("Pixel [");
        s.append("X = " + this.getX() + "; ");
        s.append("Y = " + this.getY() + "; ");
        s.append("Color = " + this.getColor() + "]");
        res = s.toString();
        return res;
    }
    
    /**
     * Creates a new Pixel by copying the attributes of this Pixel.
     * 
     * @return  A new Pixel object with the same attributes as the original.
     */
    public Pixel clone() { return new Pixel(this); }
    
    // Instance methods

    //Move up, down, left and right of a real value - Assumes value 0 or bigger
    /**
     * Move the Pixel up by the specified distance.
     * 
     * @param d The distance to move up.
     */
    public void goUp(int d) { this.y += d; }
    
    /**
     * Move the Pixel down by the specified distance.
     * 
     * @param d The distance to move down.
     */
    public void goDown(int d) { this.y -= d; }
    
    /**
     * Move the Pixel left by the specified distance.
     * 
     * @param d The distance to move left.
     */
    public void goLeft(int d) { this.x -= d; }
    
    /**
     * Move the Pixel right by the specified distance.
     * 
     * @param d The distance to move right.
     */
    public void goRight(int d) { this.x += d; }
    
    /**
     * Change the color of the Pixel to a new color value (between 0 and 255).
     * 
     * @param c The new color value to set.
     * @return  true if the color change was successful; false if the provided color is out of bounds.
     */
    public boolean recolor(int c)
    {
        // Change its color to a new color of given number (between 0 and 255)
        boolean flag = false;
        if(c >= 0 && c <= 255) {
            this.setColor(c);
            flag = true;
        }
        return flag;
    }
    
    /**
     * Get the name of the color represented by the Pixel's color value.
     * 
     * @return  The name of the color (e.g., "White," "Gray," "Blue," etc.).
     */
    public String toColorName()
    {
        String[] colorNames = {"White", "Gray", "Blue", "Green", "Red", "Yellow", "Black"};
        int[] limits = {2, 52, 102, 152, 202, 252, 255};
        int i = 0, tc = this.getColor();
        
        while((i < limits.length) && (tc > limits[i])) {
            i++;
        }
        
        return colorNames[i];
    }
}
