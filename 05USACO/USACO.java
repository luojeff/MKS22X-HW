import java.util.*;
import java.io.*;

public class USACO {
    public int[] options;
    public int[][] lake;
    public int[][] instructions;

    public USACO(){
    }
    
    public int bronze(String filename){
	options = new int[4];
	int vol = 0;
	
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

	for(int[] instruction : instructions){
	    stomp(instruction[0], instruction[1], instruction[2]);
	}
	
	/*
	for(int[] r : lake){
	    for(int c : r){
		System.out.print(c + " ");
	    }
	    System.out.print("\n");
	}
	*/

	for(int r = 0; r < lake.length; r++){
	    for(int c = 0; c < lake[r].length; c++){
	        if(lake[r][c] < options[2]){
		    vol += options[2] - lake[r][c];
		}
	    }
        }
	
	return vol * 6 * 12 * 6 * 12;
    }

    public int silver(String filename){
	return -1;
    }

    public void stomp(int r, int c, int digAmount){
	int highest = 10;

	for(int row = r-1; row < r+2; row++){
	    for(int col = c-1; col < c+2; col++){
		if(lake[row][col] > highest){
		    highest = lake[row][col];
		}
	    }
	}

	for(int row = r-1; row < r+2; row++){
	    for(int col = c-1; col < c+2; col++){
		if(lake[row][col] > highest - digAmount){
		    lake[row][col] = highest - digAmount;
		}
	    }
	}
	
    }

    public static void main(String[] args){
	USACO a = new USACO();
        System.out.println(a.bronze("makelake.in"));
    } 
}
