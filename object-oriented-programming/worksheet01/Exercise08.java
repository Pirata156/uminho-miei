import java.util.Scanner;   //Input class

/**
 * Program that accepts N grades (real numbers) between 0 and 20 and determines its average.
 *
 * @author (PIRATA)
 * @version (2012.03.28)
 */
public class Exercise08
{
    /**
     * This is the main method.
     * Made so it calculates the average each time gets a new value.
     * (Could also just add all the values and average them at the end)
     *
     * @param args  Unused
     * @return      Nothing
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);  //Input from keyboard
        int n, i = 0;
        float inGrade, average = 0;
        
        System.out.printf("Number of grades: ");
        n = keyboard.nextInt();
        while(i < n) {
            System.out.printf("Grade: ");
            inGrade = keyboard.nextFloat();
            if(inGrade >= 0 && inGrade <= 20) {
                average = ((average * i) + inGrade) / (i + 1);
                i++;
                System.out.printf("Average: %2.1f%n", average);
            } else {
                System.err.printf("Grade error: OutOfBounds%n");
            }
        }
        keyboard.close();
        
        System.out.printf("Final average of %d grades: %2.1f%n", i, average);
    }
}
