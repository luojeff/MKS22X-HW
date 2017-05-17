public class MazeSolver {
    Maze maze;

    public MazeSolver(String s){
	maze = new Maze(s);
    }

    public void solve(){
	
    }

    public void solve(int i){
    }

    public static void main(String[] args){
	MazeSolver ms = new MazeSolver("maze");
	System.out.println(ms);

	ms.solve();
    }
}
