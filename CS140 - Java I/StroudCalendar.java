/* NAME:       PAUL STROUD
   DATE:       20170126
   CLASS:      CS140
   FILENAME:   StroudCalendar.java
   
   DESCRIPTION:
   Source code for Calendar Assignment - Part 01
*/


import java.util.Scanner;        // import statement for using Scanner objects
import java.util.Calendar;       // import statement for using Calendar objects

public class StroudCalendar
   {
    public static final int calendarSize = 12,     // size of each calendar cell in characters
                        cellHeight = (calendarSize / 2) - 2;   // height of each cal cell (excl. borders)
                        
    public static void main(String[] args)
         {
          Calendar curDate = Calendar.getInstance();     // create new Calendar obj to access current date

          System.out.print("\nPlease enter the date to display:");
          Scanner console = new Scanner(System.in);      // create a new Scanner obj for keyboard
          String userDate = console.next();              // read a string (hopefully it's a date)
       
          drawArt();                                     // don't know what this does
          
          System.out.println("Showing month #" + monthFromDate(userDate));
          drawMonth(monthFromDate(userDate));            // draw requested month
          displayDate(monthFromDate(userDate), dayFromDate(userDate));
       
          System.out.println("\n\nShowing month #" + (curDate.get(Calendar.MONTH) + 1) + " (current)");      
          drawMonth(curDate.get(Calendar.MONTH) + 1);    // draw calendar for current month = cal.MONTH + 1 (to offset counting from zero)
          displayDate(curDate.get(Calendar.MONTH) + 1, curDate.get(Calendar.DATE));
         }

    public static void drawMonth(int month)       // draw the calendar for <month>
         {
          System.out.printf("%" + (1 + 7 * (calendarSize - 1) / 2) + "d\n", month);
                     // print the month number, centered on the calendar table
          drawCalBorderH(7);     // draw first horizontal border
          for (int i=0; i<5; drawRow(i++));     // draw each row of the calendar, from 1 through 5
          System.out.println();  // add an extra blank line afterwards
         }

    public static void drawRow(int row)    // completes a row of the cal; the top horiz. border MUST BE ALREADY DRAWN
         {
          drawCalBorderV(row);      // draw the middle part of the row
          drawCalBorderH(row);      // draw the bottom horizontal border
         }

    public static void drawCalBorderV(int row)     // draws the vert borders of the cal row + date, x <days>
         {
          int  width = calendarSize - 2;     // the width of each cal cell (excl. borders)
          System.out.print("|");             // print the first vert. border
          int  days = (row == 4 ? 3 : 7),    // number of <days> = 7 for the first 4 rows; <days> = 3 for the last row
               cellDate = 7 * row + 1;       // starting date for the row = 7 * week number
          for (int i=0; i<days; i++, cellDate++)   // complete the first line, adding right just. dates
             System.out.printf("%" + (width-1) + "d |", cellDate);   // <width-1> allows for extra space between <cellDate> and "|"
          for (int i=0; i<cellHeight; i++)   // outer loop I: print blank lines * <cellHeight>
             {
              System.out.print("\n|");       // leftmost vert. border
              for (int j=0; j<days; j++, System.out.print("|"))      // inner loop J: print cells * <days>, adding right borders
                 for (int k=0; k<width; k++)    // inner loop K: print the middle of each cell = (" " * <width>)
                     System.out.print(" ");
             }
          System.out.println();              // move cursor to the new line
         }
      
    public static void drawCalBorderH(int row)     // draws horiz border of the cal row, x <days>
         {
          System.out.print("+");             // leftmost "+" in the series
          int days = ( row == 4 ? 3 : 7);    // number of <days> = 7 for the first 4 rows; <days> = 3 for the last row
          for (int i=0; i<days; i++, System.out.print("+"))    // print horiz. borders: series of "-" separated by "+"
             for (int j=0; j<calendarSize-2; j++) System.out.print("-");
          System.out.println();              // move cursor to the new line
         }
      
    public static void displayDate(int month, int day)   // echoes the month and day to the console based on param vars
         {
          System.out.println( "Month: " + month +
                           "\nDay: " + day);
         }
      
    public static int monthFromDate(String date)   // returns the month value as int
         {
          return Integer.parseInt(date.substring(0, date.indexOf("/")));
                     // 1. find "/" with <indexOf>
                     // 2. cut substring from start to <indexOf>
                     // 3. convert substring to <int> and return the value
         }      
      
    public static int dayFromDate(String date)     // returns the day value as int
         { 
          return Integer.parseInt(date.substring(date.indexOf("/")+1));
                     // 1. find "/" with <indexOf>
                     // 2. cut substring from <indexOf>+1 to the end of <date> string
                     // 3. convert substring to <int> and return the value
         }

    public static void drawArt()       // prints ASCII art
         {
          System.out.println( "           #####        ####         \n" +
                              "           ###################       \n" +
                              "        ######################       \n" +
                              "    ########  #######   #####        \n" +
                              "  ########  ###########   ####       \n" +
                              "   ######  ############     ######   \n" +
                              "    ####  ##########^^##    #######  \n" +
                              "     ###   ##   ###   ###    #####   \n" +
                              "    ####   ##vv##/\\#####    ####    \n" +
                              "  ######   ############     ####     \n" +
                              "  #######    ||||||||||    #####     \n" +
                              "   #######    #||||||##   #######    \n" +
                              "        #####   #####   ########     \n" +
                              "        #####################        \n" +
                              "       ##################            \n" +
                              "         ###      #####              \n" +
                              "          #                          \n" +
                              "          #                          \n" +
                              "          #                          \n" +
                              "\tThis isn't over yet, Baird. You better reload...\n\n\n");
         }
   }