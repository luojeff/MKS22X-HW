public class FrontierPriorityQueue {
    MyHeap myHeap;

    public FrontierPriorityQueue(){
	myHeap = new MyHeap(true);
    }

    public void add(Location location){
	myHeap.add(location);
    }

    public Location next(){
	try {
	    return myHeap.remove();
	} else {
	    return null;
	}
    }

}
