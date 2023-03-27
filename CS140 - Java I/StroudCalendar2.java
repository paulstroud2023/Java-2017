/* NAME:       PAUL STROUD
   DATE:       20170218
   CLASS:      CS140
   FILENAME:   StroudCalendar2.java
   
   DESCRIPTION:
   Source code for Calendar Assignment - Part 02
*/


import java.util.Scanner;        // import statement for using Scanner objects
import java.util.Calendar;       // import statement for using Calendar objects

public class StroudCalendar2
   {
    public static final int   CALENDAR_SIZE = 12,     // size of each calendar cell in characters
                              CELL_HEIGHT = (CALENDAR_SIZE / 2) - 2;   // height of each cal cell (excl. H borders)
    public static final String   WEEKDAYS[] = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" },
                                 MONTHS[] = {   "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE",
                                                "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" };
    public static final int DAYS_IN_MONTH[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };


    public static void main(String[] args)
      {
       System.out.print("\n\t\tWELCOME TO 600613 CALENDAR!\n\n");
       drawArt(); // not sure what this does
       Scanner console = new Scanner(System.in);
       Calendar curDate = Calendar.getInstance();     // create new Calendar obj to access current date
       // extract numeric month/day/year into the array
       int dateIntArray[] = { curDate.get(Calendar.MONTH), curDate.get(Calendar.DATE), curDate.get(Calendar.YEAR) };
       char choice;
          
       // USER INPUT LOOP AKA "THE MENU"
       do {
           System.out.print("\nEnter a command. Type ? for help: ");
           
           // grab a char from console, then OR 0x20 to ignore case
           choice = (char) (console.next().charAt(0) | 0b00100000);
           switch (choice)    // check the value
            {
             case 'q': 
                  break;   // exit the switch statement
             case 'e': case 'n': case 'p':
                  switch (choice)
                    {
                     case 'e':   // user enters custom date
                           dateIntArray = inputDate(); break;
                     case 'n':   // change date to next month
                           setNextMonth(dateIntArray); break;
                     case 'p':   // change date to prev. month
                           setPrevMonth(dateIntArray); break;
                    }
                  // set date and continue down to 't'
                  curDate.set(dateIntArray[2], dateIntArray[0], dateIntArray[1]);
             case 't':
                  drawMonth(curDate);  // print calendar for <curDate>
                  System.out.print( "\tSelected date is:   "  + MONTHS[curDate.get(Calendar.MONTH)] +
                                    " " + curDate.get(Calendar.DATE) + ", " + curDate.get(Calendar.YEAR));
                  break;   // exit the switch statement
             case '?':
                  System.out.println(  "\t\'e\'\tenter a custom date for display\n" +
                                       "\t\'t\'\tdisplay today's calendar\n" +
                                       "\t\'n\'\tdisplay next month's calendar\n" +
                                       "\t\'p\'\tdisplay previous month's calendar\n" +
                                       "\t\'q\'\texit the program\n");
                  break;   // exit the switch statement
             default:   // Warning: PEBKAC
                  System.out.print("ERROR ID.10.T: Please enter a valid command.");
            }  // end of switch
          } while (choice != 'q');  // repeat loop unless <choice> == 'q'
          
       System.out.println("\n\t\tGITT TO ZE CHOPPA NAUWW!!!");
      }

   public static void drawMonth(Calendar calObj)       // draw the calendar for <month>
      {
       int dateVar = calObj.get(Calendar.DATE);
       calObj.set(Calendar.DATE, 1);
       int  firstWeekday = calObj.get(Calendar.DAY_OF_WEEK),      // extract day of the week (in Java format)
            daysInMonth = calObj.getActualMaximum(Calendar.DATE); // get num of days in month
       // adj week to start from Monday=0     
       firstWeekday = (firstWeekday == 1)  ?  6 : firstWeekday-2; // if Sunday, set to 6; else -=2
       // calc num of rows (+1 if division was incomplete)
       int numOfRows = ((daysInMonth + firstWeekday) / 7)
                       + ((((daysInMonth + firstWeekday) % 7) == 0)  ?  0 : 1);

       calObj.set(Calendar.DATE, dateVar);   // restore correct date
       
       System.out.println( "\n\tMonth is " + MONTHS[calObj.get(Calendar.MONTH)] +
                           "\n\tFirst weekday is " + WEEKDAYS[firstWeekday] +
                           "\n\tDays in month: " + daysInMonth);

       System.out.printf(  "\n%" + (1 + 7 * (CALENDAR_SIZE - 1) / 2) + "s\n",
                           MONTHS[calObj.get(Calendar.MONTH)] + " " +
                           calObj.get(Calendar.YEAR));
                           // print the month name, centered on the calendar table
                           
       // print weekday names
       for (int i=0; i<7; i++)
          System.out.printf("  %-" + (CALENDAR_SIZE-3) + "s", WEEKDAYS[i]);
       System.out.println();
       
       // DRAW CAL HERE
       for (int i=0; i<numOfRows; i++)    // print 5 rows
          drawRow(i, firstWeekday, daysInMonth, dateVar);
       drawCalBorderH(numOfRows, firstWeekday, daysInMonth);   // draw bottom border

       System.out.println();  // add an extra blank line afterwards
      }

   public static void drawRow(int row, int firstWeekday, int daysInMonth, int targetDate)    // completes a row of the cal; the top horiz. border MUST BE ALREADY DRAWN
      {
       drawCalBorderH(row, firstWeekday, daysInMonth);      // draw the top horizontal border
       drawCalBorderV(row, firstWeekday, daysInMonth, targetDate);      // draw the middle part of the row
      }

   public static void drawCalBorderV(int row, int firstWeekday, int daysInMonth, int targetDate)     // draws the vert borders of the cal row + date, x <days>
      {
       int rowStartDate = row * 7 + 1 - firstWeekday; // calc start date, adj for <firstWeekday>
       int  width = CALENDAR_SIZE - 2;     // the width of each cal cell (excl. borders)
       
       for (int i=0; (i<7) && (rowStartDate <= daysInMonth); i++, rowStartDate++)
          {  // while (the row is incomplete) AND (the date doesn't exceed <daysInMonth>)
           if (rowStartDate < 1)   for (int j=0; j<CALENDAR_SIZE-1; j++) System.out.print(" ");
                  // if the date is zero/neg, print blank spaces
            else   // if the date is valid, print border
             {
              if (rowStartDate == targetDate)
                  System.out.printf("|%" + (width-1) + "s ", ":( " + rowStartDate);   // <width-1> allows for extra space between <rowStartDate> and "|"
                else 
                  System.out.printf("|%" + (width-1) + "d ", rowStartDate);   // <width-1> allows for extra space between <rowStartDate> and "|"

             }
          } // end of outer for loop
       if (rowStartDate <= daysInMonth+1) System.out.print("|\n");   // complete border and go to next line
          
       for (int i0=0; i0<CELL_HEIGHT; i0++)   // print blank lines * <CELL_HEIGHT>
          {
           rowStartDate = row * 7 + 1 - firstWeekday; // reinitialize start date

           for (int i=0; (i<7) && (rowStartDate <= daysInMonth); i++, rowStartDate++)
              {  // while (the row is incomplete) AND (the date doesn't exceed <daysInMonth>)
               if (rowStartDate < 1)   for (int j=0; j<CALENDAR_SIZE-1; j++) System.out.print(" ");
                        // if the date is zero/neg, print blank spaces
                 else   // if the date is valid, print border
                      {
                       System.out.print("|");      // inner loop J: print cells * <days>, adding right borders
                       for (int k=0; k<width; k++)    // inner loop K: print the middle of each cell = (" " * <width>)
                          System.out.print(" ");
                      }
                   }
           if (rowStartDate <= daysInMonth+1) System.out.print("|\n"); // complete border and go to next line
          }
      }
      
   // draws horiz border of the cal row
   public static void drawCalBorderH(int row, int firstWeekday, int daysInMonth)
      {
       int rowStartDate = row * 7 + 1 - firstWeekday; // calc start date, adj for <firstWeekday>
       // while (the row is incomplete) AND (the date doesn't exceed <daysInMonth>+7)
       //   <daysInMonth>+7 allows the bottom border to print correctly
       //   due to <num of horiz borders> = <numOfRows>+1
       for (int i=0; (i<7) && (rowStartDate <= daysInMonth+7); i++, rowStartDate++)
          {
           if (rowStartDate < 1)   for (int j=0; j<CALENDAR_SIZE-1; j++) System.out.print(" ");
                  // if the date is zero/neg, print blank spaces
            else  // if the date is valid, print border
             {
              System.out.print('+');
              for (int j=0; j<CALENDAR_SIZE-2; j++) System.out.print("-");
             }
          } // end of outer for loop
       // validate <rowStartDate>, else method could print '+' by itself
       if (rowStartDate <= daysInMonth+8)
         System.out.print("+\n");   // complete border and go to next line
      }

   public static void setPrevMonth(int[] dateArray)
      {
       if (dateArray[0] == 0)    // if month is Jan
            {
             dateArray[0] = 11;  // set month to Dec
             dateArray[2]--;     // decrement year
            }
         else dateArray[0]--;    // decrement month
      }

   public static void setNextMonth(int[] dateArray)
      {
       if (dateArray[0] == 11)   // if month is Dec
            {
             dateArray[0] = 0;   // set month to Jan
             dateArray[2]++;     // increment year
            }
         else dateArray[0]++;    // increment month
      }

   public static int[] inputDate()
      {
       Scanner console = new Scanner(System.in);
       boolean loopVar = true;
       String userInput = "";
       String[] tempStringArray;
       int dateArray[] = {};
       while (loopVar)
          {
           System.out.print("\nEnter the date in short month/day/year format (i.e. 5/16/99): ");
           userInput = console.nextLine();
           int slashCount = 0;
           boolean badInput = false;
           
           // Step 1: check the length
           if (userInput.length()<3 || userInput.length()>8)
               {
                System.out.print("ERROR 01: Invalid input! Please use the M/D/Y format.");
                continue;
               }
             else
               {
                 // Step 2: validate each character
                 for (int i=0; i<userInput.length(); i++)
                    {
                     if (userInput.charAt(i) < '/' || userInput.charAt(i) > '9')
                       {
                        System.out.print("ERROR 02: Invalid input!\t\'" + userInput.charAt(i) + '\'');
                        badInput = true;  // set the flag for the if statement after the loop
                        break;
                       }
                     if (userInput.charAt(i) == '/') slashCount++;   // count slashes
                    } // end of for
              
                 if (!badInput)  // if no invalid chars detected
                   {
                    // Step 3: verify <slashCount>
                    if (slashCount != 2) // error-out if invalid
                      {
                       System.out.print("ERROR 03: Please use one slash in M/D/Y format.");
                       continue;
                      }
   
                    // Step 4: extract month/day values and store as integers
                    tempStringArray = userInput.split("/");       // extract month/day from the date string
                    dateArray = new int[tempStringArray.length];  // create blank int array to hold numbers
                    for (int i=0; i<dateArray.length; i++)        // convert strings to ints
                       dateArray[i] = Integer.parseInt(tempStringArray[i]);
                    
                    // Step 5: validate each number
                    if (dateArray[0] < 1  ||  dateArray[0] > 12)
                           System.out.print("ERROR 04: Invalid MONTH value.\t" + dateArray[0]);
                      else if (dateArray[1] < 1  ||  dateArray[1] > DAYS_IN_MONTH[dateArray[0]-1])
                           System.out.print("ERROR 05: Invalid DAY value.\t" + dateArray[1]);
                      else if (dateArray[2] < 0  ||  dateArray[2] > 99)
                           System.out.print("ERROR 06: Invalid YEAR value.\t" + dateArray[2]);
                      else loopVar = false;  // end the loop if no errors found
                      
                     // Step 6: adjust the month and year values
                     if (!loopVar)    // if step 5 was successful
                       {
                        dateArray[0]--;   // now 0<month<11
                        dateArray[2] += (dateArray[2] < 90) ? 2000 : 1900; // add proper century to year
                       }
                   } // end of if(!badInput)
               } // end of else
          } // end of while loop
       return dateArray;
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
                              "          &                          \n\n\n");
      }


   // METHODS FROM PART 1 (OBSOLETE)      
   public static void displayDate(int month, int day)   // echoes the month and day to the console based on param vars
      { System.out.println( "Month: " + month + "\nDay: " + day); }
      
   public static int monthFromDate(String date)   // returns the month value as int
      { return Integer.parseInt(date.substring(0, date.indexOf("/"))); }      
      
   public static int dayFromDate(String date)     // returns the day value as int
      { return Integer.parseInt(date.substring(date.indexOf("/")+1)); }
  }