public class MyHeap {
    private String[] arr;
    private int size;
    private int curr;

    private int multiplier = 1;

    public MyHeap(){
	arr = new String[4];
	size = arr.length;
	curr = 0;
    }

    public MyHeap(boolean min){
	this();
	if(min){
	    multiplier = -1;
	}
    }

    public void add(String s){
	if(++curr < size){
	    arr[curr] = s;
	} else {	
	    resize();
	    arr[curr] = s;
	}
    }

    public String remove(){
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

    private void pushUp(){}
    private void pushDown(){}

    public static void main(String[] args){
	MyHeap h = new MyHeap();
	h.add("hello");
	h.add("world!");

	System.out.println(h.peek());
	System.out.println(h.remove());
	System.out.println(h.peek());
    }
}
