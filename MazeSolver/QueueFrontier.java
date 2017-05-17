import java.util.*; 

public class QueueFrontier implements Frontier {
    LinkedList<Location> linked;

    public QueueFrontier(){
	linked = new LinkedList<Location>();
    }

    public void add(Location loc){
	linked.add(loc);
    }

    public Location next(){
	return linked.poll();
    }
}
