 import java.util.*;

public class FrontierPriorityQueue implements Frontier {
    private MyHeap myHeap;

    public FrontierPriorityQueue(){
	myHeap = new MyHeap(true);
    }

    public void add(Location location){
	myHeap.add(location);
    }

    public Location next(){
	try {
	    return myHeap.remove();
	} catch (NoSuchElementException e){
	    return null;
	}
    }

    public int getSize(){
	return myHeap.getSize();
    }

    public boolean hasNext(){
	return myHeap.getSize() > 0;
    }
}
