import java.util.*;
/*


   Brody Coleman
   Whatcom Community College
   CS145 - Mid-Term Exam main program for problem 01.
   02/15/2017
   
   Simulates building Lego sets.


*/


class SetBuilder{

   public static void main(String[] args){
      ArrayList<LegoSet> Shelf = new ArrayList<LegoSet>();
      
      Shelf.add(new StarWarsSet("Death Star",4016,1000));
      Shelf.add(new StarWarsSet("Hoth",2144,400));
      Shelf.add(new CitySet("Fire Station",919,65));
      Shelf.add(new ClassicSet("Biplane",50,5));
      
      int totalTime = 0;
      for(LegoSet set : Shelf){
          set.build();
          System.out.println(set);
          totalTime += set.getBuildTime();
      }
      
      System.out.println("It took "+ totalTime +" min to build your shelf of Lego Sets.");
   }
}