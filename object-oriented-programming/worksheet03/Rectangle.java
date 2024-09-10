
/**
 * The Rectangle class represents a rectangle in a 2D plane defined by two corner points.
 *
 * The class provides methods to calculate the base length, height length, diagonal length, perimeter, area of the rectangle
 * and it allows moving the rectangle by a specified amount in the x and y directions.
 *
 * @author (PIRATA)
 * @version (2012.06.04)
 */
public class Rectangle
{
    // Instance variables
    private Ponto2D extreme1;   // Coordinates of one corner
    private Ponto2D extreme2;   // Coordinates of the opposite corner

    /**
     * Default constructor for objects of class Rectangle.
     * Initializes both Rectangle corners with the default Ponto2D default constructor - at (0,0).
     */
    public Rectangle()
    {
        this.extreme1 = new Ponto2D();
        this.extreme2 = new Ponto2D();
    }
    
    /**
     * Constructs a new Rectangle by copying the attributes of the given Rectangle.
     * 
     * @param r The Rectangle to copy the data from.
     */
    public Rectangle(Rectangle r)
    {
        this.extreme1 = r.getC1();  // Clones the corner
        this.extreme2 = r.getC2();  // Clones the corner
    }
    
    /**
     * Constructor for creating a new Rectangle with specified attributes.
     *
     * @param c1    One of the extreme corners of the Rectangle.
     * @param c2    The diagonally opposed corner of the Rectangle.
     */
    Rectangle(Ponto2D c1, Ponto2D c2)
    {
        this.extreme1 = c1.clone();
        this.extreme2 = c2.clone();
    }
    
    // Getters & Setters
    /**
     * Get the coordinates of the first corner of the Rectangle.
     *
     * @return  A copy of the Ponto2D object representing the first corner.
     */
    public Ponto2D getC1() { return this.extreme1.clone(); }
    
    /**
     * Get the coordinates of the second corner of the Rectangle.
     *
     * @return  A copy of the Ponto2D object representing the second corner.
     */
    public Ponto2D getC2() { return this.extreme2.clone(); }
    
    /**
     * Set the coordinates of the first corner of the Rectangle.
     *
     * @param p The Ponto2D object representing the first corner.
     */
    private void setC1(Ponto2D p) { this.extreme1 = p.clone(); }
    
    /**
     * Set the coordinates of the second corner of the Rectangle.
     *
     * @param p The Ponto2D object representing the second corner.
     */
    private void setC2(Ponto2D p) { this.extreme2 = p.clone(); }

    // Instance methods
    /**
     * Calculate the base length of the rectangle.
     *
     * @return  The length of the base.
     */
    public double baseLength()
    {
        double res;
        res = Math.abs(this.getC2().getX() - this.getC1().getX());
        return res;
    }
    
    /**
     * Calculate the height length of the rectangle.
     *
     * @return  The length of the height.
     */
    public double heightLength()
    {
        double res;
        res = Math.abs(this.getC2().getY() - this.getC1().getY());
        return res;
    }
    
    /**
     * Calculate the diagonal length of the rectangle.
     *
     * @return  The length of the diagonal.
     */
    public double diagonalLength()
    {
        double res;
        res = Math.sqrt(Math.pow(this.heightLength(),2.0) + Math.pow(this.baseLength(),2.0));
        return res;
    }
    
    /**
     * Calculate the perimeter of the rectangle.
     *
     * @return  The perimeter of the rectangle.
     */
    public double perimeter()
    {
        double res, b, h;
        b = this.baseLength();
        h = this.heightLength();
        res = b * 2 + h * 2;
        return res;
    }
    
    /**
     * Calculate the area of the rectangle.
     *
     * @return  The area of the rectangle.
     */
    public double area()
    {
        double res, b, h;
        b = this.baseLength();
        h = this.heightLength();
        res = b * h;
        return res;
    }
    
    /**
     * Move the rectangle by a specified amount in the x and y directions.
     *
     * @param dx    The displacement in the XX direction.
     * @param dy    The displacement in the YY direction.
     */
    public void move(double dx, double dy)
    {
        this.extreme1.incCoord(dx,dy);
        this.extreme2.incCoord(dx,dy);
        // Bigger projects, for maintainability, might require to get, change and set the instance variables instead!
    }
    
    // Complementary methods
    /**
     * Check if two Rectangle objects are equal based on their attributes.
     * It compares both points, even if they are in inversed order.
     *
     * @param o The object to compare to.
     * @return  true if the rectangles are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass()))
            return false;
        Rectangle r = (Rectangle) o;
        return (((this.getC1().equals(r.getC1())) && (this.getC2().equals(r.getC2()))) ||
                ((this.getC2().equals(r.getC1())) && (this.getC1().equals(r.getC2()))));
        // So, we could compare the other inexistente corners, but after asking seems unrelated to the exercise!
    }
    
    /**
     * Returns a string representation of the Rectangle in the format
     * "Rectangle {C1:(XX,YY), C2:(XX,YY)}".
     * 
     * @return  A string representation of the Rectangle.
     */
    public String toString()
    {
        StringBuilder s = new StringBuilder("Rectangle {");
        s.append("C1:" + this.getC1().toString());
        s.append(", C2:" + this.getC2().toString() + "}");
        return s.toString();
    }
    
    /**
     * Creates a new Rectangle object that is a deep copy of this Rectangle.
     * 
     * @return  A new Rectangle object with the same attributes as the original.
     */
    public Rectangle clone() { return new Rectangle(this); }
}
