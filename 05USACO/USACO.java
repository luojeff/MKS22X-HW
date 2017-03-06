import java.util.*;
import java.io.*;

public class USACO {
    public int[] options;
    public int[][] lake;
    public int[][] instructions;
    

    public USACO(String filename){
	options = new int[4];
	
	try{
	    Scanner sc = new Scanner(new File(filename));
	
	    int count = 0;
	    while(sc.hasNextInt() && count < 4){

		options[count] = sc.nextInt();
		count++;
	    }

	    lake = new int[options[0]][options[1]];

	    for(int r = 0; r < options[0]; r++){
		for(int c = 0; c < options[1]; c++){
		    lake[r][c] = sc.nextInt();
		}
	    }

	    instructions = new int[options[3]][3];

	    for(int r = 0; r < options[3]; r++){
		for(int c = 0; c < 3; c++){
		    instructions[r][c] = sc.nextInt();
		}
	    }
	   	    	    
	} catch (FileNotFoundException e){
	    System.out.println("boohoo");
	}
	
    }

    


    public static void main(String[] args){
	USACO a = new USACO("makelake.in");
	//System.out.println(options);
	for(int[] b : a.instructions){
	    for(int i : b){
		System.out.print(i + " ");
	    }
	    System.out.println("\n");
	}
    }
    
   
}
