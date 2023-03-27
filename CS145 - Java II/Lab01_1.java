/* NAME:          PAUL STROUD
   DATE:          20170411
   CLASS:         CS145
   FILENAME:      Lab01_1.java
   DESCRIPTION:   Source code for Lab 01 - 1 */

public class Lab01_1
   {
    public static void main(String[] args)
      {
       drawLine(8);
       drawTriTop(8);
       drawTriTop(8);
       drawLine(8);
       drawTriBottom(8);
       drawTriBottom(8);
       drawLine(8);
      }
    public static void drawLine(int width)
      {
       System.out.print("\n\n+");
       for (int j=0; j<width-2; j++) System.out.print("-");
       System.out.print("+");
      }
    public static void drawTriTop(int width)
      {
       for (int i=0; i<3; i++)
          {
           System.out.print("\n\n|");
           int offset = 2*i;     // num of spaces in the middle
           // (width-offset-4) is the number of spaces on each side of '^'
           // 4 represents two '|' and two '^' chars
           for (int j=0; j<(width-offset-4)/2; j++) System.out.print(' ');
           System.out.print("^");
           for (int j=0; j<offset; j++) System.out.print(' ');
           System.out.print("^");
           for (int j=0; j<(width-offset-4)/2; j++) System.out.print(' ');
           System.out.print("|");
          }
      }
    public static void drawTriBottom(int width)
      {
       for (int i=3; i>0; i--)
          {
           System.out.print("\n\n|");
           int offset = 2*(i-1);     // num of spaces in the middle, adj. to range from 4 to 0
           // (width-offset-4) is the number of spaces on each side of '^'
           // 4 represents two '|' and two '^' chars
           for (int j=0; j<(width-offset-4)/2; j++) System.out.print(' ');
           System.out.print("^");
           for (int j=0; j<offset; j++) System.out.print(' ');
           System.out.print("^");
           for (int j=0; j<(width-offset-4)/2; j++) System.out.print(' ');
           System.out.print("|");
          }
       
      }
   }