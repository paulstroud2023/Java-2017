import java.util.*;
import java.io.*;

public class Lab03
   {
    public static void main(String[] args)
            throws FileNotFoundException, IOException
      {
       Scanner fileInput = new Scanner(new File("Items.txt"));
       DiscountBill splurge = new DiscountBill();
//       GroceryBill splurge = new GroceryBill();
       String[] s;
       while (fileInput.hasNextLine())
          {
           s = fileInput.nextLine().split(" ");
           Item tmpItem = new Item( Double.parseDouble(s[3]),  // price
                                    Double.parseDouble(s[4]),  // discount
                                    Integer.parseInt(s[0]),    // quantity
                                    s[1], s[2]);               // name and description
           splurge.add(tmpItem);
          }
       splurge.printReceipt();
      }
   }