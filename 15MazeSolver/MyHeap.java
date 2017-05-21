import java.util.*;

public class MyHeap {
    private Location[] arr;
    private int curr;
    private int mult = 1;

    public MyHeap(){
	arr = new Location[4];	
	curr = 0;
    }

    public MyHeap(boolean min){
	this();
	if(!min){
	    mult = -1;
	}
    }

    public void add(Location loc){        
	if(++curr >= arr.length){
	    resize();
	}

	int lastElement = curr;
	arr[curr] = loc;	    

	while(arr[curr/2] != null && mult*loc.compareTo(arr[curr/2]) > 0){
	    pushUp(curr);
	    curr /= 2;
	}

	curr = lastElement;	
    }

    public Location remove(){
	if(curr < 1){
	    throw new NoSuchElementException("Heap contains no elements!");
	}
	
	Location replace = arr[curr];
	Location removed = arr[1];
	
	arr[curr--] = null;
	arr[1] = replace; //replace top element with last
	pushDown(1);	
	
	return removed;
    }

    // Returns index of child, *largest if boolean is true, *smallest otherwise
    // Returns -1 if there is no child suitable
    private int getChild(int parentIndex){
	if(arr[parentIndex] == null || parentIndex * 2 >= arr.length){
	    return -1;
	}

	Location parent = arr[parentIndex];
	Location firstChild = arr[parentIndex*2];
	Location secondChild = arr[parentIndex*2+1];

	if(secondChild != null){
	    if(mult * firstChild.compareTo(secondChild) >= 0 && mult * firstChild.compareTo(parent) > 0){
		return parentIndex*2;
	    } else if (mult * secondChild.compareTo(firstChild) > 0 && mult * secondChild.compareTo(parent) > 0){
		return parentIndex*2+1;
	    }
	    return -1;
	} else if (firstChild != null){
	    if(mult*firstChild.compareTo(parent) > 0){
		return parentIndex*2;
	    }
	    return -1;
	}
	return -1;
    }

    public Location peek(){
	return arr[1];
    }

    private void resize(){
	Location[] newArr = new Location[arr.length * 2];

	for(int i=0; i<arr.length; i++){
	    newArr[i] = arr[i];
	}

	arr = newArr;
    }    

    private void pushUp(int index){
	Location toBePushedDown = arr[index/2];
	Location toBePushedUp = arr[index];
	arr[index] = toBePushedDown;
	arr[index/2] = toBePushedUp;	
    }    

    // Pushes top element down into correct ordering position
    private void pushDown(int index){
	int childIndex = getChild(index);

	while(childIndex != -1){
	    Location toBePushedDown = arr[index];
	    Location toBePushedUp = arr[childIndex];
	    arr[index] = toBePushedUp;
	    arr[childIndex] = toBePushedDown;

	    index = childIndex;
	    childIndex = getChild(childIndex);
	}
    }    

    // For debugging
    // Prints entire contents of MyHeap array
    public String toString(){
	String ret = "MyHeap: [";

	for(int i=0; i<arr.length; i++){
	    if(i != arr.length-1){
		ret += arr[i] + ",";
	    } else {
		ret += arr[i];
	    }
	}

	return ret + "]";
    }
}
