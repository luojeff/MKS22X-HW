import java.util.*;
import java.io.*;

public class USACO {
    public int[] options;
    public int[] options2;
    public int[][] lake;
    public char[][] pasture;
    public int[][] intPasture;
    public int[][] instructions;
    public int[] instructions2;

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
	options2 = new int[3];
	instructions2 = new int[4];
	
	try{
	    Scanner sc = new Scanner(new File(filename));
	    
	    int count = 0;
	    while(sc.hasNextInt() && count < 3){
		options2[count++] = sc.nextInt();
	    }

	    pasture = new char[options2[0]][options2[1]];
	    intPasture = new int[options2[0]][options2[1]];
	    
	    for(int r = 0; r < pasture.length; r++){
		String row = sc.next();	       		
		for(int c = 0; c < pasture[r].length; c++){		    
		    pasture[r][c] = row.charAt(c);
		}
	    }
	    	    
	    for(int i=0; i<instructions2.length; i++){
		instructions2[i] = sc.nextInt()-1;
	    }

	    for(int r = 0; r < pasture.length; r++){
		for(int c = 0; c < pasture[r].length; c++){		    
		    if(pasture[r][c] == '*'){
			intPasture[r][c] = -1;
		    } else if (r == instructions2[0] && c == instructions2[1]){
			intPasture[r][c] = 1;
		    }
		}
	    }
	    
	    count = 0;
	    while(count < options2[2]){
	        updatePasture();
			
		count++;
	    }
	    	    
	} catch (FileNotFoundException e){
	    System.out.println("File not found!");
	}
	
	return intPasture[instructions2[2]][instructions2[3]];
    }

    /*
      Updates the board;
      Squares will receive the value of the sum of its 
      four surrounding squares
    */
    private void updatePasture(){
	int[][] newPasture = new int[intPasture.length][intPasture[0].length];
	
	for(int r = 0; r < newPasture.length; r++){
	    for(int c = 0; c < newPasture[r].length; c++){
		if(intPasture[r][c] >= 0){
		    newPasture[r][c] = getSumSurrounding(r, c);
		} else if (intPasture[r][c] == -1){
		    newPasture[r][c] = -1;
		}
	    }
	}

        intPasture = newPasture;
    }

    private int getSumSurrounding(int r, int c){
	int sum = 0;

	int[][] squares = {
	    {r+1,c},
	    {r-1,c},
	    {r,c+1},
	    {r,c-1}
	};

	for(int[] square : squares){
	    if(inPasture(square)){
		int squareVal = intPasture[square[0]][square[1]];
	        if(squareVal >= 0){
		    sum += squareVal;
		}
	    }
	}

	return sum;
    }

    private boolean inPasture(int[] square){
	return square[0] >= 0 && square[0] < intPasture.length && square[1] >= 0 && square[1] < intPasture[0].length;
    }

    private void stomp(int r, int c, int digAmount){
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
	System.out.println(a.silver("ctravel.in"));
    } 
}
