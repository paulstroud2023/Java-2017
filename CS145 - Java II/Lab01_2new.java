/* NAME:          PAUL STROUD
   DATE:          20170411
   CLASS:         CS145
   FILENAME:      Lab01_2new.java
   DESCRIPTION:   Source code for Lab 01 - 2 */

public class Lab01_2new
   {
    public static int numOfSteps = 5;
    // array to hold elements of the human ascii
    public static String[] human = { "  O  *******", " /|\\ *      ", " / \\ *      " };
    
    public static void main(String[] args)
      {
       for (int i=0; i<numOfSteps; i++)
          {
           for (int k=0; k<3; k++)
              {
               // offset the line as needed
               for (int j=0; j<6*(numOfSteps-i-1); j++)
                  System.out.print(" ");
               
               // print human
               System.out.print(human[k]);
               
               // fill in the rest of the line
               for (int j=0; j<6*i; j++)
                  System.out.print(" ");
               
               // print border and go to next line
               System.out.print("*\n");
              }
          }

       // print the floor
       System.out.print("*");
       for (int i=0; i<numOfSteps+1; i++)
          System.out.print("******");
      }
   }

/*
              O  *******	6*(Nsteps-n)*" " + person + step + 6*n*" " + border
             /|\ *     *
             / \ *     *
        O  *******     *
       /|\ *           *
       / \ *           *
  O  *******           *
 /|\ *                 *
 / \ *                 *
******                 *
*/