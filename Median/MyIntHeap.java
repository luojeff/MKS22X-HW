import java.util.*;

public class MyIntHeap {
    private Integer[] arr;
    private int curr;
    private int mult = 1;

    public MyIntHeap(){
	arr = new Integer[4];	
	curr = 0;
    }

    public MyIntHeap(boolean min){
	this();
	if(!min){
	    mult = -1;
	}
    }

    public void add(Integer i){        
	if(++curr >= arr.length){
	    resize();
	}

	int lastElement = curr;
	arr[curr] = i;	    

	while(arr[curr/2] != null && mult*i>arr[curr/2]){
	    pushUp(curr);
	    curr /= 2;
	}

	curr = lastElement;	
    }

    public Integer remove(){
	if(curr < 1){
	    throw new NoSuchElementException("Heap contains no elements!");
	}
	
	Integer replace = arr[curr];
	Integer removed = arr[1];
	
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

	Integer parent = arr[parentIndex];	
	Integer firstChild = arr[parentIndex*2];
        Integer secondChild = arr[parentIndex*2+1];

	if(secondChild != null){
	    if(mult*firstChild.intValue()>=secondChild.intValue() && mult*firstChild.intValue()>parent.intValue()){
		return parentIndex*2;
	    } else if (mult*secondChild.intValue()>firstChild.intValue() && mult*secondChild.intValue()>parent.intValue()){
		return parentIndex*2+1;
	    }
	    return -1;
	} else if (firstChild != null){
	    if(mult*firstChild.intValue()>parent.intValue()){
		return parentIndex*2;
	    }
	    return -1;
	}
	return -1;
    }

    public Integer peek(){
	return arr[1];
    }

    private void resize(){
	Integer[] newArr = new Integer[arr.length * 2];

	for(int i=0; i<arr.length; i++){
	    newArr[i] = arr[i];
	}

	arr = newArr;
    }    

    private void pushUp(int index){
	Integer toBePushedDown = arr[index/2];
	Integer toBePushedUp = arr[index];
	arr[index] = toBePushedDown;
	arr[index/2] = toBePushedUp;	
    }    

    // Pushes top element down into correct ordering position
    private void pushDown(int index){
	int childIndex = getChild(index);

	while(childIndex != -1){
	    Integer toBePushedDown = arr[index];
	    Integer toBePushedUp = arr[childIndex];
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

    public static void main(String[] args){
	MyIntHeap h = new MyIntHeap(false);
	
	h.add(3);
	h.add(10);
	h.add(1);
	h.add(13);
	h.add(0);

	System.out.println(h);	

        for(int i=0; i<20; i++){
	    System.out.println(h.remove());
	}        
    }
}
