import java.util.Scanner;   //Input class

/**
 * Program that reads a series of words, ending with the word "zzz", into an array.
 * Then, it repeatedly acccepts a word, until the word "xxx" is entered, and checks if such a word does not accept in the array.
 * If it exists, the program must remove that word from the array.
 *
 * @author (PIRATA)
 * @version (2012.04.26)
 */
public class Exercise07
{
    /**
     * This value is the maximum size the array can take.
     */
    public static final int MAXDIM = 7;
    
    /**
     * Method that read a series of words, ending with the word "zzz", onto an array.
     *
     * @param words     Initialized array onto which it'll store the read words.
     * @return          Number of words read and stored in the array.
     */
    public static int readWordsArray(String[] words)
    {
        Scanner keyboard = new Scanner(System.in);
        String inWord;
        int counter = 0;
        
        do {
            System.out.print("Word [" + counter + "] = ");
            inWord = keyboard.next();
            words[counter] = inWord;
            counter++;
        } while((!inWord.equals("zzz")) && (counter < MAXDIM));
        keyboard.close();
        return counter;
    }
    
    /**
     * Method that read a series of words, ending with the word "xxx", and removes them from the array if they exist.
     *
     * @param words     Array with the list of words stored.
     * @param size      Effective number of words stored in the array.
     * @param roRem     Word to check and remove from the array.
     * @return          Effective number of words still stored in the array.
     */
    public static int removeWordArray(String[] words, int size, String toRem)
    {
        int i, j, intSize = size;
        
        for(i = 0; i < intSize; i++) {
            if(words[i].equals(toRem)) {
                for(j = i + 1; j < intSize; j++) {
                    words[j-1] = words[j];
                }
                intSize--;
            }
        }
        return intSize;
    }
    
    /**
     * This is the main method.
     * All input values are assumed correct and in the expected bounds.
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        String[] dictio = new String[MAXDIM];
        String inWord;
        int size = 0, i;
        
        System.out.println("Reading words onto array.");
        size = readWordsArray(dictio);
        for(i = 0; i < size; i++) {
            System.out.print(" |" + dictio[i] + "|");
        }
        
        System.out.println("\nWords to remove (\"xxx\" to stop):");
        do {
            inWord = keyboard.next();
            size = removeWordArray(dictio, size, inWord);
        } while(!inWord.equals("xxx"));
        keyboard.close();
        for(i = 0; i < size; i++) {
            System.out.print(" |" + dictio[i] + "|");
        }
    }
}
