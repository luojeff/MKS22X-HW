import java.util.ArrayList;

/**
 * Generates # of solutions to the Queens chess problem with n-pieces. 
 */
public class QueenBoard {
    private int[][] board;
    private int solutionCount;

    private ArrayList<int[]> queenOrder;

    public QueenBoard(int size) {
	board = new int[size][size];

	// Keeps track of the order of queens in which they are added to the
	// board. Useful when the most recently added queen must be removed from
	// the board
	queenOrder = new ArrayList<int[]>();
    }

    public void solve() {
	board = new int[board.length][board.length];
	solveH(0, true);
    }

    public void countSolutions() {
	board = new int[board.length][board.length];
	solutionCount = 0;
	solveH(0, false);
    }

    /**
     * Returns true if the board can be solved with n-queens. If stopAtFirst is
     * false, the function will instead increment the solutionCount by going
     * through all the solutions.
     */
    public boolean solveH(int r, boolean stopAtFirst) {
	ArrayList<Integer> openSquares = getOpenSquares(r);
	if (hasOpenSquares(openSquares)) {
	    if (r == board.length - 1) {
		if (stopAtFirst) {
		    addQueen(r, openSquares.get(0));
		    solutionCount++;
		    return true;
		} else {
		    // Don't add a queen to the last row when stopAtFirst is
		    // false; this newly added queen will be removed but the
		    // (n-1)th queen will still be in the second to last row
		    solutionCount++;
		    return false;
		}
	    } else {
		for (int c : openSquares) {
		    addQueen(r, c);
		    if (solveH(r + 1, stopAtFirst)) {
			return true;
		    } else {
			int lastQueenRow = queenOrder.get(queenOrder.size() - 1)[0];
			int lastQueenCol = queenOrder.get(queenOrder.size() - 1)[1];
			removeQueen(lastQueenRow, lastQueenCol);
		    }
		}
		return false;
	    }
	} else {
	    return false;
	}
    }

    public int getCount() {
	return solutionCount;
    }

    /*
     * Adds a queen to the board (-1) and increments spots which it attacks by 1
     */
    public void addQueen(int r, int c) {
	board[r][c] = -1;
	queenOrder.add(new int[] { r, c });
		
	for (int i = 0; i < board.length; i++) {
	    if (board[i][c] != -1)
		board[i][c]++;
	    if (board[r][i] != -1)
		board[r][i]++;
	}

	int[] topLeft = new int[2];
	int[] botLeft = new int[2];

	int k = 0;
	while (inLimits(r - k) && inLimits(c - k)) {
	    topLeft[0] = r - k;
	    topLeft[1] = c - k;
	    k++;
	}

	k = 0;
	while (inLimits(r + k) && inLimits(c - k)) {
	    botLeft[0] = r + k;
	    botLeft[1] = c - k;
	    k++;
	}

	k = 0;
	while (inLimits(topLeft[0] + k) && inLimits(topLeft[1] + k)) {
	    if (board[topLeft[0] + k][topLeft[1] + k] != -1)
		board[topLeft[0] + k][topLeft[1] + k]++;
	    k++;
	}

	k = 0;
	while (inLimits(botLeft[0] - k) && inLimits(botLeft[1] + k)) {
	    if (board[botLeft[0] - k][botLeft[1] + k] != -1)
		board[botLeft[0] - k][botLeft[1] + k]++;
	    k++;
	}
    }

    /*
     * Removes a queen from the board, decrementing spots that were attacked by
     * 1. NOTE: because the recursive algorithm assumes that you can't place
     * queens in spots that are attacked by other queen(s), removing the queen
     * replaces the spot with a zero(0), even if it happens to be attacked by
     * another queen. In the logic of the solveH function, this should still
     * work.
     */
    public void removeQueen(int r, int c) {
	if (board[r][c] == -1) {
	    for (int i = 0; i < board.length; i++) {
		if (board[i][c] >= 1) {
		    board[i][c]--;
		}
		if (board[r][i] >= 1) {
		    board[r][i]--;
		}
	    }
	}

	int[] topLeft = new int[2];
	int[] botLeft = new int[2];

	int k = 0;
	while (inLimits(r - k) && inLimits(c - k)) {
	    topLeft[0] = r - k;
	    topLeft[1] = c - k;
	    k++;
	}

	k = 0;
	while (inLimits(r + k) && inLimits(c - k)) {
	    botLeft[0] = r + k;
	    botLeft[1] = c - k;
	    k++;
	}

	k = 0;
	while (inLimits(topLeft[0] + k) && inLimits(topLeft[1] + k)) {
	    if (board[topLeft[0] + k][topLeft[1] + k] >= 0)
		board[topLeft[0] + k][topLeft[1] + k]--;
	    k++;
	}

	k = 0;
	while (inLimits(botLeft[0] - k) && inLimits(botLeft[1] + k)) {
	    if (board[botLeft[0] - k][botLeft[1] + k] >= 0)
		board[botLeft[0] - k][botLeft[1] + k]--;
	    k++;
	}

	queenOrder.remove(queenOrder.size() - 1);
	board[r][c] = 0;
    }

	
    // Displays array in 2-d fashion
    /* 
       public String toString() {
       String retArray = "";
       for (int[] row : board) {
       for (int col : row) {
       retArray += col + " ";
       }
       retArray += "\n";
       }
       return retArray;
       }
    */
	
    public String toString() {
	String retArray = "";
	for(int[] row : board){
	    for(int col: row){
		if(col >= 0){
		    retArray += "_ ";
		} else {
		    retArray += "Q ";
		}
	    }
	    retArray += "\n";
	}
	return retArray;
    }

    private boolean inLimits(int i) {
	return i >= 0 && i < board.length;
    }

    private boolean hasOpenSquares(ArrayList<Integer> openSquares) {
	return openSquares.size() > 0;
    }

    /*
     * returns array containing the indices in which there is an open square.
     * 
     * e.g. getOpenSquares([0,1,2,0,1]) -> [0,3]
     * 
     * An open square is present in the 0th and 3rd indices
     */
    private ArrayList<Integer> getOpenSquares(int row) {
	ArrayList<Integer> retArray = new ArrayList<Integer>();
	for (int i = 0; i < board.length; i++) {
	    if (board[row][i] == 0) {
		retArray.add(i);
	    }
	}
	return retArray;
    }
}
