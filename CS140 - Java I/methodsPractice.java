/* NAME:       PAUL STROUD
   DATE:       20160119
   CLASS:      CS140
   FILENAME:   methodPractice.java
   
   DESCRIPTION:
   Source code for Lab 02 b - Method Practice
*/

class methodsPractice{
   //mainMethod
   public static void main(String[] args){
      specialPrint("beedo",14,"[]^[]");
      System.out.println("\nThere are " + numContain("lmc","trylmctolmclmcFindHowlmcManylmcexistlmchere") + " instances");
   }
   
   //print out the content string num quantity of times follow but ' and '
   // + the end string
   public static void specialPrint(String content, int num, String end){
      for (int i=0; i<num; i++)
         System.out.print(content+' ');
      System.out.println(end);
   }
   
   //Find how many times find occurs within body
   public static int numContain(String find, String body){

      int count = 0, loopCount = body.length()-find.length();  // new variable to get the number of iterations   
      for (int i=0; i < loopCount; i++)
         if (find.equals(body.substring(i, i+find.length())))  // extract a substring of "find.length" size from "body" and compare to "find"
           count++;  // if true, increment count
      
      return count;
   }
}