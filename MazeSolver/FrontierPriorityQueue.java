 import java.util.*;

public class FrontierPriorityQueue implements Frontier {
    public MyHeap data;

    public FrontierPriorityQueue(){
	data = new MyHeap(true);
    }

    public void add(Location location){
        data.add(location);
    }

    public Location next(){
        return data.remove();
    }

    public int size(){
	return data.size();
    }

    public boolean hasNext(){
	return data.size() > 0;
    }
}
