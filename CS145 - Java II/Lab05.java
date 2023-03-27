/* NAME:       PAUL STROUD
   DATE:       20170519
   CLASS:      CS145
   FILENAME:   Lab05.java
   
   DESCRIPTION:
   Source code for Lab 05
*/

import java.util.*;

class Lab05
   {
    public static boolean verbose=false;
    public static String[] pegName = { "A", "B", "C" };
    public static void main(String[] args)
      {
       Scanner console = new Scanner(System.in);
       System.out.print("\n#####  HANOI TOWER SOLVER  #####" +
                        "\n\nEnter the starting number of disks: ");
       int start, end;
       start = console.nextInt();
       System.out.print("\nEnter the ending number of disks: ");
       end = console.nextInt();
       
       System.out.print("\nVerbose mode? (Y/N): ");
       if (console.next().equalsIgnoreCase("Y"))
         verbose = true;
       for (int i=start; i<=end; i++)
          {
           System.out.print("\n\n\tSOLVING FOR " + i + " DISKS... ");
           System.out.print("\n\tMOVES USED: " + hanoi(i, 1, 2, 3));
          }
      }
      
    public static int hanoi(int n, int A, int B, int C)  // move n disks A->B using C
      {
       int i=0;      // move counter
       if (n > 0)
         {
          i += hanoi(n-1, A, C, B);    // move n-1 disks from A->B using C
          i++;                         // increment counter for moving 1 disk A->C
          i += hanoi(n-1, B, A, C);    // move n-1 from B->C using C
          if (verbose) System.out.print("\nMoving disk #" + n +
                                          " from peg " + pegName[A-1] +
                                          " to peg " + pegName[B-1] + 
                                          ", using peg " + pegName[C-1] + ".");
         }
       return i;
      }
   }