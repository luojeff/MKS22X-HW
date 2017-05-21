import java.util.*;

public class MazeSolver {
    public Maze maze;
    public Frontier frontier;
    private boolean animate;
    private ArrayList<Location> removed;

    public MazeSolver(String filename){
	this(filename, false);
    }
   
    public MazeSolver(String filename, boolean animate){
	this.animate = animate;
        maze = new Maze(filename);
	removed = new ArrayList<Location>();
    }

    public void solve(){
	solve(1);
    }

    public void solve(int style){

	// MORE OPTIMAL???

	if(style == 0){
	    frontier = new StackFrontier();
	} else if (style == 1){
	    frontier = new QueueFrontier();	    
	} else if (style == 2){
	    frontier = new FrontierPriorityQueue();
	} else if (style == 3){
	    frontier = new FrontierPriorityQueue();
	}
	
	frontier.add(maze.getStart());
	boolean foundSolution = false;

	// PROCESSING FRONTIER HERE

	/* instructions
	   while not done and not empty:
	   get next node
	   process that node
	*/
	while(frontier.hasNext() && !foundSolution){
	    Location removedLoc = frontier.next();
	    removed.add(removedLoc);
	    
	    // Updates frontier with new neighbors
	    for(Location loc : getNeighbors(removedLoc)){
		if(loc.getRow() == maze.getEnd().getRow() && loc.getCol() == maze.getEnd().getCol()){
		    foundSolution = true;
		}
		if(notInData(loc, style) && notInRemoved(loc)){		    
		    frontier.add(loc);
		}
	    }
	}

	if(foundSolution){
	    System.out.println("Found solution!");
	    System.out.println("Ended with " + frontier.size() + " neighbors in data");
	} else {
	    System.out.println("No solution found!");
	}
    }

    private ArrayList<Location> getNeighbors(Location curr){
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
	    if(maze.inBounds(possible) && maze.isValid(possible)){
		neighbors.add(possible);
	    }
	}

	return neighbors;
    }

    private boolean notInData(Location toAdd, int style){
	// MAKE THIS SO IT'S NOT ONLY FOR QUEUEFRONTIERS!!!


	if(style == 0){
	    for(Location loc : ((StackFrontier) frontier).data){
		if(loc.getRow() == toAdd.getRow() && loc.getCol() == toAdd.getCol()){
		    return false;
		}
	    }
	} else if(style == 1){	    
	    for(Location loc : ((QueueFrontier) frontier).data){
		if(loc.getRow() == toAdd.getRow() && loc.getCol() == toAdd.getCol()){
		    return false;
		}
	    }	
	} else if (style == 2){
	    for(Location loc : ((FrontierPriorityQueue) frontier).data){
		if(loc.getRow() == toAdd.getRow() && loc.getCol() == toAdd.getCol()){		          return false;
		}
	    }	
	}
	return true;
    }

    private boolean notInRemoved(Location toAdd){
	for(Location loc : removed){
	    if(loc.getRow() == toAdd.getRow() && loc.getCol() == toAdd.getCol()){
		return false;
	    }
	}
	return true;
    }

    public static void main(String[] args){
	MazeSolver ms = new MazeSolver("data1.txt");
	ms.solve(2);
	System.out.println(ms.maze);
    }
}
