 import java.util.*;

public class FrontierPriorityQueue implements Frontier {
    public MyHeap data;
    private boolean aStar;

    public FrontierPriorityQueue(){
	this(true);
    }

    public FrontierPriorityQueue(boolean aStar){
        this.aStar = aStar;
	data = new MyHeap(false);
    }

    public void add(Location location){
	if(aStar){
	    data.add(location);
	} else {
	}
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
