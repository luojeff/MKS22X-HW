import java.util.*;

public class MyHeap {
    private String[] arr;
    private int size;
    private int curr;

    private int multiplier = 1;

    public MyHeap(){
	arr = new String[4];
	size = arr.length;
	curr = 1;
    }

    public MyHeap(boolean min){
	this();
	if(min == false){
	    multiplier = -1;
	}
    }

    public void add(String s){
	if(++curr < size){
	    int last = curr;
	    arr[curr] = s;

	    while(arr[curr/2] != null && s.compareTo(arr[curr/2]) > 0){
		pushUp(curr);
		curr /= 2;
	    }

	    curr = last;
	} else {	
	    resize();
	    arr[curr] = s;
	}
    }

    public String remove(){
	if(curr == 1){
	    throw new NoSuchElementException();
	}

	String ret = arr[curr];
	arr[curr--] = null;
	
	return ret;
    }

    public String peek(){
	return arr[curr];
    }

    private void resize(){
	size *= 2;
	String[] newArr = new String[size];

	for(int i=0; i<arr.length; i++){
	    newArr[i] = arr[i];
	}

	arr = newArr;
    }    

    private void pushUp(int index){
	String toBePushedDown = arr[index/2];
	String toBePushedUp = arr[index];
	arr[index] = toBePushedDown;
	arr[index/2] = toBePushedUp;	
    }

    private void pushDown(int index){
	String toBePushedDown = arr[index];
	String toBePushedUp = arr[index/2];
	arr[index/2] = toBePushedUp;
	arr[index] = toBePushedDown;
    }

    public static void main(String[] args){
	MyHeap h = new MyHeap();
	h.add("d");
	h.add("a");
	h.add("c");
	h.add("e");
	h.add("b");
	
	System.out.println(h.remove());
        System.out.println(h.remove());
	System.out.println(h.remove());
	System.out.println(h.remove());
	System.out.println(h.remove());
    }
}
