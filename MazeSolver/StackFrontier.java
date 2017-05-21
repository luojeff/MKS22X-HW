import java.util.*;

public class StackFrontier implements Frontier {
    public LinkedList<Location> data;

    public StackFrontier(){
	data = new LinkedList<Location>();
    }
    
    public void add(Location loc){
	data.add(loc);
    }

    public int size(){
	return data.size();
    }

    public Location next(){
	return data.removeLast();
    }

    public boolean hasNext(){
	return data.size() > 0;
    }
}
