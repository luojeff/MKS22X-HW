import java.util.*;

public class MazeSolver {
    public Maze maze;
    public Frontier frontier;
    public boolean animate;

    public MazeSolver(String filename){
	this(filename, false);
    }
   
    public MazeSolver(String filename, boolean animate){
	this.animate = animate;
        maze = new Maze(filename);
    }

    public void solve(){	
    }

    public void solve(int style){
	switch(style){
	case 0:
	    frontier = new StackFrontier();
	case 1:
	    frontier = new QueueFrontier();
	    /*
	case 2:
	    frontier = new FrontierPriorityQueue();
	case 3:
	    frontier = new FrontierPriorityQueue();
        default:
	    */
	}

	frontier.add(maze.getStart());

	// PROCESS FRONTIER HERE

	/*
	  while not done and not empty:
	  get next node
	  process that node
	*/

	System.out.println(((QueueFrontier)frontier).linked.size());
    }

    public ArrayList<Location> getNeighbors(Location curr){
	ArrayList<Location> neighbors = new ArrayList<Location>();
	int row = curr.getRow();
	int col = curr.getCol();

	Location[] possibles = {
	    new Location(row+1,col,null,Location.getDist(row+1,col,maze.getStart()),Location.getDist(row+1,col,maze.getEnd()),false),
	    new Location(row-1,col,null,Location.getDist(row-1,col,maze.getStart()),Location.getDist(row-1,col,maze.getEnd()),false),
	    new Location(row,col+1,null,Location.getDist(row,col+1,maze.getStart()),Location.getDist(row,col+1,maze.getEnd()),false),
	    new Location(row,col-1,null,Location.getDist(row,col-1,maze.getStart()),Location.getDist(row,col-1,maze.getEnd()),false)
	};

	for(Location possible : possibles){
	    if(maze.inBounds(possible)){
		neighbors.add(possible);
	    }
	}

	return neighbors;
    }

    public static void main(String[] args){
	MazeSolver ms = new MazeSolver("data2.txt");
	ms.solve(1);

	System.out.println(ms.maze);
    }
}
