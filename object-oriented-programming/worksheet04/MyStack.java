import java.util.ArrayList;

/**
 * Represents a stack of strings with basic stack operations.
 * The stack follows the LIFO ("last in, first out") principle.
 * It uses an ArrayList<String> for implementation.
 *
 * @author PIRATA
 * @version 2012.04.16
 */
public class MyStack
{
    // Instance variables
    private ArrayList<String> stack;    // Put and pop elements from the end of the list

    /**
     * Default constructor for MyStack. Initializes an empty stack.
     */
    public MyStack()
    {
        this.stack = new ArrayList<String>();
    }
    
    /**
     * Copy constructor for MyStack.
     *
     * @param ms    Another MyStack to copy elements from.
     */
    public MyStack(MyStack ms)
    {
        this.stack = ms.getStack();     // getStack already copies the stack internally.
    }
    
    /**
     * Constructor for MyStack with an element.
     *
     * @param t A String to initialize the stack.
     */
    public MyStack(String t)
    {
        this.stack = new ArrayList<String>();
        this.stack.add(t);  // There's no String clonning!
    }
    
    // Getters & Setters
    /**
     * Gets a deep copy of the stack.
     *
     * @return  A new ArrayList<String> with the same elements as the stack.
     */
    private ArrayList<String> getStack()
    {
        ArrayList<String> res = new ArrayList<>();
        for(String s : this.stack) {
            res.add(s);
        }
        return res;
    }
    
    /**
     * Sets the stack with the provided ArrayList<String>.
     *
     * @param st    The ArrayList<String> to set as the new stack.
     */
    private void setStack(ArrayList<String> st)
    {
        this.stack = new ArrayList<>();
        for(String s : st) {
            this.stack.add(s);
        }
    }
    
    // Instance methods
    /**
     * Get the element at the top of the stack.
     *
     * @return  The element at the top of the stack.
     * @throws IllegalStateException    if this stack is empty.
     */
    public String top()
    {
        // So, according to class, we should assume that we won't call top() whithout checking if empty first.
        // But I checked how would normally be done. So here's a random appropriate exception
        if(this.isEmpty()) {
            // In case it's empty, you just "THROW" an exception
            throw new IllegalStateException("Cannot retrieve the top element. The stack is empty!");
        } else {
            return this.stack.get(this.stack.size() - 1);
        }
    }
    
    /**
     * Inserts a string at the top of the stack.
     *
     * @param s String to be inserted.
     */
    public void push(String s)
    {
        this.stack.add(s);
    }
    
    /**
     * Removes the top element from the stack if it is not empty.
     */
    public void pop()
    {
        if(!this.isEmpty()) {
            this.stack.remove(this.stack.size() - 1);
        }
    }
    
    /**
     * Checks whether the stack is empty.
     *
     * @return  true if the stack is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return this.stack.isEmpty();
    }
    
    /**
     * Determines the length of the stack.
     *
     * @return  The length of the stack.
     */
    public int length()
    {
        return this.stack.size();
    }

    // Complementary methods
    /**
     * Checks if two MyStack objects are equal based on their elements and their order in the stack.
     *
     * @param o The object to compare to.
     * @return  true if the MyStacks are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        boolean res = true;
        int i;
        if(this == o)
            return true;
        if(o == null || this.getClass() == o.getClass())
            return false;
        MyStack ms = (MyStack) o;
        for(i = 0; res && i < this.stack.size(); i++) {
            if(!this.stack.get(i).equals(ms.getStack().get(i))) {
                res = false;
            }
        }
        return res;
    }
    
    /**
     * Returns a string representation of the MyStack in the format "MyStack={<bottomStack>, ..., <topStack>}".
     *
     * @return  A string representation of the MyStack.
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer("MyStack={");
        sb.append(this.stack.toString());
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Creates a new MyStack object that is a deep copy of this MyStack.
     *
     * @return  A new MyStack object with the same elements as the original.
     */
    public MyStack clone() { return new MyStack(this); }
}
