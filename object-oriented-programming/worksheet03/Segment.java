
/**
 * Represents a line segment defined by two 2D points, a starting point, and an ending point.
 * This class provides methods for calculating various properties of the line segment.
 *
 * @author (PIRATA)
 * @version (2012.05.25)
 */
public class Segment
{
    // Instance variables
    private Ponto2D start;  // The starting point of the line segment.
    private Ponto2D end;    // The ending point of the line segment.

    /**
     * Default constructor for objects of class Segment.
     * Initializes the line segment with both starting and ending points at the origin (0, 0).
     */
    public Segment()
    {
        this.start = new Ponto2D();
        this.end = new Ponto2D();
    }
    
    /**
     * Constructs a new Segment by copying the attributes of the given Segment.
     * 
     * @param s The Segment to copy the data from.
     */
    public Segment(Segment s)
    {
        this.start = s.getStart();  // Copies/clones the starting point.
        this.end = s.getEnd();      // Copies/clones the ending point.
    }
    
    /**
     * Constructs a new Segment using the provided starting and ending points.
     * 
     * @param s The starting point of the line segment.
     * @param e The ending point of the line segment.
     */
    public Segment(Ponto2D s, Ponto2D e)
    {
        this.start = s.clone(); // Clones the starting point.
        this.end = s.clone();   // Clones the ending point.
    }
    
    /**
     * Constructs a new Segment using the provided coordinates for the starting and ending points.
     * 
     * @param xs    The x coordinate of the starting point.
     * @param ys    The y coordinate of the starting point.
     * @param xe    The x coordinate of the ending point.
     * @param ye    The y coordinate of the ending point.
     */
    public Segment(int xs, int ys, int xe, int ye)
    {
        this.start = new Ponto2D(xs, ys);
        this.end = new Ponto2D(xe, ye);
    }
    
    // Getters & Setters - Getters give copies for Security & Immutability
    /**
     * Get the starting point of the line segment.
     * 
     * @return  The starting point as a cloned Ponto2D object.
     */
    public Ponto2D getStart() { return this.start.clone(); }
    
    /**
     * Get the ending point of the line segment.
     * 
     * @return  The ending point as a cloned Ponto2D object.
     */
    public Ponto2D getEnd() { return this.end.clone(); }
    
    /**
     * Set the starting point of the line segment.
     * 
     * @param p The new starting point as a cloned Ponto2D object.
     */
    public void setStart(Ponto2D p) { this.start = p.clone(); }
    
    /**
     * Set the ending point of the line segment.
     * 
     * @param p The new ending point as a cloned Ponto2D object.
     */
    public void setEnd(Ponto2D p) { this.end = p.clone(); }
    
    // Instance methods
    /**
     * Calculates and returns the length of the segment.
     *
     * @return  The length of the segment.
     */
    public double length()
    {
        double res;
        double x1 = this.getStart().getX(), y1 = this.getStart().getY();
        double x2 = this.getEnd().getX(), y2 = this.getEnd().getY();
        res = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return res;
    }
    
    /**
     * Calculates and returns the slope of the segment.
     *
     * @return  The slope of the Segment. If the line is vertical, Double.POSITIVE_INFINITY is returned.
     */
    public double slope()
    {
        double res;
        double x1 = this.getStart().getX(), y1 = this.getStart().getY();
        double x2 = this.getEnd().getX(), y2 = this.getEnd().getY();
        if(x2 - x1 == 0) {
            res = Double.POSITIVE_INFINITY;
        } else {
            res = (y2 - y1) / (x2 - x1);
        }
        return res;
    }
    
    /**
     * Determines the direction in which the segment goes in the YY axis (up, down, or horizontal).
     *
     * @return  A string indicating the direction of the segment.
     */
    public String goesUpOrDown()
    {   // Could also use slope()
        double y1 = this.getStart().getY();
        double y2 = this.getEnd().getY();
        if(y2 > y1) {
            return "Goes up";
        } else if(y2 == y1) {
            return "Goes horizontal";
        } else {
            return "Goes down";
        }
    }
    
    /**
     * Moves the segment by the specified distances in both the X and Y directions.
     *
     * @param dx    The distance to move in the XX axis.
     * @param dy    The distance to move in the YY axis.
     */
    public void move(double dx, double dy)
    {
        this.start.incCoord(dx, dy);
        this.end.incCoord(dx, dy);
        // TO use getters would be something like: this.setStart(this.getStart().incCoord(dx, dy));
    }
    
    /**
     * Calculates and returns the perimeter assuming the segment is a diameter of a circle.
     *
     * @return  The perimeter of the circle whose diameter is the segment.
     */
    public double perimeterOfDiameter()
    {
        double res = Math.PI * this.length();
        return res;
    }
    
    // Complementary methods - equals, clone, toString
    /**
     * Check if two Segment objects are equal based on their attributes.
     *
     * @param o The object to compare to.
     * @return  true if the segments are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass()))
            return false;
        Segment s = (Segment) o;
        return ((this.getStart().equals(s.getStart())) &&
                (this.getEnd().equals(s.getEnd())));
    }
    
    /**
     * Generates a string representation of the Segment in the format "Segment [(x1,y1),(x2,y2)]".
     *
     * @return  A string representation of the segment.
     */
    public String toString()
    {
        return String.format("Segment [(%d,%d),(%d,%d)]", this.getStart().getX(), this.getStart().getY(), this.getEnd().getX(), this.getEnd().getY());
    }
    
    /**
     * Creates a new Segment object that is a deep copy of this Segment.
     *
     * @return  A new Segment object with the same attributes as the original.
     */
    public Segment clone() { return new Segment(this); }
}
