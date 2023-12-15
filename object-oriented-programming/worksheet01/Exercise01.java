import java.util.Scanner;   //Input class

/**
 * Read a name and an age and print a text with the results.
 *
 * @author (PIRATA)
 * @version (2012.02.27)
 */
public class Exercise01
{
    /**
     * This is the main method.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);  //Input from the keyboard
        String name;
        int age;
        //Input processing
        System.out.print("Write the name: ");
        name = keyboard.nextLine();
        System.out.print("Specify the age: ");
        age = keyboard.nextInt();
        keyboard.close();   //Input close
        //Output processing
        System.out.println("Name: " + name + ". Age:  " + age + " years old.");
    }
}
