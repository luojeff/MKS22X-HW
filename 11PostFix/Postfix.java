import java.util.Stack;
import java.lang.*;

public class Postfix {
    public static String[] tokens;
    public static Stack<Double> tokenStack;

    public static boolean isOp(String token){
	String[] validOps = {"+","-","*","/","%"};
	boolean contained = false;

        for(String op : validOps){
	    if(op.equals(token)){
		contained = true;
	    }
	}

	return contained;
    }

    public static  double calculate(String op, double a, double b){
	double result;
	
        switch(op){
	case "+":
	    result = b + a;
	    return result;	    
	case "-":
	    result = b - a;
	    return result;
	case "*":
	    result = b * a;
	    return result;
	case "/":
	    result = b / a;
	    return result;
	case "%":
	    result = b % a;
	    return result;
	default:
	    throw new IllegalArgumentException();
	}
    }

    public static double eval(String exp){
	tokenStack = new Stack<Double>();
	tokens = exp.split(" ");

	for(String s : tokens){
	    if(isOp(s)){
		tokenStack.push(calculate(s, tokenStack.pop(), tokenStack.pop()));
	    } else {
		tokenStack.push(Double.parseDouble(s));
	    }
	}

	return tokenStack.pop();
    }

    public static void main(String[] args) {	
        System.out.println(Postfix.eval("10 20 + 7 % 5 4 + /"));
    }
}
