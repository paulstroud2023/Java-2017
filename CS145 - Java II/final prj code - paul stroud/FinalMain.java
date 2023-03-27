/* NAME:       PAUL STROUD
   DATE:       20170613
   CLASS:      CS145
   FILENAME:   FinalMain.java
   
   DESCRIPTION:
   Source code for final project (main)
*/


import java.util.*;

class FinalMain
   {
    public static void main(String[] args)
      {
       System.out.print("\n   WELCOME TO THE TREE FARM" +
                        "\nLet's plant some trees!\n");
       Scanner console = new Scanner(System.in);
       
       ArrayList<Tree> trees = new ArrayList<Tree>();
       for (int i=1; i<4; i++)
          {
           System.out.print("\n___ Tree #" + i + " ___\nEvergreen/decidious? (e/d): ");
           char type = console.next().charAt(0);
           System.out.print("\nTree name (string): ");
           String name = console.next();
           System.out.print("\nTree symbol (char): ");
           char sym = console.next().charAt(0);
           System.out.print("\nGrowth speed (double 0 to 1): ");
           double speed = console.nextDouble();

           // get more info and create a selected tree object
           if (type == 'd')   // decidious
                 {
                  System.out.print("\nLeaf shape (string): ");
                  String lShape = console.next();
                  System.out.print("\nLeaf size (int 0 to 2): ");
                  int lSize = console.nextInt();
                  System.out.print("\nFruit type (string or 0 if none): ");
                  String fruit = console.next();
                  if (fruit.charAt(0) == '0')   // no fruit
                     { trees.add(new DecidiousTree(name, sym, speed, lShape, lSize)); }
                    else   // has fruit
                     { trees.add(new DecidiousTree(name, sym, speed, lShape, lSize, fruit)); }
                 }
             else if (type == 'e')  // evergreen
                 {
                  System.out.print("\nNeedle length (int): ");
                  int nLength = console.nextInt();
                  trees.add(new EvergreenTree(name, sym, speed, nLength));
                 }
          }
       
       System.out.print("\n\nWe now have the following trees:");
       
       for (int i=0; i<3; i++)   // print out all trees
          System.out.println(trees.get(i));
       
       char choice = 'a';
       while (choice != 'q')  // loop menu
          {
           System.out.print("\n\n______________________________" +
                            "\nLet's pick a time of the year... (s/f/q) : ");
           choice = console.next().charAt(0);
           if (choice == 's') // spring time
               {
                System.out.print("\n\n   [ SPRING ]");
                for (int i=0; i<3; i++)
                   {
                    if (trees.get(i) instanceof DecidiousTree) // decidious behavior
                        {
                         ((DecidiousTree) trees.get(i)).leafOut();
                         ((DecidiousTree) trees.get(i)).bloom();
                        }
                      else    // evergreen behavior
                        {
                         ((EvergreenTree) trees.get(i)).growShoots();
                         ((EvergreenTree) trees.get(i)).bloom();
                        }
                   }
               }
             else if (choice =='f') // fall time
               {
                System.out.print("\n\n   [ FALL ]");
                for (int i=0; i<3; i++)
                   {
                    if (trees.get(i) instanceof DecidiousTree) // decidious tree behavior
                        {
                         ((DecidiousTree) trees.get(i)).shedLeaves();
                         System.out.print("\n" + trees.get(i).getName() + " tree produced " +
                                          ((DecidiousTree) trees.get(i)).harvestFruit());
                        }
                      else // evergreen behavior
                        {
                         System.out.print("\n" + trees.get(i).getName() + " tree produced " +
                                          ((EvergreenTree) trees.get(i)).harvestFruit());
                        }
                   }
               }
          }

       System.out.print("\nExiting... Have a nice day!");
      }
   }