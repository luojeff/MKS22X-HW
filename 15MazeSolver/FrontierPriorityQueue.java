import java.util.*;

public class FrontierPriorityQueue implements Frontier {
    public MyHeap data;
    private boolean aStar;

    public FrontierPriorityQueue(){
	this(true);
    }

    public FrontierPriorityQueue(boolean aStar){
	data = new MyHeap(false);
        this.aStar = aStar;
    }

    public void add(Location location){
	if(aStar){
	    location.setAStar(true);
	    data.add(location);
	} else {
	    location.setAStar(false);
	    data.add(location);
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
