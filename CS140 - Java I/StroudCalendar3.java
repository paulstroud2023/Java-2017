/* NAME:       PAUL STROUD
   DATE:       20170228
   CLASS:      CS140
   FILENAME:   StroudCalendar3.java
   
   DESCRIPTION:
   Source code for Calendar Assignment - Part 03
*/

/* [CHANGELOG]
   1. drawMonth() and its child methods now output to a 
      PrintStream object to allow printing to file
   2. new menu options added in main(): 'v' to add events, and 'f' to print to file
   3. main() now allows to import events from 'calendarEvents.txt'
   4. fixed the bug with showing today's calendar after choosing another option
*/


import java.util.Scanner;        // import statement for using Scanner objects
import java.util.Calendar;       // import statement for using Calendar objects
import java.io.*;

public class StroudCalendar3
   {
    public static final int   CALENDAR_SIZE = 15,     // size of each calendar cell in characters
                              CELL_HEIGHT = (CALENDAR_SIZE / 2) - 2;   // height of each cal cell (excl. H borders)
    public static final String   WEEKDAYS[] = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" },
                                 MONTHS[] = {   "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE",
                                                "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" };
    public static final int DAYS_IN_MONTH[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };


    public static PrintStream calOutput = System.out;    // output to (console || file)
    public static String[][] eventArray;                 // 2D array to hold events

    public static void main(String[] args)
            throws FileNotFoundException, IOException
      {
       System.out.print("\n\t\tWELCOME TO 600613 CALENDAR!\n\n");
       drawArt();                                     // not sure what this does
       Scanner console = new Scanner(System.in);
       Calendar curDate = Calendar.getInstance();     // create new Calendar obj to access current date
       // extract numeric month/day/year into the array
       int dateIntArray[] = { curDate.get(Calendar.MONTH), curDate.get(Calendar.DATE), curDate.get(Calendar.YEAR) };
       
       eventArray = new String[12][];     // pre-allocate memory for <eventArray>
       for (int i=0; i<12; i++)           // alloc. correct number of days/mo
          {
           curDate.set(dateIntArray[2], i, 1);
           eventArray[i] = new String[curDate.getActualMaximum(Calendar.DATE)];
           for (int j=0; j<eventArray[i].length; j++)
              eventArray[i][j] = "";      // init elmt as empty string
          }
       curDate.set(dateIntArray[2], dateIntArray[0], dateIntArray[1]);  // restore correct date
       
       // import event file
       File ioFile = new File("CAL_DATA/calendarEvents.txt");
       if (ioFile.canRead())     // verify read access
            {
             Scanner fileInput = new Scanner(ioFile);
             while (fileInput.hasNextLine())
                {
                 String temp[] = fileInput.nextLine().split("\t");   // separate date and description
                 int[] eventDate = { monthFromDate(temp[0])-1, dayFromDate(temp[0])-1 };  // grab date values
                 if (temp[1].length() > CALENDAR_SIZE-2)
                   temp[1] = temp[1].substring(0, CALENDAR_SIZE-2);
                 eventArray[eventDate[0]][eventDate[1]] = temp[1];
                }
            }
         else System.out.println("ERROR: Cannot access \'calendarEvents.txt\'. No events will be imported.");    

       char choice;
       // USER INPUT LOOP AKA "THE MENU"
       do {
           System.out.print("\nEnter a command. Type ? for help: ");

           // grab a char from console, then OR 0x20 to ignore case
           choice = (char) (console.nextLine().charAt(0) | 0b00100000);
           if (choice == 't')    // restore today's date if needed - bug fix
             {
              curDate = Calendar.getInstance();
              dateIntArray = new int[] { curDate.get(Calendar.MONTH), curDate.get(Calendar.DATE), curDate.get(Calendar.YEAR) };
             }
             
           switch (choice)    // check the value
            {
             case 'q':     // exit the program
                  break;   
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
                  break;


             // NEW MENU OPTIONS
             case 'f':        // print calendar to file
                  System.out.print("\nPlease enter destination file name: ");
                  String filename = console.nextLine();  // grab filename
                  File outputFile = new File("CAL_DATA/" + filename);
                  outputFile.createNewFile();            // create file as needed
                  if (outputFile.canWrite())             // verify write access
                       {
                        calOutput = new PrintStream(outputFile);  // redirect cal output to file stream
                        
                        // print calendar
                        dateIntArray = inputDate();
                        curDate.set(dateIntArray[2], dateIntArray[0], dateIntArray[1]);
                        drawMonth(curDate);

                        // finish and close file
                        System.out.print("\nSUCCESS! Calendar saved in \'" + filename + "\' inside CAL_DATA folder.");
                        calOutput.close();         // close the file stream
                        calOutput = System.out;    // redirect output back to console
                       }
                     else System.out.print("ERROR 07: Cannot write to file \'" + filename + "\'");
                  break;
             case 'v':     // add event
                  dateIntArray = inputDate();
                  System.out.print("Please enter event name: ");

                  String temp = console.nextLine();
                  if (temp.length() > CALENDAR_SIZE-2)
                    temp = temp.substring(0, CALENDAR_SIZE-2);
                  eventArray[dateIntArray[0]][dateIntArray[1]-1] = temp;
                  break;
             // end of new stuff


             case '?':     // print help
                  System.out.println(  "\t\'e\'\tenter a custom date for display\n" +
                                       "\t\'t\'\tdisplay today's calendar\n" +
                                       "\t\'n\'\tdisplay next month's calendar\n" +
                                       "\t\'p\'\tdisplay previous month's calendar\n" +
                                       "\t\'v\'\tadd a new event to calendar\n" +               // new
                                       "\t\'f\'\tsave calendar to file (prompts for date)\n" +  // new
                                       "\t\'q\'\texit the program\n");
                  break;
             default:   // Warning: PEBKAC
                  System.out.print("ERROR ID.10.T: Please enter a valid command.");
            }  // end of switch
          } while (choice != 'q');  // repeat loop unless <choice> == 'q'
          
       System.out.println("\n\t\tGITT TO ZE CHOPPA NAUWW!!!");
      }

   public static void drawMonth(Calendar calObj)       // draw cal for <calObj>
      {
       int dateVar = calObj.get(Calendar.DATE);
       calObj.set(Calendar.DATE, 1);                              // temp. set date to 1 to find 1st weekday
       int  firstWeekday = calObj.get(Calendar.DAY_OF_WEEK),      // extract day of the week (in Java format)
            daysInMonth = calObj.getActualMaximum(Calendar.DATE); // get num of days in month
       // adj week to start from Monday=0     
       firstWeekday = (firstWeekday == 1)  ?  6 : firstWeekday-2; // if Sunday, set to 6; else -=2
       // calc num of rows (+1 if division was incomplete)
       int numOfRows = ((daysInMonth + firstWeekday) / 7)
                       + ((((daysInMonth + firstWeekday) % 7) == 0)  ?  0 : 1);

       calObj.set(Calendar.DATE, dateVar);   // restore correct date


       // format month name to be centered on the calendar table
       String tmpString = String.format(  "\n%" + (1 + 7 * (CALENDAR_SIZE - 1) / 2) + "s\n",
                                          MONTHS[calObj.get(Calendar.MONTH)] + " " +
                                          calObj.get(Calendar.YEAR) );
       calOutput.printf(tmpString);

                           
       // print weekday names
       for (int i=0; i<7; i++)
          {
           tmpString = String.format("  %-" + (CALENDAR_SIZE-3) + "s", WEEKDAYS[i]);
           calOutput.printf(tmpString);
          }
       calOutput.println();

       
       // DRAW CAL HERE
       for (int i=0; i<numOfRows; i++)    // print cal rows
          drawRow(i, firstWeekday, daysInMonth, dateVar, calObj.get(Calendar.MONTH));
       drawCalBorderH(numOfRows, firstWeekday, daysInMonth);   // complete bottom border

       calOutput.println();  // add an extra blank line afterwards
      }

   public static void drawRow(int row, int firstWeekday, int daysInMonth, int targetDate, int month)    // completes a row of the cal; the top horiz. border MUST BE ALREADY DRAWN
      {
       drawCalBorderH(row, firstWeekday, daysInMonth);      // draw the top horizontal border
       drawCalBorderV(row, firstWeekday, daysInMonth, targetDate, month);      // draw the middle part of the row
      }

   public static void drawCalBorderV(int row, int firstWeekday, int daysInMonth, int targetDate, int month)     // draws the vert borders of the cal row + date, x <days>
      {
       int rowStartDate = row * 7 + 1 - firstWeekday; // calc start date, adj for <firstWeekday>
       int  width = CALENDAR_SIZE - 2;     // the width of each cal cell (excl. borders)
       String tmpString;   // string container for formatted output
       
       for (int i=0; (i<7) && (rowStartDate <= daysInMonth); i++, rowStartDate++)
          {  // while (the row is incomplete) AND (the date doesn't exceed <daysInMonth>)
           if (rowStartDate < 1)   for (int j=0; j<CALENDAR_SIZE-1; j++) calOutput.print(" ");
                  // if the date is zero/neg, print blank spaces
            else   // if the date is valid, print border
             {
              if (rowStartDate == targetDate)
                  tmpString = String.format("|%" + (width-1) + "s ", ":( " + rowStartDate);   // <width-1> allows for extra space between <rowStartDate> and "|"
                else 
                  tmpString = String.format("|%" + (width-1) + "d ", rowStartDate);   // <width-1> allows for extra space between <rowStartDate> and "|"
              calOutput.printf(tmpString);
             }
          } // end of outer for loop
       if (rowStartDate <= daysInMonth+1) calOutput.print("|\n");   // complete border and go to next line
          

       for (int h=0; h<CELL_HEIGHT; h++)   // print blank lines * <CELL_HEIGHT>
          {
           rowStartDate = row * 7 + 1 - firstWeekday; // reinitialize start date

           for (int i=0; (i<7) && (rowStartDate <= daysInMonth); i++, rowStartDate++)
              {  // while (the row is incomplete) AND (the date doesn't exceed <daysInMonth>)
               if (rowStartDate < 1)   for (int j=0; j<CALENDAR_SIZE-1; j++) calOutput.print(" ");
                           // if the date is zero/neg, print blank spaces
                 else      // if the date is valid, print border
                      {          // print the event (if it exists) on the 2nd blank line
                       if ((h == 1) && (eventArray[month][rowStartDate-1].length() != 0))
                           {
                            tmpString = String.format("|%-" + width + "s", eventArray[month][rowStartDate-1]);
                            calOutput.printf(tmpString);    // print event
                           }
                         else    // otherwise, print a blank line
                           {
                            calOutput.print("|");         
                            for (int k=0; k<width; k++)    // print the middle of each cell = (<whitespace> * <width>)
                               calOutput.print(" ");
                           }
                      }
                   }
           if (rowStartDate <= daysInMonth+1) calOutput.print("|\n"); // complete rightmost border and go to next line
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
           if (rowStartDate < 1)   for (int j=0; j<CALENDAR_SIZE-1; j++) calOutput.print(" ");
                  // if the date is zero/neg, print blank spaces
            else  // if the date is valid, print border
             {
              calOutput.print('+');
              for (int j=0; j<CALENDAR_SIZE-2; j++) calOutput.print("-");
             }
          } // end of outer for loop
       // validate <rowStartDate>, else method could print '+' by itself
       if (rowStartDate <= daysInMonth+8)
         calOutput.print("+\n");   // complete border and go to next line
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