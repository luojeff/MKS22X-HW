public class MyDeque {
    private String[] data;
    private int size; // size of array (including nulls)
    private int numElements; // size of array (only set values)
    private int front; // right-most index that isn't set
    private int back;
    
    public MyDeque(){
	this(2);
    }

    public MyDeque(int size){
	this.size = size;
	this.front = 0;
	this.back = 0;
	this.numElements = 0;

	data = new String[size];
    }    

    // resizes array to double the original capacity
    public void resize(){
	size *= 2;

	System.out.println("Resize called to: " + size);

	String[] resized = new String[size];	
	copyTo(data, resized);
	data = resized;
    }

    // copies from original into new size array
    public void copyTo(String[] original, String[] resized){
	int originalSize = size/2;
	int originalCounter = front;
	int resizedCounter = 0;	
	int throughElements = 0; // how many elements have been counted
	
	while(throughElements < originalSize){
	    resized[resizedCounter++] = original[originalCounter];
	    originalCounter = (originalCounter+1) % originalSize;
	    throughElements++;
	}
    }
    
    // auto-resize on add (if space is needed) !!!

    public void addFirst(String element){
        if(numElements != size){
	    if(front == 0){
		front = size-1;
	    } else {
		front--;
	    }
	    data[front] = element;
	} else {
	    resize();
	    front = size-1;
	    back = numElements-1;
	    data[size-1] = element;
	}
	
	numElements++;
    }
    
    public void addLast(String element){
	if(numElements != size && numElements != 0){
	    back = (back+1) % size;
	    data[back] = element;
	} else if(numElements != size && numElements == 0){
	    // special case where start and end are null
	    data[back] = element;
	} else {
	    resize();
	    back = numElements;
	    data[back] = element;
	}
	numElements++;
    }
    
    public String removeFirst(){
	String element = data[front];

	front = (front+1) % size;
	
	numElements--;
	return element;
    }
    
    public String removeLast(){
	String element = data[back];

	if(back == 0){
	    back = size-1;
	} else {
	    back--;
	}
	
	numElements--;
	return element;
    }
    
    public String getFirst(){
	return data[front];
    }
    
    public String getLast(){
	return data[back];
    }

    public String toString(){
	String ret = "[";

	int curr = front;
	int throughElements = 0;
	
	while(throughElements < numElements){
	    if(throughElements == numElements-1){
		ret += data[curr];
	    } else {
		ret += data[curr] + ",";
	    }
	    
	    curr = (curr+1) % size;
	    throughElements++;
	}
	

	/*
	for(int i=0; i<size; i++){
	    if(i != size-1){
		ret += data[i] + ",";
	    } else {
		ret += data[i];
	    }
	}
	*/

	return ret + "]";
    }

    public static void main(String[] args){
	MyDeque test = new MyDeque();

	/* LENGTHY TESTS */
	
	test.addLast("Hi");

	System.out.println("Done 1");
	System.out.println(test);
	
	test.addLast("Bye");

	System.out.println("Done 2");
	System.out.println(test);
	
	test.addLast("LAST");

	System.out.println("Done 3");
	System.out.println(test);
	
	test.addFirst("Good");
	
	System.out.println("Done 4");
	System.out.println(test);
	
	test.addFirst("FIRST");

	System.out.println("Done 5");
	System.out.println(test);		

	System.out.println("First: " + test.getFirst());
	System.out.println("Back: " + test.back);
	System.out.println("Back: " + test.getLast());	

	test.removeFirst();
	System.out.println(test);

	test.removeLast();
	System.out.println(test);

	System.out.println("First: " + test.getFirst());
	System.out.println("Last: " + test.getLast());

    }
}
