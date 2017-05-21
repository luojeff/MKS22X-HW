public class Location implements Comparable<Location>{
    private int row, col;
    private int distToStart, distToGoal;

    public Location(int row, int col, int toStart, int toGoal){
	this.row = row;
	this.col = col;
	distToStart = toStart;
	distToGoal = toGoal;
    }

    public int compareTo(Location other){
	return (distToStart + distToGoal) - other.distToStart - other.distToGoal;
    }
}
