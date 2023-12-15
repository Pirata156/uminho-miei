import java.util.Scanner;   //Input class

/**
 * Program that presents the user with a vertical menu with 5 options.
 * Then reads an int between those valid options, which show a verbatim.
 * It should only close when user selects the Exit option.
 *
 * @author (PIRATA)
 * @version (2012.04.24)
 */
public class Exercise13
{
    //Immutable String - could be called inside main as final String MENU
    final static String MENU = "1 - Insert\n2 - Remove\n3 - Consult\n4 - Save\n5 - Exit";
    
    /**
     * This is the main method.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);  //Input from keyboard
        int inValue = 0;
        
        do {
            System.out.println(MENU);
            inValue = keyboard.nextInt();
            
            switch(inValue) {
                case 1:
                    System.out.println("Insert"); break;
                case 2:
                    System.out.println("Remove"); break;
                case 3:
                    System.out.println("Consult"); break;
                case 4:
                    System.out.println("Save"); break;
                case 5:
                    System.out.println("Exit"); break;
                default:
                    System.err.println("Invalid option!");
            }
        } while(inValue != 5);
        keyboard.close();
    }
}
