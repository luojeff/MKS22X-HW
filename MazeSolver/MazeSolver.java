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

	//CHANGE THIS TO BE MORE OPTIMAL FOR STYLE PARAMETER
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
	boolean foundSolution = false;

	// PROCESSING FRONTIER HERE

	/* instructions
	   while not done and not empty:
	   get next node
	   process that node
	*/
	while(frontier.hasNext() && !foundSolution){
	    
	    // updates frontier with new neighbors
	    for(Location loc : getNeighbors(frontier.next())){
		if(loc.getRow() == maze.getEnd().getRow() && loc.getCol() == maze.getEnd().getCol()){
		    foundSolution = true;
		}
		if(isNew(loc)){		    
		    frontier.add(loc);
		    System.out.println("Neighbor added!");
		}
	    }
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

    private boolean isNew(Location toAdd){
	// MAKE THIS SO IT'S NOT ONLY FOR QUEUEFRONTIERS!!!
        for(Location loc : ((QueueFrontier) frontier).linked){
	    if(loc.getRow() == toAdd.getRow() && loc.getCol() == toAdd.getCol()){
		return false;
	    }
	}
	return true;
    }

    public static void main(String[] args){
	MazeSolver ms = new MazeSolver("data2.txt");
	ms.solve(1);

	System.out.println(ms.maze.toString());
    }
}
