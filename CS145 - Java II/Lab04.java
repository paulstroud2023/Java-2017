/* NAME:       PAUL STROUD
   DATE:       20170511
   CLASS:      CS145
   FILENAME:   Lab04.java
   
   DESCRIPTION:
   Source code for Lab 04
*/
import java.util.*;

class Lab04
   {
    public static void main(String[] args)
      {
       //      PROBLEM 11.3
       List<Integer> list1 = new LinkedList<Integer>();
       // source array
       int[] sourceList1 = { 3, 7, 1, 0, 1, 4, 5, 4, 6, 5, 6, 9 };
       for (int i=0; i<sourceList1.length;)  // create list from array
          list1.add(sourceList1[i++]);
       System.out.println("\nList1 before: " + list1);
       removeInRange(list1, 5, 5, 11);
       System.out.println("List1 after: " + list1);
       
       //      PROBLEM 11.10
       Set<String> set2 = new HashSet<String>();
       // source array
       String[] sourceSet2 = {"wasd", "zxcvbn", "1", "h67", "j", "erty", "qw", "abc"};
       for (int i=0; i<sourceSet2.length;)   // create set from array
          set2.add(sourceSet2[i++]);
       System.out.println("\nSet2 before: " + set2);
       removeEvenLength(set2);
       System.out.println("Set2 after: " + set2);
       
       //      PROBLEM 11.13
       Map<String, String> map3 = new HashMap<String, String>();
       String[][] sourceMap3 = { { "potato", "banana" },
                                 { "tomato", "banana" },
                                 { "onion", "raspberry" },
                                 { "pepper", "orange" },
                                 { "broccoli", "apple" } };
       for (int i=0; i<sourceMap3.length; i++)
          map3.put(sourceMap3[i][0], sourceMap3[i][1]);
       System.out.println("\nMap3 = " + map3);
       System.out.println("All mappings unique?\t" + (isUnique(map3) ? "YES" : "NO"));
       
       //      PROBLEM 11.19
       Map<String, Integer> map4 = new HashMap<>();
       String[] strSrcMap4 = { "qwerty", "wasd", "zxcvb", "123456", "xyz987",
                               "a", "o", "7", "?", ":(",
                               "@", "Z", ".", "y", "!"};
       int[] intSrcMap4 = {   1, 2, 3, 2, 5,
                              3, 3, 9, 1, 1,
                              1, 5, 9, 2, 8 };
       for (int i=0; i<strSrcMap4.length; i++)
          map4.put(strSrcMap4[i], intSrcMap4[i]);
       System.out.println("\nMap4 = " + map4);
       System.out.println("The rarest element is: " + rarest(map4));
      }
    
    // remove the <value> from the <list> within the range between <start> and <end>  
    public static void removeInRange(List<Integer> list, Integer value, int start, int end)
      {
       if (start >= end) System.out.println("Incorrect range.");
       if (start < 0) System.out.println("Incorrect value: start of the range.");
       if (end > list.size()-1) System.out.println("Incorrect value: end of the range.");
       for (int i=start; i<end;)
          {
           if (list.get(i) == value)   // test if current element equals <value>
               { list.remove(i); end--; }    // if yes, remove it and adj. <end>
             else i++;                       // if not, go to next elem.
          }
      }

    // removes strings of even length from the <set>
    public static void removeEvenLength(Set<String> set)
      {
       Iterator<String> x = set.iterator();  // create an iterator <x> for the set
       while (x.hasNext())          // while <x> has elements
          {
           String tmp = x.next();   //  pull next elem.
           // if length is even, remove it
           if (tmp.length() % 2 == 0) x.remove();
          }
      }

    public static boolean isUnique(Map<String, String> map)
      {
       // make a list of mapped values
       List<String> valList = new LinkedList<String>();  // value list
       for (String i : map.keySet())   
          valList.add(map.get(i));
       
       // iterate over the list
       Iterator<String> x = valList.iterator();
       while (x.hasNext())
          {
           // remove next elem. and compare to list
           String tmp = x.next();
           x.remove();
           if (valList.contains(tmp)) return false;   // if duplicate found
          }
 
       // if all checks out
       return true;
      }
      
    // return the int that occurs the least in the <map>
    public static int rarest(Map<String, Integer> map)
      {
       if (map.isEmpty())
         throw new IllegalArgumentException("Method rarest() cannot process an empty map.");

       List<Integer> valList = new LinkedList<Integer>();         // temp list for values
       TreeMap<Integer, Integer> duplicates = new TreeMap<>();    // temp tree map to hold count->value mappings
       // make a list of original values
       for (String i : map.keySet())
          valList.add(map.get(i));
       
       Collections.sort(valList);      // ascending sort
       Collections.reverse(valList);   // reverse order
       
       // iterate over the list (largest to smallest)
       while (valList.size() != 0)
          {
           int tmpVal = valList.get(0), tmpCount = 1;    // pull the first value, set count to 1
           valList.remove(0);                            // remove value from the list
           while (valList.contains(tmpVal))              // if more occurences
             {
              tmpCount++;
              valList.remove((Integer) tmpVal);          // remove duplicates
             }
           duplicates.put(tmpCount, tmpVal);             // add count+value to temp tree map
          }

       // first entry will be least occurence + least int
       return duplicates.get(duplicates.firstKey());     // return mapped value
      }
   }