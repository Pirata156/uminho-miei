import java.util.Scanner;   //Input class

/**
 * Program that generates a random number between 0 and 100. The program gives the user 5 attempts to guess the number.
 * Each incorrect attempt the program will say if the generated number is bigger or smaller than the one inputted.
 * On the fifth failed attempt, the user loses. Whether you win or lose the program asks if you wanna replay again or not.
 *
 * @author (PIRATA)
 * @version (2012.04.24)
 */
public class Exercise14
{
    final static String MESSAGEGENERATE = "Random number between 0 and 100 generated.";
    final static String MESSAGEBIGGER = "The correct number is bigger than that one.";
    final static String MESSAGESMALLER = "The correct number is smaller than that one.";
    
    /**
     * This methos generates a pseudo-random number between the two values passed as arguments.
     *
     * @param i     Lowest value the random number can take
     * @param f     Highest value the random number can take
     * @return      Random number between i and f included.
     */
    public static int randomGenerate(int i, int f)
    {
        //Math.random() gives a number between 0 included and 1 not included.
        int res = (int) (Math.random() * (f - i + 1));
        return res;
    }
    
    /**
     * This is the main method.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);  //Input from keyboard
        int inValue;    //Input storage
        String inChoice;
        boolean stillPlays = true, wFlag;
        int indAttempt, genNumber;
        
        while(stillPlays) {
            System.out.println(MESSAGEGENERATE);
            genNumber = randomGenerate(0,100);
            indAttempt = 1;
            wFlag = false;
            //Five attempts
            while(indAttempt <= 5 && !wFlag) {
                System.out.print("Guess #" + indAttempt + ": ");
                inValue = keyboard.nextInt();
                if(inValue == genNumber) {
                    wFlag = true;
                } else if(genNumber > inValue) {
                    indAttempt++;
                    System.out.println(MESSAGEBIGGER);
                } else if(genNumber < inValue) {
                    indAttempt++;
                    System.out.println(MESSAGESMALLER);
                }
            }
            //Win or lose messages
            if(wFlag) {
                System.out.println("\nYou guessed it, it was number " + genNumber);
            } else {
                System.out.println("\nUh oh, you lost! Better luck next time. The correct number was " + genNumber);
            }
            //Still plays?
            System.out.print("Do you want to continue playing? [Y/*]: ");
            inChoice = keyboard.next();
            if(inChoice.equals("y") || inChoice.equals("Y")) {
                stillPlays = true;
            } else {
                stillPlays = false;
            }
        }
        keyboard.close();
    }
}
