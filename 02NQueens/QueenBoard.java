public class QueenBoard {
    private int[][] board;
    private int solutionCount;
    
    public QueenBoard(int size){
	board = new int[size][size];
    }

    public void solve(int c){
	solveH(0);
    }

    public boolean solveH(int c){
	return false;
    }

    // Adds a queen to the board, including threatened spots
    public void addQueen(int r, int c){
	board[r][c] = -1;
	for(int i=0; i<board.length; i++){
	    if(board[i][c] != -1){
		board[i][c]++;
	    }
	    if (board[r][i] != -1){
		board[r][i]++;
	    }
	}

	// Implementing diagonals
	int min = Math.min(r,c);
	int max = Math.max(r,c);
	int[] topLeft = {r-min,c-min};
	int[] botLeft = {r+max,c-max};

	int k = 0;
	while(inLimits(topLeft[0]+k) && inLimits(topLeft[1]+k)){
	    if(board[topLeft[0]+k][topLeft[1]+k] != -1)
		board[topLeft[0]+k][topLeft[1]+k]++;
	    k++;
	}

	k = 0;
	while(inLimits(botLeft[0]-k) && inLimits(botLeft[1]+k)){
	    if(board[botLeft[0]-k][botLeft[1]+k] != -1)
		board[botLeft[0]-k][botLeft[1]+k]++;
	    k++;
	}	   
    }

    public void removeQueen(int r, int c){
	if(board[r][c] == -1){
	    board[r][c] = 0;
	    for(int i=0; i<board.length; i++){
		if(board[i][c] >= 1){
		    board[i][c]--;
		}
		if (board[r][i] >= 1){
		    board[r][i]--;
		}
	    }
	}
    }

    // Displays array in 2-d fashion
    public String toString(){
	String retArray = "";
        for(int[] row : board){
	    for(int col : row){
		retArray += col + " ";
	    }
	    retArray += "\n";
	}
	return retArray;
    }

    public boolean inLimits(int i){
	return 0 <= i && i < board.length;
    }
}
