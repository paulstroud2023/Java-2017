public class CitySet implements LegoSet
   {
    private String name;
    private int pieces;
    private int buildTime;
    public CitySet(String name, int pieces, int buildTime)
      {
       this.name = name;
       this.pieces = pieces;
       this.buildTime = buildTime;
      }
    public void build()
      {
       System.out.println("Brick by brick by brick");
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