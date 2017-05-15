public class Location implements Comparable<Location>{
    private int row, col;
    private int distToStart, distToGoal;
    private Location previous;
    private boolean aStar;

    public Location(int r, int c, Location previous, int toStart, int toGoal, boolean aStar){
	row = r;
	col = c;
	previous = p;
	distToStart = toStart;
	distToGoal = toGoal;
	this.aStar = aStar;
    }

    public int compareTo(Location other){
	return -1;
    }
}
