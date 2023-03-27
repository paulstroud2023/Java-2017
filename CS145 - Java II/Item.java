class Item{

   private double price;
   private double discount;
   private int quantity;
   private String title;
   private String desc;
   
   public Item(double p, double d, int q, String n, String t){
      this.price = p;
      this.discount = d;
      this.title = n;
      this.desc = t;
      this.quantity = q;
   }
   
   public int getQuantity(){
      return this.quantity;
   }
   public double getPrice(){
      return this.price;
   }
   
   public double getDiscount(){
      return this.discount;
   }
   
   public String getName(){
      return this.title;
   }
   
   public String getDesc(){
      return this.desc;
   }
}