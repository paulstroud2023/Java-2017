public class CalcPI
   {
    public static void main(String[] args)
      {
       long hits=0, total=100000000;
       double myPI, x, y, R = 40.0;
       System.out.println("Approximating Pi using " + total + " points...");
       for (long i=0; i<total; i++)
          {
           x = R * Math.random(); y = R * Math.random();
           if (x*x + y*y < R*R) hits++;
          }
       System.out.println("\tPI = " + (myPI = 4.0 * hits / total));
       System.out.println("\tMath.PI = " + Math.PI);
      }
   }
// it takes around 100,000,000 points to accurately approximate to 4 decimal places