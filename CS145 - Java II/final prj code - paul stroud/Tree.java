/* NAME:       PAUL STROUD
   DATE:       20170613
   CLASS:      CS145
   FILENAME:   Tree.java
   
   DESCRIPTION:
   Source code for final project (super class)
*/

/*
   TREE SUPERCLASS
   Provides common fields and behaviors for child classes
*/

public class Tree
   {
    private int age;
    private char treeSymbol;
    private double growthSpeed, height, diameter;
    private String name;
    public Tree(String name, char treeSymbol, double growthSpeed)
      {
       this.treeSymbol = treeSymbol;
       this.name = name;
       this.growthSpeed = growthSpeed;
      }
    public char getSymbol()
      {
       return this.treeSymbol;
      }
    public String getName()
      {
       return this.name;
      }
    public double getHeight()
      {
       return this.height;
      }
    public String toString()
      {
       String[] tmpArray = new String[3];
       char tmpSymbol = getSymbol();
       for (int i=0; i<3; i++)
          {
           tmpArray[i] = "";
           for (int j=0; j<i*2+1; j++)
              tmpArray[i] += tmpSymbol;
          }
       return "\n  ." + tmpArray[0] + ".  " +
              "\n ." + tmpArray[1] + ". " +
              "\n " + tmpArray[2] + " " +
              "\n   I   " +
              "\n   I   " +
              "\n Name: " + this.getName() +
              "\n Age: " + this.age +
              "\n Height: " + this.height +
              "\n Diameter:" + this.diameter;
      }
   }