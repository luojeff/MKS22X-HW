public class Location implements Comparable<Location>{
    private int row, col;
    private int distToStart, distToGoal;
    private Location previous;
    private boolean aStar;

    public Location(int row, int col, Location prev, int toStart, int toGoal, boolean aStar){
	this.row = row;
	this.col = col;
	previous = prev;
	distToStart = toStart;
	distToGoal = toGoal;
	this.aStar = aStar;
    }

    public int compareTo(Location other){
	return (distToStart + distToGoal) - other.distToStart - other.distToGoal;
    }
}
