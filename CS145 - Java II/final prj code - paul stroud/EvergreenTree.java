/* NAME:       PAUL STROUD
   DATE:       20170613
   CLASS:      CS145
   FILENAME:   EvergreenTree.java
   
   DESCRIPTION:
   Source code for final project (subclass 1)
*/

public class EvergreenTree
      extends Tree implements FruitTree
   {
    private String fruit;
    private int needleLength;
    public EvergreenTree(String name, char treeSymbol, double growthSpeed, int needleLength)
      {
       super(name, treeSymbol, growthSpeed);
       this.needleLength = needleLength;
       this.fruit = "cones";
      }
    public void bloom()
      {
       System.out.print("\n" + getName() + " is growing " + this.fruit + ". This is cone-tastic!");
      }
    public String harvestFruit() // return "fruit"
      {
       return "nuts! (They make squirrels happy)";
      }
    public void growShoots()  // simulate growing fresh shoots in the Spring
      {
       System.out.print("\n" + getName() + ": Oh shoot, these are so green and soft!");
      }

    // duplicate method, calling back to bloom()
    // could be used for additional processing in a more elaborate setup
    public void growCones()
      {
       this.bloom();
      }
      
    public String toString() // override parent toString method
      {
       // call parent method and add child class info
       return super.toString() + 
              "\n Needle length: " + this.needleLength;
      }
   }