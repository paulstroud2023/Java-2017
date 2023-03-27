/* NAME:       PAUL STROUD
   DATE:       20160111
   CLASS:      CS140
   FILENAME:   loopPractice.java
   
   DESCRIPTION:
   Source code for Lab 02 - Loop Practice
*/

class loopPractice{

   public static void main(String[] args){
      System.out.println("Problem 1:");
      countDown3();
      
      System.out.println("Problem 2:");
      countDownX(43,5);
      countDownX(108,4);
      
      System.out.println("Problem 3:");
      printABC();
      
      System.out.println("Problem 4:");
      System.out.println(pow(6,4));
      System.out.println(pow(2,10));
      
      System.out.println("Problem 5:");
      decCount();
      
      System.out.println("Problem 6:");
      printBox(15);
      
   }
   
   //Change the functionality of the folowing function.
   //Cause it to count down from 19 by threes. 
   //Use a for loop and print all numbers on the same line. Separate the numbers by a space
   public static void countDown3(){
       int start = 19;
       //Begin edit.
       System.out.print("\nCounting down from 19 in increments of 3:\n\t");       
       for (; start >=0; start -= 3)
          {
           System.out.print(start + " ");
          }
       System.out.print("\n\n");
       //End edit.
      }
   
   //Change the functionality of the folowing function.
   //Cause it to count down from start by x. 
   //Use a for loop and print all numbers on the same line. Separate the numbers by a space
   public static void countDownX(int start,int x){
      //Begin edit.
       System.out.print("\nCounting down from " + start + " in increments of " + x + ":\n\t");       
       for (; start >=0; start -= x) System.out.print(start + " ");
       System.out.print("\n\n");
      
      //End edit.
   }
   
   //After running this program the first time, You should see that the line in the edit block
   //prints the 'a' character. Use this to print out the entire alphabet. 
   public static void printABC(){
      //Start edit.
         System.out.print("\nAlphabet here:\n\t");
         for (int i=0; i<26; System.out.print(((char)(97 + i++)) + " "));
         System.out.print("\n\n");
      //End edit.
      System.out.println();
   }
   
   //In the edit block, write a loop where total = a^b by the end of the loop.
   public static int pow(int a, int b){
      int total = a;
      //Begin edit.
      
      System.out.print("\n" + a + " to the power of " + b + " is:\n\t");
      for (; b>1; b--) total *= a; // multiply "total" by "a", do it "b" times

      //End edit.
      return total;
   }
   
   //This method will print out the sequence 999999999888888887777777666666555554444333221.
   // It will be useful to use nested for loops to solve this problem.
   public static void decCount(){
      //Begin edit.
      
      System.out.print("\nThe sequence is:\n\t");
      for (int i=9; i>0; i--) // go over each number
         for (int j=i; j>0; j--) // print it "i" number of times
            System.out.print(i);
      System.out.print("\n\n");
      
      //End edit.
   }
   
   //The following method prints a box. However it is not scalable by size.
   //Every run of the function the box will be the same size and the size
   //variable will not effect it.
   //
   //Together in class we will come up with a solution using nested for loops
   //that will allow us to resize the box.
   public static void printBox(int size){
      //Begin edit.
      
      System.out.println("Drawing a " + size + "x" + (size/2) + " box");
      
      for (int i=0; i<size; i++) System.out.print("*"); // first line
      System.out.println();  // move to the next line

      for (int i=0; i<size/2; i++) // print each row of the box
         {
          System.out.print("|");
          for (int j=0; j<size-2; j++) System.out.print(" ");
          System.out.print("|\n");
         }
      
      for (int i=0; i<size; i++) System.out.print("*"); // last line
      System.out.println();

      //End edit.      
   }
}