import java.io.*;
import java.util.*;

class Lab07Star
   {
    public static void main(String[] args)
            throws FileNotFoundException
      {
       File f = new File("StarData.txt");
       int starCount=0;
       if (f.canRead())
            {
             Scanner tmpScanner = new Scanner(f);
             while (tmpScanner.hasNextLine())
               {
                starCount++;
                tmpScanner.nextLine();
               }
            }
         else System.out.print("ERROR: Can't read the input file.");
       Star starArray[] = new Star[starCount];
       
       Scanner fileInput = new Scanner(f);
       for (int i=0; i<starCount; i++)
          {
           String starInfo[] = fileInput.nextLine().split("[|]");
           
           starArray[i] = new Star(starInfo[0], starInfo[1], Double.valueOf(starInfo[2]));
          }
       
       // SMACKDOWN!
       Star hulkSmash = starArray[0];
       for (int i=1; i<starCount; i++)
          hulkSmash = hulkSmash.collide(starArray[i]);
       
       System.out.println("The big collapse has created:\n" + hulkSmash);
      }
   }