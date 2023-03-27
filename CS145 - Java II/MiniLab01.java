/* NAME:          PAUL STROUD
   DATE:          20170406
   CLASS:         CS145
   FILENAME:      MiniLab01.java
   DESCRIPTION:   Source code for Mini Lab 01 */

import java.util.*;

public class MiniLab01
   {
    public static void main(String[] args)
      {
       // Part 1
       Scanner console = new Scanner(System.in);

       System.out.print("How many double numbers would you like to generate? ");
       int numDbl = console.nextInt();
       while (numDbl <= 0)
          {
           System.out.print("Please enter a positive number: ");
           numDbl = console.nextInt();
          }

       double[] dblArray = new double[numDbl];
       System.out.print("\nThe numbers are:\n");
       for (int i=0; i<numDbl; i++)
          {
           dblArray[i] = Math.round((Math.random() * 100. - 30.) * 100000) / 100000.;
           System.out.printf("\t%9.5f\n", dblArray[i]);
          }
       
       // Part 2
       double maxDbl, minDbl, avgDbl, range;
       maxDbl = minDbl = avgDbl = dblArray[0];
       int numWith8 = 0, numOfPos = 0;

       if (dblArray[0] > 0) numOfPos++;           
       if ((dblArray[0] * 100000) % 10 == 8) numWith8++;
       for (int i=1; i<numDbl; i++)
          {
           if (dblArray[i] < minDbl) minDbl = dblArray[i];
           if (dblArray[i] > maxDbl) maxDbl = dblArray[i];
           avgDbl += dblArray[i];
           
           if (dblArray[i] > 0) numOfPos++;           
           if ((dblArray[i] * 100000) % 10 == 8) numWith8++;
          }
       avgDbl = Math.round(100000 * (avgDbl /= numDbl)) / 100000.;
       
       //Part 3
       System.out.print("\n++++++STATS++++++" + 
                        "\n\tMax:\t\t" + maxDbl + 
                        "\n\tMin:\t\t" + minDbl +
                        "\n\tRange:\t" + minDbl + "  to  " + maxDbl +
                        "\n\tAvg:\t\t" + avgDbl + 
                        "\n\tEnd with 8:\t\t" + numWith8 +
                        "\n\tNumbers > 0:\t" + numOfPos);
      }
   }