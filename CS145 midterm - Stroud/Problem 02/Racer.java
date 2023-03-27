/*
    Brody Coleman
    Whatcom Community College
    Mid-Term Exam Base Racer Class.

    
*/

class Racer{
   public int speed;
   public String title;
   public int position;
   
   public Racer(String name, int howFast){
      this.speed = howFast;
      this.title = name;
      this.position = 0;
   }
   
   public void move(){
      this.position+=this.speed;
   }
   
   public void wins(){
      System.out.println(this.title + " the Racer wins with a final position of "+this.position+".");
   }
}