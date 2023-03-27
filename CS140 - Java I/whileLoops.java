/* NAME:       PAUL STROUD
   DATE:       20160228
   CLASS:      CS140
   FILENAME:   whileLoops.java
   
   DESCRIPTION:
   Source code for Lab 05 - While Loops
*/

import java.util.*;
import java.io.*;

class whileLoops
   {
    public static void main(String[] args)
         throws FileNotFoundException
      {
       System.out.print("\n\n\tPART 1");
       avgDoublesFixed();
       System.out.print("\n\n\tPART 2");
       avgDoublesFlex();
       System.out.print("\n\n\tPART 3");
       avgDoublesLtd();
       System.out.print("\n\n\tPART 4");
       avgDoublesFile();
      }
      
    public static void avgDoublesFixed()  // averages a user-defined number of doubles and prints output
      {
       Scanner console = new Scanner(System.in);
       double total=0, count;
       System.out.print("\nHow many numbers would you like to average? ");
       count = console.nextInt();
       System.out.print("\nPlease enter the numbers to average: ");
       for (int i=0; i<count; i++)
          total += console.nextDouble();
       System.out.print("\nThe average of numbers is: " + (Math.round((total * 100.0) / count)/100.0));
      }
      
    public static void avgDoublesFlex()  // averages a flexible number of doubles and prints output
      {
       Scanner console = new Scanner(System.in);
       double total=0, count=0;
       System.out.print("\nPlease enter the numbers to average: ");
       while (console.hasNextDouble())
          {
           total += console.nextDouble();
           count++;
          }
       System.out.print("\nThe average of numbers is: " + (Math.round((total * 100.0) / count)/100.0));
      }
   
    public static void avgDoublesLtd()  // averages doubles within limited range (0;100) and prints output
      {
       Scanner console = new Scanner(System.in);
       double num, total=0, count=0;
       
       System.out.print("\nPlease enter the numbers to average: ");

       while (console.hasNextDouble())
          {
           num = console.nextDouble();
           if ((num > 0) && (num < 100))
             {
              total += num;
              count++;
             }
          }
       System.out.print("\nThe average of numbers is: " + (Math.round((total * 100.0) / count)/100.0));
      }

    public static void avgDoublesFile()  // averages doubles from an input file and prints output
         throws FileNotFoundException
      {
       File inputFile = new File("doubles.txt");
       if (inputFile.canRead())  // check if file can be read
            {
             Scanner input = new Scanner(inputFile);
             double total=0, count=0;
             
             System.out.print("\nReading numbers from the file...");
             while (input.hasNextDouble())
                {
                 total += input.nextDouble();
                 count++;
                }
             System.out.print("\nThe average of numbers is: " + (Math.round((total * 100.0) / count)/100.0));
            }
         else System.out.print("\nERROR: The file cannot be read.");
      }
   }   