public class ExamCode2
   {
    public static void main(String[] args)
      {
       revString("abcQWNRUIOQWde 123   !@#");
      }
    public static void revString(String phrase)
      {
       int spaceCount=0;
       String newString = "";
       for (int i=phrase.length()-1; i>=0; i--)
          {
           if (phrase.charAt(i) == ' ') spaceCount++;
           newString = newString + phrase.charAt(i);
          }
       System.out.println(newString + "\nThere are " + spaceCount + " spaces in this phrase.");
      }
   }