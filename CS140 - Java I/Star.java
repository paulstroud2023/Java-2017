public class Star
   {
    private String   name,       // star name
                     stellarDsg; // stellar designation
    double magnitude;
    

    // class constructor
    public Star(String name, String desig, double magn)
      {
       this.name = name;
       this.stellarDsg = desig;
       this.magnitude = magn;
      }

    
    public double getMagnitude()
      { return magnitude; }
      
    public Star collide(Star bang)
      {
       // random seed to create black hole
       int random = (int) (1000.0 * Math.random());
       
       // 2.5% chance of black hole, or either star is already a black hole
       if (random > 975 || this.name.equals("BLACK HOLE") || bang.name.equals("BLACK HOLE"))
         return new Star("BLACK HOLE", "FU8AR", -0.0001);
       
       
       // otherwise, combine the two stars
       // shorten the name if this is an original star
       if (!this.name.contains("/")) this.name = this.name.substring(0,3);
       return new Star( this.name + "/" + bang.name.substring(0,3),
                        this.stellarDsg + "-" + bang.stellarDsg.substring(0,3),
                        this.magnitude + bang.magnitude );
      }

    
    public String toString()
      {
       return "[ " + name + ";   " + stellarDsg + ";   " + magnitude + " ]";
      }
    
    
   }