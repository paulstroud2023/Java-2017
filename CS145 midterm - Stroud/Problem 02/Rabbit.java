/* 
   Paul Stroud
   Joseph Siler
   CS 145
   Midterm
   5/9/2017
*/

public class Rabbit extends Racer {
   public int napTurn;

   public Rabbit (String name, int howFast, int napTurn) {
       super(name, howFast);
       this.napTurn = napTurn;
   }
   
   public void move() {
      if(napTurn > 0) {
         napTurn--; 
      } else {
         this.position+=this.speed;
      }  
   }
   
   
}