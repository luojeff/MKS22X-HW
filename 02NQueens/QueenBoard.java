import java.util.ArrayList;

/**
 * Generates solutions to the Queens chess problem with n-pieces.
 */
public class QueenBoard {
    private int[][] board;
    private int solutionCount;

    private ArrayList<int[]> queenOrder;
    private int[][] solution;

    public QueenBoard(int size) {
	board = new int[size][size];
	queenOrder = new ArrayList<int[]>();
	solution = new int[size][size];
    }

    public void solve() {
	solveH(0);
    }	
	
    public boolean solveH(int r) {
	ArrayList<Integer> openSquares = getOpenSquares(r);
	if (hasOpenSquares(openSquares)) {
	    if (r == board.length - 1) {
		addQueen(r, openSquares.get(0));
		solutionCount++;
		return true;
	    } else {			
		for (int c : openSquares) {
		    addQueen(r, c);
		    if (solveH(r + 1)) {
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

    public int getSolutionCount() {
	return solutionCount;
    }

    // Adds a queen to the board, including threatened spots
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

	/*
	 * int min = Math.min(r, c); int max = Math.max(r, c); int[] topLeft = {
	 * r - min, c - min }; int[] botLeft = { r + max, c - max };
	 */

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
	// NOTE: REMOVE FUNCTION DOESN'T REPLACE OLD-QUEEN SQUARE
	// WITH THE PROPER SQUARE DENOTING THE NUMBER OF QUEENS
	// THREATENING THE SPOT. THIS IS UNDER THE ASSUMPTION
	// THAT NO QUEENS WILL BE PUT IN A SPOT WHERE THEY ARE
	// DIRECTLY ATTACKING EACH OTHER
    }

    // Displays array in 2-d fashion
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
