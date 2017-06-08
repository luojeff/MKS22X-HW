import java.util.*;

public class MazeSolver {
    private Maze maze;
    private Frontier frontier;
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
	if(style == 0){
	    frontier = new StackFrontier();
	} else if (style == 1){
	    frontier = new QueueFrontier();	    
	} else if (style == 2){
	    frontier = new FrontierPriorityQueue(false); // Best-first
	} else if (style == 3){
	    frontier = new FrontierPriorityQueue(true); // A*	    
	    maze.getStart().setAStar(true);
	    maze.getEnd().setAStar(true);
	}

	
	frontier.add(maze.getStart());	
	boolean foundSolution = false;

	// PROCESSING FRONTIER HERE
	while(frontier.hasNext() && !foundSolution){
	    Location removedLoc = frontier.next();
	    maze.set(removedLoc.getRow(),removedLoc.getCol(),'.');
	    removed.add(removedLoc);
	    
	    // Updates frontier with new neighbors
	    for(Location loc : getNeighbors(removedLoc)){		
		if(loc.getRow() == maze.getEnd().getRow() && loc.getCol() == maze.getEnd().getCol()){
		    maze.getEnd().setPrevious(removedLoc);
		    foundSolution = true;		    
		} else if (notInData(loc, style) && notInRemoved(loc)){
		    if(style == 3){
			loc.setAStar(true);
		    }
		    frontier.add(loc);
		    loc.setPrevious(removedLoc);
		    maze.set(loc.getRow(),loc.getCol(),'?');
		}
	    }

	    if(animate){
		System.out.println(maze.toString(30));
	    }
	}

	// ADDS SOLUTION TO PLAIN MAZE
	if(foundSolution){
	    Location curr = maze.getEnd().prev;
	    while(curr != null){
		maze.set(curr.getRow(),curr.getCol(),'@');
		curr = curr.prev;
	    }
	}
	
	maze.set(maze.getStart().getRow(),maze.getStart().getCol(),'S');
	maze.set(maze.getEnd().getRow(),maze.getEnd().getCol(),'E');
    }

    private ArrayList<Location> getNeighbors(Location curr){
	ArrayList<Location> neighbors = new ArrayList<Location>();
	int row = curr.getRow();
	int col = curr.getCol();

	Location[] possibles = {
	    new Location(row+1,col,curr,Location.getDist(row+1,col,maze.getStart()),Location.getDist(row+1,col,maze.getEnd()),false),
	    new Location(row-1,col,curr,Location.getDist(row-1,col,maze.getStart()),Location.getDist(row-1,col,maze.getEnd()),false),
	    new Location(row,col+1,curr,Location.getDist(row,col+1,maze.getStart()),Location.getDist(row,col+1,maze.getEnd()),false),
	    new Location(row,col-1,curr,Location.getDist(row,col-1,maze.getStart()),Location.getDist(row,col-1,maze.getEnd()),false)
	};

	for(Location possible : possibles){
	    if(maze.inBounds(possible) && maze.isValid(possible)){
		neighbors.add(possible);
	    }
	}

	return neighbors;
    }

    public String toString(){
	return maze.toString();
    }

    public String toString(int n){
	return maze.toString(n);
    }

    private boolean notInData(Location toAdd, int style){
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
	} else if (style == 2 || style == 3){
	    for(Location loc : ((FrontierPriorityQueue) frontier).data){
		if(loc.getRow() == toAdd.getRow() && loc.getCol() == toAdd.getCol()){
		    return false;
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
	/*
	MazeSolver ms = new MazeSolver("data4.txt",true);
	ms.solve(2);
	String ans = ms.toString();
	*/
    }
}
