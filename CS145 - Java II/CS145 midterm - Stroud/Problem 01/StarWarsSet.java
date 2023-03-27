public class StarWarsSet implements LegoSet
   {
    private String name;
    private int pieces;
    private int buildTime;
    public StarWarsSet(String name, int pieces, int buildTime)
      {
       this.name = name;
       this.pieces = pieces;
       this.buildTime = buildTime;
      }
    public void build()
      {
       System.out.println("These are not the Legos you're looking for...");
      }
    public int getBuildTime()
      {
       return this.buildTime;
      }
    public String toString()
      {
       return String.format("Set: %-12s  |  pieces: %6d  |  build time: %6d", name, pieces, buildTime);
      }
   }