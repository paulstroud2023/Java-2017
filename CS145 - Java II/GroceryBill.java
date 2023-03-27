import java.util.*;

public class GroceryBill implements Bill
   {
    ArrayList<Item> itemList = new ArrayList<Item>();
    public void add(Item i)
      {
       itemList.add(i);
      }
    public double getTotal()
      {
       if (itemList.size() == 0)
         return 0.0;
       double total = 0;
       for (int i=0, num=itemList.size(); i<num; i++)
          total += itemList.get(i).getQuantity() * itemList.get(i).getPrice();
       return total;
      }
    public void printReceipt()
      {
       if (itemList.size() == 0)
            System.out.println("This is not the receipt you're looking for...");
         else
            {
             System.out.print("\nTHE GROCERY BILL IS:");
             for (int i=0, num=itemList.size(); i<num; i++)
                {
                 Item tmp = itemList.get(i);
                 System.out.print("\n  " + tmp.getName() +
                                 "\n    " + tmp.getDesc() +
                                 "\n    PRICE: $" + tmp.getPrice() +
                                 "\n    QTY:   " + tmp.getQuantity());
                } // end of for
             System.out.print("\nTHE TOTAL IS: $" + this.getTotal());
            } // end of else
      } // end of printReceipt
   }