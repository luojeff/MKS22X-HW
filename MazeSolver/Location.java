public class Location implements Comparable<Location>{
    private int row, col;
    private int distToStart, distToGoal;

    public Location(int row, int col, Location previous, int toStart, int toGoal, boolean aStar){
	this.row = row;
	this.col = col;
	distToStart = toStart;
	distToGoal = toGoal;
    }

    public int compareTo(Location other){
	return (distToStart + distToGoal) - other.distToStart - other.distToGoal;
    }

    public int getRow(){return row;}
    public int getCol(){return col;}

    public int getDist(Location next){
	return Math.abs(this.getRow() - next.getRow()) + Math.abs(this.getCol() - next.getCol());
    }

    public static int getDist(int row, int col, Location next){
	return Math.abs(row - next.getRow()) + Math.abs(col - next.getCol());
    }
}
