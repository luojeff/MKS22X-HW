import java.lang.IllegalArgumentException;

public class Recursion {
    public static void main(String[] args){
	System.out.println("Square Root: " + sqrt(16));
	System.out.println("Square Root: " + sqrt(100));
	System.out.println("Square Root: " + sqrt(1234432112));
	//System.out.println("Square Root: " + sqrt(-1));
    }

    public static String name(){
	String name = "Luo,Jeffrey";
	return name;
    }
    
    public static double sqrt(double n){
        if(n < 0){
	    throw new IllegalArgumentException();
	}
        return sqrtHelper(n, 1);
    }

    public static double sqrtHelper(double n, double guess){
	if(isCloseEnough(guess * guess, n, 0.00000001)){
	    return guess;
	} else {
	    double new_guess = (n / guess + guess) / 2;
	    return sqrtHelper(n, new_guess);
	}
    }

    public static boolean isCloseEnough(double a, double b, double epsilon){
	return Math.abs(a - b)/Math.max(a, b) < epsilon;
    }
}
