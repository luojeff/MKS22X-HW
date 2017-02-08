import java.lang.IllegalArgumentException;

public class Recursion {
    public static String name(){
	String name = "Luo,Jeffrey";
	return name;
    }
    
    public static double sqrt(double n){
        if(n <= 0){
	    if(n == 0)
		return n;
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
