/* NAME:          PAUL STROUD
   DATE:          20170608
   CLASS:         CS145
   FILENAME:      AssassinManager.java
   DESCRIPTION:   Source code for Linked Nodes lab */

import java.util.*;

class AssassinManager
   {
    ArrayList<AssassinNode> hitmen = new ArrayList<AssassinNode>();        // array list for the kill ring
    ArrayList<AssassinNode> goodSamurai = new ArrayList<AssassinNode>();   // array list for the graveyard
    public AssassinManager(List<String> names)     // class constructor using a list of String names
      {
       if (names == null || names.size() == 0)
         throw new IllegalArgumentException();
       Iterator iter = names.iterator();
       AssassinNode ptr = null;
       while (iter.hasNext())    // parse the list and create linked nodes
          {
           AssassinNode tmp = new AssassinNode((String) iter.next());
           if (ptr != null)
             { ptr.next = tmp; }
           hitmen.add(tmp);
           ptr = tmp;
          }
       ptr.next = hitmen.get(0); // loop around to the first element
      }
    public void printKillRing()
      {
       Iterator iter = hitmen.iterator();
       while (iter.hasNext())
          {
           AssassinNode tmp = (AssassinNode) iter.next();
           System.out.print("\n" + tmp.name + " -> " + tmp.next.name);
          }
       System.out.print("\n\n");
      }
    public void printGraveyard()
      {
       Iterator iter = goodSamurai.iterator();
       while (iter.hasNext())
          {
           AssassinNode tmp = (AssassinNode) iter.next();
           System.out.print("\n" + tmp.name + " <= X( " + tmp.killer);
          }
       System.out.print("\n\n");
      
      }
    public boolean killRingContains(String name)
      {
       Iterator iter = hitmen.iterator();
       while (iter.hasNext())
          {
           if (((AssassinNode) iter.next()).name.equalsIgnoreCase(name))
             return true;
          }
       return false;
      }
    public boolean graveyardContains(String name)
      {
       Iterator iter = goodSamurai.iterator();
       while (iter.hasNext())
          {
           if (((AssassinNode) iter.next()).name.equalsIgnoreCase(name))
             return true;
          }
       return false;
      }
    public boolean isGameOver()
      {
       if (hitmen.get(0).next == hitmen.get(0)) // if node[0] is the only elem. (points to self)
         return true;
       return false;
      }
    public String winner()
      {
       if (!isGameOver()) return null;
       return hitmen.get(0).name;
      }
    public void kill(String name)
      {
       if (isGameOver())
         throw new IllegalStateException();
       if (!killRingContains(name))
         throw new IllegalArgumentException();

       Iterator iter = hitmen.iterator();
       AssassinNode target = null;  // kill target
       while (iter.hasNext())    // iterate over the array list
          {
           AssassinNode tmp = (AssassinNode) iter.next();
           if (tmp.next.name.equalsIgnoreCase(name))  // if next node is the target
             {
              target = tmp.next;             // save the target
              target.killer = tmp.name;      // record the killer
              goodSamurai.add(target);       // add target to graveyard
              tmp.next = tmp.next.next;      // adjust the next pointer for killer
              break;                         // break out of the loop
             } // end if
          } // end while
       hitmen.remove(target);    // remove target from the kill ring
      } // end kill()
   }