/* NAME:       PAUL STROUD
   DATE:       20170613
   CLASS:      CS145
   FILENAME:   FruitTree.java
   
   DESCRIPTION:
   Source code for final project (interface)
*/

/*
   FRUITTREE INTERFACE
   Specifies behaviors applicable to fruit trees
   Implemented by child classes of the Tree class
*/

interface FruitTree
   {
    public void bloom();            // simulate blooming
    public String harvestFruit();   // return String as fruit; could return an object in a more complex scenario
   }