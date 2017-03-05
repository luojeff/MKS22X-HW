import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Maze {

    private char[][] maze;
    private boolean animate;
    private int startingRow;
    private int startingCol;
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    /*
     * Constructor loads a maze text file, and sets animate to false by default.
     * 1. The file contains a rectangular ASCII maze, made with the following 4
     * characters: '#' - locations that cannot be moved onto ' ' - locations
     * that can be moved onto 'E' - the location of the goal (exactly 1 per
     * file) 'S' - the location of the start(exactly 1 per file) 2. The maze has
     * a border of '#' around the edges. So you don't have to check for out of
     * bounds! 3. When the file is not found OR there is no E or S then: print
     * an error and exit the program.
     */

    public Maze(String filename) {
	Scanner scan;
	String line = "";
	int count = 0; // get length
	int ecount = 0;
	int scount = 0;

	animate = false;

	try {
	    scan = getScanner(filename);

	    while (scan.hasNextLine()) {
		line = scan.nextLine();
		count++;
	    }

	    // Initializes maze
	    maze = new char[count][line.length()];

	    count = 0;
	    scan = getScanner(filename);

	    // Reads into array
	    while (scan.hasNextLine()) {
		line = scan.nextLine();
		for (int i = 0; i < line.length(); i++) {
		    if (line.charAt(i) == 'E')
			ecount++;
		    if (line.charAt(i) == 'S') {
			startingRow = count;
			startingCol = i;
			scount++;
		    }
		    maze[count][i] = line.charAt(i);
		}
		count++;
	    }

	    if (!(scount == 1 && ecount == 1)) {
		System.exit(1);
	    }
	} catch (FileNotFoundException e) {
	    System.exit(1);
	}
    }

    public Scanner getScanner(String filename) throws FileNotFoundException {
	File file = new File(filename);
	Scanner retScanner = new Scanner(file);

	return retScanner;
    }

    
    public String toString() {
	String retString = "";
	for (char[] r : maze) {
	    String line = "";

	    for (char c : r) {
		if (c == '@') {
		    line += c;
		    //line += ANSI_PURPLE + c + ANSI_RESET;
		} else if (c=='#'){
		    line += '#';
		} else {
		    line += c;
		}
	    }

	    retString += line + '\n';
	}
	
	return retString;
    }

    public void setAnimate(boolean b) {
	animate = b;
    }

    public void clearTerminal() {
	System.out.println("\033[2J\033[1;1H");
    }

    /*
     * Wrapper Solve Function Since the constructor exits when the file is not
     * found or is missing an E or S, we can assume it exists.
     */
    public boolean solve() {
	int srow = startingRow;
	int scol = startingCol;

	maze[srow][scol] = ' ';
	return solve(srow, scol);
    }

    private void wait(int millis) {
	try {
	    Thread.sleep(millis);
	} catch (InterruptedException e) {
	}
    }

    /*
     * Recursive solve function
     */
    private boolean solve(int row, int col) {

	if (animate) {
            clearTerminal();
	    System.out.println("Row, Col: " + row + " " + col);
	    System.out.println(this);
	    wait(30);
	}	
	
	if (!onBoard(row, col))
	    return false;
		
	maze[row][col] = '@';

	/*
	 * If it's a deadend, it will trace back until reaching a square with
	 * open squares
	 */
	if (getSurrounding(row, col, false).size() == 0) {

	    // If square is the starting point
	    if (row == startingRow && col == startingCol) {
		return false;
	    } else {
		int[] branchSquare = traceback(row, col);
		if (branchSquare[0] == startingRow && branchSquare[1] == startingCol) {
		    return false;
		} else {
		    return solve(branchSquare[0], branchSquare[1]);
		}
	    }
	} else {

	    for (Integer[] square : getSurrounding(row, col, false)) {
		char c = maze[square[0]][square[1]];

		if (c == 'E') {
		    return true;
		} else {
		    return solve(square[0], square[1]);
		}
	    }
	}

	return false; // so it compiles
    }

    /*
     * Called when reaching a deadend; converts periods (.) into (@) along the
     * deadend trail until reaching an intersection allowing another passage.
     * Returns an array containing the row and column of the closest
     * intersection
     */
    public int[] traceback(int row, int col) {
	if (row == startingRow && col == startingCol) {
	    maze[row][col] = '@';
	} else {
	    maze[row][col] = '.';
	}

	/*
	 * If there is at least one space to move to (in addition to one taken
	 * up by an '@' OR '.')
	 */
	if (getSurrounding(row, col, false).size() >= 1) {
	    return new int[] { row, col };
	} else {

	    /*
	     * If one square open, being a '@' - Keeps tracing until reaching a
	     * point where there is an open ' ' space
	     */
	    if (getSurrounding(row, col, true).size() > 0) {
		return traceback(getSurrounding(row, col, true).get(0)[0], getSurrounding(row, col, true).get(0)[1]);
	    }

	}

	/* should be unreachable */
	return new int[] { -1, -1 };
    }

    private ArrayList<Integer[]> getSurrounding(int r, int c, boolean goBack) {
	ArrayList<Integer[]> preAL = new ArrayList<Integer[]>();
	ArrayList<Integer[]> retAL = new ArrayList<Integer[]>();

	preAL.add(new Integer[] { r - 1, c });
	preAL.add(new Integer[] { r + 1, c });
	preAL.add(new Integer[] { r, c + 1 });
	preAL.add(new Integer[] { r, c - 1 });

	for (Integer[] i : preAL) {
	    if (goBack) {
		if (onBoard(i[0], i[1]) && !isWall(i[0], i[1]) && !isDeadend(i[0], i[1])) {
		    retAL.add(i);
		}
	    } else {
		if (onBoard(i[0], i[1]) && !isWall(i[0], i[1]) && !isDeadend(i[0], i[1]) && !isTaken(i[0], i[1])) {
		    retAL.add(i);
		}
	    }
	}
	return retAL;
    }

    private boolean onBoard(int r, int c) {
	return r < maze.length && r >= 0 && c < maze[r].length && c >= 0;
    }

    private boolean isTaken(int r, int c) {
	return maze[r][c] == '@' || (r == startingRow && c == startingCol);
    }

    private boolean isDeadend(int r, int c) {
	return maze[r][c] == '.' || (r == startingRow && c == startingCol);
    }

    private boolean isWall(int r, int c) {
	return maze[r][c] == '#';
    }
}
