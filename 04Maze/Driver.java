public class Driver {

    public static void main(String[] args) {
	Maze f;
	f = new Maze("data2.dat");
      
	f.setAnimate(false);
	f.solve();

	System.out.println(f);
    }
}
