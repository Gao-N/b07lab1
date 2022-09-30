import java.io.File;

public class Driver { 
	 public static void main(String [] args) throws Exception{ 
	  Polynomial p = new Polynomial();
//	  File f = new File("input");
//	  f.createNewFile();
//	  Polynomial n = new Polynomial(f);
//	  System.out.println(n.evaluate(1));
//	  System.out.println(p.evaluate(3)); 
	  double [] c1 = {6,5}; 
	  int [] exp1 = {0,3};
	  Polynomial p1 = new Polynomial(c1,exp1); 
	  double [] c2 = {-2,-9};
	  int [] exp2 = {1,4};
	  Polynomial p2 = new Polynomial(c2, exp2); 
	  Polynomial s = p1.add(p2);
	  System.out.println("s(0.1) = " + s.evaluate(0.1)); 
	  Polynomial q = p1.multiply(p2);
	  System.out.println(q.evaluate(1));
	  if(s.hasRoot(1))
	   System.out.println("1 is a root of s"); 
	  else 
	   System.out.println("1 is not a root of s"); 
	 } 
} 