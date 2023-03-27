/* NAME:       PAUL STROUD
   DATE:       20170202
   CLASS:      CS140
   FILENAME:   GradeCalc.java
   
   DESCRIPTION:
   Source code for Lab 04 - Loops + conditionals practice
*/

import java.util.*;

public class GradeCalc
   {
    public static void main(String[] args)
      {
       Scanner console = new Scanner(System.in);
       
       System.out.print("Please enter number grades to convert to letter grades: ");
       int studentCount = 0;  // counter var to add number IDs to students
       while (console.hasNextDouble()) // while there are double numbers in user input
         {
          studentCount++;     // increase the counter
          double grade = console.nextDouble();  // read next number grade
          char ltrGrade;      // char container for the letter grade
          if (grade >= 90)          ltrGrade = 'A';   // A = 90-100
            else if (grade >= 80)   ltrGrade = 'B';   // B = 80-90
            else if (grade >= 70)   ltrGrade = 'C';   // C = 70-80
            else if (grade >= 60)   ltrGrade = 'D';   // C = 60-70
            else                    ltrGrade = 'F';   // F = anything else
          
          System.out.printf("\n\tStudent %d has a grade of %.1f and has received a(n) %s", studentCount, grade, ltrGrade);
               // output students ID, number grade, and letter grade
         }
       
      }
   }