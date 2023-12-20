import java.util.ArrayList;

/**
 * Represents a 2D plane with a list of points.
 *
 * @author PIRATA
 * @version 2012.04.16
 */
public class Plane
{
    // Instance variables
    private ArrayList<Ponto2D> points;  // The list of points of the plane.
    
    // Constructors
    /**
     * Default constructor for objects of class Plane.
     * Initializes the plane with empty list of points.
     */
    public Plane()
    {
        this.points = new ArrayList<Ponto2D>();
    }
    
    /**
     * Constructs a new Plane by copying the attributes of the given Plane.
     * 
     * @param other The Plane to copy the data from.
     */
    public Plane(Plane other)
    {
        this.points = other.getPoints();    // getPoints() already makes a deep clone of the list
    }
    
    /**
     * Constructs a new Plane with a starting capacity for the list of points.
     * 
     * @param capacity  The initial capacity for the list.
     */
    public Plane(int capacity)
    {
        this.points = new ArrayList<Ponto2D>(capacity);
    }
    
    // Getters & Setters
    /**
     * Get the list of points of the Plane.
     * 
     * @return  The list of points as a deep cloned ArrayList<Ponto2D> object.
     */
    public ArrayList<Ponto2D> getPoints()
    {
        ArrayList<Ponto2D> res = new ArrayList<Ponto2D>(this.points.size());
        for(Ponto2D p : this.points) {
            if(p != null) { // In the offchance that the element is null!
                res.add(p.clone());
            }
        }
        return res;
    }
    
    //private ArrayList<Ponto2D> getPointsRef() { return this.points; } // Sometimes having a getAttributeReference() is useful in case of future code modification.
    
    /**
     * Set the list of points of the Plane.
     * 
     * @param pts   The list of points to be cloned to the Plane.
     */
    public void setPoints(ArrayList<Ponto2D> pts)
    {
        this.points.clear();    // Clears the array in case of non empty.
        for(Ponto2D p : pts) {
            this.points.add(new Ponto2D(p));
        }
    }
    
    // Instance methods
    /**
     * Get the total number of points in the plane.
     *
     * @return  The total number of points in the plane.
     */
    public int numberPoints()
    {
        return this.points.size();
    }
    
    /**
     * Add a non repetitive new point to the plane.
     *
     * @param pt    The point to be added to the plane.
     * @return      true if this plane list of points changed as a result of the call.
     */
    public boolean addPoint(Ponto2D pt)
    {
        boolean flag = false;
        if(!this.points.contains(pt)) {
            this.points.add(pt.clone()); // Returns true if this collection changed as a result of the call
            flag = true;
        }
        return flag;
        // Simplified: return !this.points.contains(pt) && this.points.add(pt.clone());
    }
    
    /**
     * Remove a point from the plane if it exists.
     *
     * @param pt    The point to be removed from the plane.
     * @return      true if this plane contained the specified element.
     */
    public boolean removePoint(Ponto2D pt)
    {
        return this.points.remove(pt);  // Returns true if this list contained the specified element
    }
    
    /**
     * Join points from another ArrayList<Ponto2D> to the current plane.
     *
     * @param pts   The points to be added to the current plane.
     */
    public void joinPoints(ArrayList<Ponto2D> pts)
    {   // addAll would copie the references instead.
        for(Ponto2D p : pts) {
            this.addPoint(p);
        }
    }
    
    /**
     * Determine the number of points more to the right or above a given point.
     *
     * @param ref   The reference point for comparison.
     * @return      The number of points more to the right or above the reference point.
     */
    public int rightOrAboveOf(Ponto2D ref)
    {
        int res = 0;
        for(Ponto2D p : this.points) {
            if((p.getX() >= ref.getX()) || (p.getY() >= ref.getY())) {
                res++;
            }
        }
        return res;
    }
    
    /**
     * Displace all points with a specified x-coordinate by a given amount.
     *
     * @param cx    The XX coordinate to be displaced.
     * @param dx    The displacement amount.
     * @return      The number of points displaced.
     */
    public int displacePointsXX(double cx, double dx)
    {
        int res = 0;
        for(Ponto2D p : this.points) {
            if(p.getX() == cx) {
                p.incCoord(dx, 0);
                res++;
            }
        }
        return res;
    }
    
    /**
     * Given a plane as a parameter, determine how many points are common to the two planes.
     *
     * @param pln   The Plane to compare with.
     * @return      The number of common points between the two planes.
     */
    public int numberCommon(Plane pln)
    {
        int res = 0;
        for(Ponto2D pt : pln.getPoints()) {
            if(this.points.contains(pt)) {
                res++;
            }
        }
        return res;
    }
    
    /**
     * Create a list containing the points common to the receiving plane and a plane given as a parameter.
     *
     * @param pln   The Plane to intersect with.
     * @return      An ArrayList<Ponto2D> containing the common points.
     */
    public ArrayList<Ponto2D> commonList(Plane pln)
    {   // So! We're not allowed to use retainAll...
        int i;
        ArrayList<Ponto2D> res = new ArrayList<Ponto2D>(), temp = pln.getPoints();  // getPoints() make a deep clone
        for(i = 0; i < this.points.size(); i++) {
            if(temp.contains(this.points.get(i))) {
                res.add(this.points.get(i).clone());
            }
        }
        return res;
    }
    
    /**
     * Create a list containing all points on the plane with a coordinate in XX lower than a value given as a parameter.
     *
     * @param cx    The value to compare the x-coordinate with.
     * @return      An ArrayList<Ponto2D> containing the points with x-coordinate lower than cx.
     */
    public ArrayList<Ponto2D> leftOf(double cx)
    {
        ArrayList<Ponto2D> res = new ArrayList<Ponto2D>();
        for(Ponto2D pt : this.points) {
            if(pt.getX() < cx) {
                res.add(pt.clone());
            }
        }
        return res;
    }
    
    /**
     * Create a new plane that contains the common points between the receiving plane and a plane given as a parameter.
     *
     * @param pln   The Plane to intersect with.
     * @return      A new Plane containing the common points.
     */
    public Plane commonPlane(Plane pln)
    {
        Plane res = new Plane();
        res.joinPoints(this.commonList(pln));
        return res;
    }
    
    // Complementary methods
    /**
     * Check if two Plane objects are equal based on their attributes.
     *
     * @param o The object to compare to.
     * @return  true if the Planes are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Plane p = (Plane) o;
        return this.points.equals(p.getPoints());
    }
    
    /**
     * Returns a string representation of the Plane in the format "Plane{(XX,YY),..,Size:SS}".
     * 
     * @return  A string representation of the Plane.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Plane{");
        for(Ponto2D p : this.getPoints()) {
            sb.append("(" + p.getX() + "," + p.getY() + "), ");
        }
        sb.append("Size:" + this.points.size() + "}");
        return sb.toString();
    }
    
    /**
     * Creates a new Plane object that is a deep copy of this Plane.
     * 
     * @return  A new Plane object with the same attributes as the original.
     */
    public Plane clone() { return new Plane(this); }
}
