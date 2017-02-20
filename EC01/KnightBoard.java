import java.util.ArrayList;

public class KnightBoard {
    int[][] board;

    public KnightBoard(int startingRows, int startingCols){
	board = new int[startingRows][startingCols];
    }  

    public void solve(){
	solveH(0,0,1);
    }

    public void solveFast(){
	solveH(0,0,1);
    }

    public boolean solveH(int row, int col, int level){
	if(level == board.length * board[0].length){
	    board[row][col] = level;
	    return true;
	} else {
	    board[row][col] = level;

	    ArrayList<Integer[]> allowedSpaces = getAllowedSpaces(row, col);
	    insertionSort(allowedSpaces);
	    
	    for(int count = 0; count < allowedSpaces.size(); count++){
		Integer[] coord = allowedSpaces.get(count);
		
		if(solveH(coord[0], coord[1], level+1)){
		    return true;
		}
	    }
	    
	    board[row][col] = 0;
	    return false;
	}
    }
    
    public ArrayList<Integer[]> getAllowedSpaces(int currentRow, int currentCol){
	ArrayList<Integer[]> retBoxes = new ArrayList<Integer[]>();
	int[] calcs = {-2, -1, 1, 2};
	int[] calcs2 = {-2, -1, 1, 2};

	for(int i : calcs){
	    for(int j : calcs2){
		if((Math.abs(j) != Math.abs(i)) && inLimits(currentRow + i, currentCol + j)){
		    if(board[currentRow + i][currentCol + j] == 0){
			retBoxes.add(new Integer[]{currentRow + i, currentCol + j});
		    }
		}
	    }
	}

	return retBoxes;
    }

    public void insertionSort(ArrayList<Integer[]> squares){
	for(int i = 1; i < squares.size(); i++){
	    int indexToSet = 0;
	    Integer[] prev = squares.get(i);
	    
	    for(int j = i-1; j >= 0; j--){
		if(getAllowedSpaces(squares.get(i)[0],squares.get(i)[1]).size() > getAllowedSpaces(squares.get(j)[0],squares.get(j)[1]).size()){
		    indexToSet = j+1;
		    j = -1;
		}
	    }
	    
	    for(int k = i-1; k >= indexToSet; k--){
		squares.set(k+1, squares.get(k));
	    }

	    squares.set(indexToSet, prev);
	}
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

    public static void main(String[] args){
	
	for(int i = 8; i < 51; i++){
	    //(i != 23 && i != 43 && i != 35 && i != 48 && i != 47)
	    {
		KnightBoard kb = new KnightBoard(i, i);
		long stime = System.nanoTime();
		kb.solveFast();
		long etime = System.nanoTime();

		long runTime = etime - stime;
		System.out.println(i+"x"+i+ "  Time: "+runTime/1000000.0+" ms");
	    }
	}
	
	//KnightBoard kb = new KnightBoard(23, 23);
	//kb.solve();
	//System.out.println(kb);
    }
}
