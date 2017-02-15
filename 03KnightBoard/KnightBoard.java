import java.util.ArrayList;

public class KnightBoard {
    int[][] board;

    public KnightBoard(int startingRows, int startingCols){
	board = new int[startingRows][startingCols];
    }  

    public void solve(){
    }

    public boolean solveH(int row, int col, int level){
	if(level == board.length * board[0].length){
	    return true;
	} else {
	    return false;
	}
    }
    
    public ArrayList<Integer[]> getAllowedSpaces(int currentRow, int currentCol){
	ArrayList<Integer[]> retBoxes = new ArrayList<Integer[]>();
	int[] calcs = {-2, -1, 1, 2};
	int[] calcs2 = {-2, -1, 1, 2};


	for(int i : calcs){
	    for(int j : calcs2){
		if(Math.abs(j) != Math.abs(i)){
		    if(inLimits(i,j)){
			retBoxes.add(new int[]{i,j});
		    }
		}
	    }
	}
	return retBoxes;
    }

    public boolean inLimits(int row, int col){
	return row < board.length && row >= 0 && col < board[row].length && col >= 0;
    }

    public String toString() {
	String retArray = "";
	for (int r = 0; r < board.length; r++) {
	    for (int c = 0; c < board[r].length; c++) {
		if(board[r][c] < 10){
		    retArray += " " + board[r][c] + " ";
		} else {
		    retArray += board[r][c] + " ";
		}
	    }
	    retArray += "\n";
	}
	return retArray;
    }
}
