import java.util.*;

public class MyLinkedList implements Iterable<Integer> {
    public LNode start, end;    
    public int size;

    public MyLinkedList(){
	size = 0;
    }

    public void add(int value){        
	add(size, value);
    }

    /*
    public void add(LNode ln){
	if(size() == 0){
	    start = ln;
	    end = ln;
	    size++;
	} else {
	    end.setNext(ln);
	    ln.setPrevious(end);
	    end = ln;
	    size++;
	}
    }
    */

    public void add(int index, int value){
	if(index < 0 || index > size){
	    throw new IndexOutOfBoundsException();
	}	
	
	LNode newNode = new LNode(value);	

	// general case
	// beginning
	// end
	// beginning |= end |= size	

	if(size == 0){
	    start = newNode;
	    end = newNode;
	} else if(index == size){
	    LNode bef = getNode(index-1);
	    bef.setNext(newNode);
	    newNode.setPrevious(bef);
	    end = newNode;
	} else if(index == 0){
	    LNode aft = getNode(index);
	    aft.setPrevious(newNode);
	    newNode.setNext(aft);
	    start = newNode;
	} else {
	    LNode curr = getNode(index);
	    LNode bef = getNode(index-1);	    
	    newNode.setNext(curr);
	    newNode.setPrevious(bef);
	    bef.setNext(newNode);
	}

	size++;
    }

    public int remove(int index){
	if(index < 0 || index >= size){
	    throw new IndexOutOfBoundsException();
	}
	
	LNode curr = getNode(index);
	LNode bef;
	LNode aft;

	if(size() <= 1){
	    start = null;
	    end = null;
	    return curr.value;
	}

	if(index == 0){
	    aft = getNode(index+1);
	    curr.setNext(null);
	    start = aft;
	} else if (index == size-1){
	    bef = getNode(index-1);
	    bef.setNext(null);	    
	    end = bef;
	} else {	    
	    bef = getNode(index-1);
	    aft = getNode(index+1);
	    bef.setNext(aft);
	}

	size--;
        return curr.value;
    }

    public String toString(){
	String ret = "[";
	LNode current = start;

        for(int i=0; i<size; i++){
	    if(i < size-1){
		ret += current + ",";
		current = current.next;
	    } else {
		ret += current;
	    }
	}

	ret += "]";
	return ret;
    }

    public int size(){
	return size;
    }

    public int get(int index){
	return getNode(index).value;
    }

    public LNode getNode(int index){
	System.out.println("Index: " + index);

	if(index < 0 || index > size-1){
	    throw new IndexOutOfBoundsException();
	}

	LNode current = start;
	while(index > 0){
	    if(index < size()){
		current = current.next;
		index--;
	    } else {
		
	    }
	}
	return current;
    }

    public int set(int index, int newValue){
	if(index < 0 || index >= size){
	    System.out.println("Error!");

	    throw new IndexOutOfBoundsException();
	}
	
	LNode currNode = getNode(index);
	int oldValue = currNode.value;
	currNode.setValue(newValue);

	return oldValue;
    }

    public int indexOf(int value){
	int ind = 0;
	int size = size();

	while(get(ind) != value){
	    ind++;
	    if(ind == size){
		return -1;
	    }
	}

	return ind;
    }

    public Iterator<Integer> iterator(){
	return new MyLinkedListIterator(this);
    }
    
    private void addAfter(LNode location, LNode toBeAdded){
	
	if(location != end){
	    LNode aft = location.next;
	    toBeAdded.setNext(aft); // only if toBeAdded is not to be last element
	}

	if(location != start){
	    LNode bef = location.prev; // only if location is not first
	    location.setPrev(bef);
	}
	
	location.setNext(toBeAdded);
	
	toBeAdded.setPrev(location);	
    }	

    public static void main(String[] args) {
	
	/*
	  MyLinkedList linked = new MyLinkedList();

	  System.out.println("Empty: " + linked);
	  // []
	
	  linked.add(3);
	  linked.add(5);
	  linked.add(7);	
	  linked.add(0, 10);
	  linked.add(3, 15);
	
	  System.out.println("First set: " + linked); // [10, 3, 5, 15, 7]
	
	  linked.set(2, 20);
	  linked.set(0, 1);	

	  System.out.println("Second set: " + linked); // [1, 3, 20, 15, 7]

	  linked.remove(1);
	  linked.remove(3);

	  System.out.println("Third set: " + linked); // [1, 20, 15]

	  System.out.println("Size: " + linked.size());

	  System.out.println("Should be 1: " +  linked.get(0));
	  System.out.println("Should be 15: " +  linked.get(2));

	  try{
	  System.out.println("Should be an error: " +  linked.get(3));
	  } catch (IndexOutOfBoundsException e){
	  System.out.println("Index out of bounds error!");
	  }

	  linked.set(2, 100); // edge case

	  System.out.println("Should be 2: " + linked.indexOf(100));
	  System.out.println("Should be -1: " + linked.indexOf(-123));
	
	*/

	MyLinkedList linked = new MyLinkedList();
	linked.add(10);
	linked.add(5);
	linked.add(7);
	linked.addAfter(linked.getNode(1), new LNode(2));
	System.out.println("List: " + linked);
    }

    /*
      Inner class for Node 
    */
    public static class LNode {
	public LNode prev, next;
	public int value;
	
	
	public LNode(int value){
	    this.value = value;	    
	}

	public String toString(){
	    return value+"";
	}

	public void setValue(int value){
	    this.value = value;
	}

	public void setNext(LNode next){
	    this.next = next;
	}

	public void setPrevious(LNode previous){
	    this.prev = prev;
	}
    }

    public class MyLinkedListIterator implements Iterator<Integer> {
	MyLinkedList myLinkedList;
	LNode current;

	public MyLinkedListIterator(MyLinkedList myLinkedList){
	    this.myLinkedList = myLinkedList;
	    current = myLinkedList.start;
	}

	public boolean hasNext(){
	    return current != myLinkedList.end;
	}

	public Integer next(){
	    if(hasNext()){
		return current.value;
	    } else {
		throw new NoSuchElementException();
	    }
	}	

	public void remove(){};
    }
}
