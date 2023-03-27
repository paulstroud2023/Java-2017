/* NAME:       PAUL STROUD
   DATE:       20170414
   CLASS:      CS145
   FILENAME:   Assignment01.java
   
   DESCRIPTION:
   Source code for Assignment 01
*/

import java.util.*;

public class Assignment01
   {
    public static int guessRange = 100;   // top limit for the guessing range
    
    public static void main(String[] args)
      {
       Scanner console = new Scanner(System.in);
       System.out.print("\nWelcome to the guessing game.\n" +
                        "_____________________________\n" +
                        "Please choose a number between 1 and " + guessRange + ",\n" +
                        "and the computer will try to guess it.\n");
       String userInput = "n";
       int  guess = guessRange / 2, guessCount = 1,   // guess the middle number as the start + set guess count
            tmpRangeHi = guessRange, tmpRangeLo = 1;  // set the working range to make guesses

       while (!userInput.equals("y"))
          {
           // prompt the user
           System.out.print("\nDid you choose " + guess + "? (y/n): ");
           userInput = console.next();
           
           // exit the loop if "yes"
           if (userInput.equals("n"))
             {
              // prompt to narrow the range
              System.out.print("Greater or less than " + guess +"? (g/l): ");
              do {
                  userInput = console.next();
                 } while ( ! (userInput.equals("g") || userInput.equals("l")));
              
              // adjust the range per user input
              if (userInput.equals("g"))
                  tmpRangeLo = guess+1;
                else
                  tmpRangeHi = guess-1;
              
              // guess the new number and loop over
              guess = (int) Math.round(Math.random() * (tmpRangeHi - tmpRangeLo)) + tmpRangeLo;
              guessCount++;
              userInput = "n";
             } // end outer if
          } // end while

       // job's done
       System.out.print("\n\nGAME OVER: It took " + guessCount + " attempts to guess your number (" + guess + ").");
      }  // end main
   }  // end class