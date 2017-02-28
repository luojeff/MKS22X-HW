import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze{

    private char[][]maze;
    private boolean animate;

    /*Constructor loads a maze text file, and sets animate to false by default.
      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - locations that cannot be moved onto
      ' ' - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)

      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!
      3. When the file is not found OR there is no E or S then: print an error and exit the program.

    */

    public Maze(String filename){
	try{
	    Scanner scan = getBF(filename);
	    int count = 0; // get length 
	    String line = "";

 	    while(scan.hasNextLine()){
		line = scan.nextLine();
		count++;
	    } 

	    maze = new char[count][line.length()];
	    count = 0;

	    while(scan.hasNextLine()){
		String line = scan.nextLine();
		for(int i = 0; i < line.length(); i++){
		    maze[count][i] = line.charAt(i);
		}
	    }


	    /*
	    for(int r=0; r<count; r++){
		for(int c=0; c<line.length(); c++){
		    maze[r][c] = 
		}
	    }
	    */

	} catch (FileNotFoundException e){}
    }

    public Scanner getBF(String filename) throws FileNotFoundException {
	File f = new File("data1.dat");
        Scanner inf = new Scanner(f);
	
	return inf;
    }

    public void printMaze(){
	for(char[] r : maze){
	    String line = "";
	    for(char c : r){
		line += r[c];
	    }
	    line += '\n';
	    System.out.println(line);
	}
    }

    public void setAnimate(boolean b){
        animate = b;
    }

    // f
    public void clearTerminal(){

        System.out.println("\033[2J\033[1;1H");

    }

    /*Wrapper Solve Function
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public boolean solve(){
	int startr = -1,startc = -1;

	//Initialize starting row and startint col with the location of the S. 

	maze[startr][startc] = ' ';//erase the S, and start solving!
	return solve(startr,startc);
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns true when the maze is solved,
      Returns false when the maze has no solution.


      Postcondition:

      The S is replaced with '@' but the 'E' is not.

      All visited spots that were not part of the solution are changed to '.'
      All visited spots that are part of the solution are changed to '@'
    */
    private boolean solve(int row, int col){
        if(animate){
            System.out.println("\033[2J\033[1;1H"+this);
            //wait(20);
        }
        //COMPLETE SOLVE
        return false; //so it compiles
    }

}
