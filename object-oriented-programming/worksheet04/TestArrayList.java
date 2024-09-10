import java.util.ArrayList;
import static java.lang.System.out;

/**
 * This class, TestArrayList, is created to showcase the usage of various ArrayList methods.
 * It includes examples with ArrayList<String> and ArrayList<Point2D>, demonstrating methods such as
 * add(), get(), addAll(), remove(), removeAll(), size(), indexOf(), contains(), and retainAll().
 *
 * Official class documentation: https://docs.oracle.com/javase/8/docs/api/
 *
 * @author PIRATA
 * @version 2012.04.16
 */
public class TestArrayList
{
    /**
     * The main method serves as the entry point for the TestArrayList program.
     *
     * @param args  The command-line arguments (Unused).
     */
    public static void main(String[] args)
    {
        // Constructors
        out.println("Creating ArrayList<String>() and ArrayList<Ponto2D>(25).");
        ArrayList<String> strList = new ArrayList<String>();    // Here ArrayList starts with initial capacity of 10.
        ArrayList<Ponto2D> ptList = new ArrayList<Ponto2D>(25); // Setting ArrayList to have an initial capacity of 25.
        out.println("StringList:" + strList.toString() + "; Size=" + strList.size() + "; Initial capacity=10");
        out.println("Ponto2DList:" + ptList.toString() + "; Size=" + ptList.size() + "; Initial capacity=25");
        
        out.println("Is empty?");
        out.println("Ponto2DList.isEmpty() = " + strList.isEmpty());
        
        // Adding
        out.println("Method add(E e): add(String \"World!\");");
        strList.add("World!");
        out.println("StringList:" + strList.toString() + "; Size=" + strList.size());
        out.println("Method add(int index, E e): add(0, \"Hello\");");
        strList.add(0, "Hello");
        out.println("StringList:" + strList.toString() + "; Size=" + strList.size());
        
        out.println("Adding points to Ponto2DList");
        ptList.add(new Ponto2D(1,2));
        ptList.add(new Ponto2D(3,4));
        ptList.add(new Ponto2D(5,6));
        ptList.add(new Ponto2D(7,8));
        ptList.add(new Ponto2D(9,0));
        out.println("Ponto2DList:" + ptList.toString() + "; Size=" + ptList.size());
        
        // Demonstrate addAll()
        out.println("\nMethod addAll(): adding {\"ArrayList\", \"methods\"}");
        ArrayList<String> additionalStrings = new ArrayList<String>();    // On the new, the type can omitted. Since already set on the type at the start.
        additionalStrings.add("ArrayList");
        additionalStrings.add("methods");
        strList.addAll(additionalStrings);
        out.println("StringList:" + strList.toString() + "; Size=" + strList.size());
        
        // Demonstrate get()
        out.println("\nMethod get(): Element at index 2: " + strList.get(2));
        
        // Demonstrate remove()
        out.println("Method remove(): remove \"World!\"");
        strList.remove("World!");
        out.println("StringList:" + strList.toString() + "; Size=" + strList.size());
        
        // Demonstrate removeAll()
        out.println("Method removeAll(): removeAll ArrayList{\"Hello\"}");
        ArrayList<String> removeStrings = new ArrayList<String>();
        removeStrings.add("Hello");
        strList.removeAll(removeStrings);
        out.println("StringList:" + strList.toString() + "; Size=" + strList.size());
        
        // Demonstrate indexOf()
        out.println("Method indexOf(): Ponto2D(3,4)");
        int index = ptList.indexOf(new Ponto2D(3, 4));
        out.println("Index of (3, 4) in pointList: " + index);
        out.println("Ponto2DList:" + ptList.toString() + "; Size=" + ptList.size());
        
        // Demonstrate contains()
        out.println("Method contains(): Ponto2D(5,6)");
        boolean contains = ptList.contains(new Ponto2D(5, 6));
        System.out.println("Contains (5, 6) in pointList: " + contains);
        out.println("Ponto2DList:" + ptList.toString() + "; Size=" + ptList.size());
        
        // Demonstrate retainAll()
        out.println("Method retainsAll(): {Ponto2D(1,2),Ponto2D(5,6)}");
        ArrayList<Ponto2D> retainPoints = new ArrayList<Ponto2D>();
        retainPoints.add(new Ponto2D(1, 2));
        retainPoints.add(new Ponto2D(5, 6));
        ptList.retainAll(retainPoints);
        out.println("Ponto2DList:" + ptList.toString() + "; Size=" + ptList.size());
    }
}
