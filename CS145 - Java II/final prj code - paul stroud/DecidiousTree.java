/* NAME:       PAUL STROUD
   DATE:       20170613
   CLASS:      CS145
   FILENAME:   DecidiousTree.java
   
   DESCRIPTION:
   Source code for final project (subclass 2)
*/

public class DecidiousTree
      extends Tree implements FruitTree
   {
    //   FIELDS
    private static final String[] leafSizes = { "small", "medium", "large" };
    private String leafShape, fruit;
    private int leafSize;
    private boolean leafy,                // holds the leaf state
                    edibleFruit=false;    // false by default, unless changed by constructor

    //   METHODS
    // constructor for fruit trees
    public DecidiousTree(String name, char treeSymbol, double growthSpeed, String leafShape, int leafSize, String fruit)
      {
       // call constructor for a non-fruit tree (to avoid redundancy)
       this(name, treeSymbol, growthSpeed, leafShape, leafSize);
       // add fruit info to the object
       this.fruit = fruit;
       this.edibleFruit = true;
      }
    // constructor overload for non-fruit trees
    public DecidiousTree(String name, char treeSymbol, double growthSpeed, String leafShape, int leafSize)
      {
       super(name, treeSymbol, growthSpeed);    // call parent constructor (from Tree class)
       // add leaf info
       this.leafShape = leafShape;
       this.leafSize = leafSize;
      }
      
    
    public void bloom()    // simulates tree blooming, if applicable
      {
       System.out.print("\n" + getName() + (edibleFruit ? " is blossoming..." : " does not blossom."));
      }
    public String harvestFruit()    // returns the type of fruit for this tree, if applicable
      {
       return edibleFruit ? fruit : "no fruit";
      }
    public void leafOut()     // simulate leafing out in the Spring
      {
       if (!leafy)   // do nothing if already leafy
         {
          System.out.print("\n" + getName() + ": Fresh green leaves - so nice!");
          leafy = true;
         }
      }
    public void shedLeaves()  // simulate defoliating in the Fall
      {
       if (leafy)    // do nothing if not leafy
         {
          System.out.print("\n" + getName() + ": Leaves are turning gold and red, and falling down.");
          leafy = true;
         }
      }
    public String toString() // override parent toString method
      {
       // call parent method and add child class info
       return  super.toString() +
               "\n Leaf shape: " + this.leafShape +
               "\n Leaf size: " + leafSizes[this.leafSize] +
               (edibleFruit ? ("\n Fruit: " + this.fruit) : "");
      }
   }