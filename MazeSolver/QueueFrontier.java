import java.util.*; 

public class QueueFrontier implements Frontier {
    public LinkedList<Location> data;

    public QueueFrontier(){
	data = new LinkedList<Location>();
    }

    public void add(Location loc){
	data.add(loc);
    }

    public int size(){
	return data.size();
    }

    public Location next(){
	return data.poll();
    }

    public boolean hasNext(){
	return data.size() > 0;
    }
}
