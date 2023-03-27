/* NAME:          PAUL STROUD
   DATE:          20170417
   CLASS:         CS145
   FILENAME:      Lab02.java
   DESCRIPTION:   Source code for Lab 02 */

import java.io.*;

public class Lab02
   {
    public static void main(String[] args)
         throws Exception
      {
       File imgFile = new File("imgURL.txt"),
            urlFile = new File("httpURL.txt");
       imgFile.createNewFile();            // create file as needed
       urlFile.createNewFile();
       if (imgFile.canWrite() && urlFile.canWrite())  // verify write access
            {
             String targetURL = "https://www.wwu.edu/";
             System.out.print("\nDownloading HTML code for " + targetURL + "... ");
             String htmlCtnr = urlStartWork.doRequest(targetURL);
             System.out.print("DONE");
             
             String[] strArr1 = htmlCtnr.split("href=\"");  // extract the beginning of URLs

             System.out.print("\n\nExtracting HTTP links... ");
             PrintStream console = System.out;     // save console output stream
             System.setOut(new PrintStream(urlFile));       // set output for http links
             for (int i=0; i<strArr1.length; i++)
                {
                 // if the string is an http/https link
                 if (strArr1[i].substring(0, 7).equals("http://") || strArr1[i].substring(0, 8).equals("https://"))
                   System.out.println(strArr1[i].split("\"")[0]); // cut off excess HTML code and print to file
                }
             System.setOut(console);   // reset output to console
             System.out.print("DONE");
             
             System.out.print("\n\nExtracting image links... ");
             System.setOut(new PrintStream(imgFile));    // set output for image links
             strArr1 = htmlCtnr.split("img\\s+src=\"");  // extract image links
             for (int i=0; i<strArr1.length; i++)
                {
                 // if the string is an image link, extract the URL and print to file
                 if (strArr1[i].substring(0, 7).equals("http://") || strArr1[i].substring(0, 8).equals("https://"))
                   System.out.println(strArr1[i].split("\"")[0]); // cut off excess HTML code and print to file
                }
             System.setOut(console);   // reset output to console
             System.out.print("DONE");
            }
         else System.out.print("ERROR: Cannot write to file");
      }
   }