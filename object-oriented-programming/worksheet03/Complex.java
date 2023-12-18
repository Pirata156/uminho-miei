
/**
 * The Complex class represents complex numbers in both rectangular and polar forms.
 * A complex number in rectangular form is represented as "z = a + bi", where a is the real part and b is
 * the imaginary part. In polar form, a complex number is represented as "z = r(cos Φ + sin Φ i)",
 * where r is the magnitude, Φ is the phase angle, and r = sqrt(a*a + b*b).
 *
 * @author (PIRATA)
 * @version (2012.03.26)
 */
public class Complex
{
    // Instance variables
    private double real;        // The real part of the complex number.
    private double imaginary;   // The imaginary part of a complex number

    // Constructors
    /**
     * Default constructor for objects of class Complex.
     * Initializes the real and imaginary parts as 0.
     */
    public Complex()
    {
        this.real = 0;
        this.imaginary = 0;
    }
    
    /**
     * Constructs a new Complex by copying the attributes of the given Complex.
     * 
     * @param c The Complex to copy the data from.
     */
    public Complex(Complex c)
    {
        this.real = c.getReal();
        this.imaginary = c.getImaginary();
    }
    
    /**
     * Constructs a Complex object with the given real and imaginary parts.
     *
     * @param r The real part of the complex number.
     * @param i The imaginary part of the complex number.
     */
    public Complex(double r, double i)
    {
        this.setReal(r);
        this.setImaginary(i);
    }
    
    // Getters & Setters
    /**
     * Get the real part of the Complex number.
     * 
     * @return  The real part of the Complex number.
     */
    public double getReal() { return this.real; }
    
    /**
     * Get the imainary part of the Complex number.
     * 
     * @return  The imaginary part of the Complex number.
     */
    public double getImaginary() { return this.imaginary; }
    
    /**
     * Set the real part of the Complex number.
     * 
     * @param r The real part of the Complex number.
     */
    public void setReal(double r) { this.real = r; }
    
    /**
     * Set the imaginary part of the Complex number.
     * 
     * @param i The imaginary part of the Complex number.
     */
    public void setImaginary(double i) { this.imaginary = i; }
    
    // Instance methods
    /**
     * Adds two complex numbers and returns the result as a new Complex object.
     *
     * @param w The complex number to be added to this complex number.
     * @return  A new Complex object representing the sum of the two complex numbers.
     */
    public Complex add(Complex w)
    {   // z + w = (a + bi) + (c + di) = (a+c) + (b+d)i
        double resReal, resImag;
        resReal = this.getReal() + w.getReal();
        resImag = this.getImaginary() + w.getImaginary();
        return new Complex(resReal, resImag);
    }
    
    /**
     * Multiplies two complex numbers and returns the result as a new Complex object.
     *
     * @param w The complex number to be multiplied with this complex number.
     * @return  A new Complex object representing the product of the two complex numbers.
     */
    public Complex multiply(Complex w)
    {   // z * w = (a + bi) * (c + di) = (ac-bd) + (bc+ad)i
        double a = this.getReal(), b = this.getImaginary();
        double c = w.getReal(), d = w.getImaginary();
        return new Complex(a * c - b * d, b * c + a * d);
    }

    /**
     * Calculates the conjugate of this complex number.
     *
     * @return  A new Complex object representing the conjugate of this complex number.
     */
    public Complex conjugate()
    {   // conjugate(a + bi) = a – bi
        return new Complex(this.getReal(), -this.getImaginary());
    }
    
    /**
     * Calculates the polar representation of the complex number and returns it as a String.
     *
     * @return  A String representing the polar form of the complex number.
     */
    public String polarRepresentation()
    {   // z = r(cos Φ + sen Φ i)
        // r = sqrt( a*a + b*b)
        // r.cos Φ = a
        // r.sen Φ = b
        // Φ = arctan b/a
        double r = this.getReal(), i = this.getImaginary(), magn, phaAng;
        String res;
        magn = Math.sqrt(r * r + i * i);
        phaAng = Math.atan2(i, r);
        res = String.format("z = %.2f (cos %.2f + sin %.2f i)", magn, phaAng, phaAng);
        return res;
    }
    
    // Complementary methods
    /**
     * Check if two Complex objects are equal based on their attributes.
     *
     * @param o The object to compare to.
     * @return  true if the Complexes are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass()))
            return false;
        Complex c = (Complex) o;
        return this.getReal() == c.getReal() && this.getImaginary() == c.getImaginary();
    }
    
    /**
     * Returns a string representation of the Complex in the format "(a + b i)".
     * 
     * @return  A string representation of the Complex.
     */
    public String toString()
    {
        double i = this.getImaginary();
        StringBuffer sb = new StringBuffer("(");
        sb.append(this.getReal() + " ");
        if(i < 0) {
            sb.append("- " + Math.abs(i) + " i)");
        } else {
            sb.append("+ " + i + " i)");
        }
        return sb.toString();
    }
    
    /**
     * Creates a new Complex object that is a deep copy of this Complex.
     * 
     * @return  A new Complex object with the same attributes as the original.
     */
    public Complex clone() { return new Complex(this); }
}
