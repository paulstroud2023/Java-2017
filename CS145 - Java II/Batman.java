class Batman implements Animal
   {
    private Point position;
    private double HP;
    private double armor = 0.25;
    
    public Batman(double HP)
      {
       position = new Point((int)Math.round(Math.random()*7), (int)Math.round(Math.random()*7));
       this.HP = HP;
      }
    
    public void attack(Animal opponent)
      {
       opponent.setHP((int) 0.5 * opponent.getHP());
      }
    public Point move()
      {
       int size = AnimalFight.SIZE;
       int offsetX = location.getX()+(int)Math.round(Math.random()*3-3);
       int offsetY = location.getY()+(int)Math.round(Math.random()*2-1);
       offsetX = (offsetX+size)%size;
       offsetY = (offsetY+size)%size;
       this.location = new Point(offsetX,offsetY);
       System.out.println("Batman fights crimes @ "+location.getX()+", "+location.getY());
       return this.location;
      }
    public int getHP()
      {
       return (int) this.HP;
      }
    public void setHP(int amount)
      {
       this.HP -= (1.0 - armor) * amount;
      }
    public Point getLocation()
      {
       return this.position;
      }
    public String toString()
      {
       return "HP: " + this.HP + " @ " + this.position + " ... BECAUSE I'M BATMAN";
      }
    public String die()
      {
       return "Alfred, there's crime everywhere... Batman.";
      }
   }