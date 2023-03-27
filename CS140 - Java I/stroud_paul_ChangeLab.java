import java.util.Scanner;

/* NAME:       PAUL STROUD
   DATE:       20160110
   CLASS:      CS140
   FILENAME:   ChangeCalculator.java   (renamed for submission)
   
   DESCRIPTION:
   Source code for Lab 01 - Make Change
*/
public class ChangeCalculator
   {

    /*
        This function is designed to use some elements of java we have not learned yet
        to take in input from the user and then return that information.

        It has no parameters and returns a double. A dollar amount such as 34.65 should
        be entered after the prompt.
    */
    public static double getInput()
      {
       System.out.print("Please enter the amount of money you for which you would like to give change.:");
       Scanner inScan = new Scanner(System.in);
       return inScan.nextDouble();
      }

    // returns the number of "size" bills for making change for "amount"
    public static int numberOfDenom(double amount, double size)
      {
       return (int) (amount / size);
      }

    // returns the remaining amount after making change for "size"
    public static double remainingCash(double amount, double size)
      {
       return (amount % size);
      }

    public static void main(String[] args)
      {
       double total = 100 * getInput(); // prompt user to enter the amount, x100 to avoid rounding error      
       double[] denomArray = { 100, 50, 20, 10, 5, 1, 0.25, 0.1, 0.05, 0.01 };
       int denomCount[] = new int[10];
       String[] denomStrings = { "hundreds", "fifties", "twenties", "tens", "fives",
                                 "ones", "quarters", "dimes", "nickels", "pennies" };
       
       System.out.println("\nThe change is as follows:");
       for (int i=0; i<10; i++) // iterate over each denomination, starting with 100s
          {
           denomCount[i] = numberOfDenom(total, 100 * denomArray[i]); // compute number of bills
           total = remainingCash(total, 100 * denomArray[i]); // reduce the remaining total
           System.out.println("\t" + denomStrings[i] + ":\t" + denomCount[i]); // print the denomination and count
          }
      }
   }