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
	case 2:
	    frontier = new FrontierPriorityQueue();
	case 3:
	    frontier = new FrontierPriorityQueue();
        default:
	}

	frontier.add(maze.getStart());

	// PROCESS FRONTIER HERE
    }

    public static void main(String[] args){
	MazeSolver ms = new MazeSolver("maze");
	System.out.println(ms);

	ms.solve();
    }
}
