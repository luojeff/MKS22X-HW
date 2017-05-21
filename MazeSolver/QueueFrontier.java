import java.util.*; 

public class QueueFrontier implements Frontier {
    public LinkedList<Location> linked;

    public QueueFrontier(){
	linked = new LinkedList<Location>();
    }

    public void add(Location loc){
	linked.add(loc);
    }

    public int size(){
	return linked.size();
    }

    public Location next(){
	return linked.poll();
    }

    public boolean hasNext(){
	return linked.size() > 0;
    }
}
