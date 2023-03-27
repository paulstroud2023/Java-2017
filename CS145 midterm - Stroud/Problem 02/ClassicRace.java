import java.util.*;
/*


   Brody Coleman
   Whatcom Community College
   CS145 - Mid-Term Exam main program for problem 02.
   02/15/2017
   
   Simulates the classic Rabbit vs Turtle race. Who wins?


*/
class ClassicRace{

  
   public static void main(String[] args){
      int finishLine = 40;   
      System.out.println("On which turn would you like the rabit to take a nap?");
      int napTurn = (new Scanner(System.in)).nextInt();
      
      Racer slowPoke = new Turtle("Fred",2);
      Racer soFast = new Rabbit("Sam",5,napTurn);
      
      while(slowPoke.position < finishLine && soFast.position < finishLine){
         slowPoke.move();
         if(slowPoke.position >= finishLine){
            slowPoke.wins();
         }else{
            soFast.move();
            if(soFast.position >= finishLine){
               soFast.wins();
            }
         }
      }
      System.out.println("The Race concludes.");
   }
}