public class MyLinkedList {
    public LNode start, end;
    public int size;

    public MyLinkedList(){
	size = 0;
    }

    public void add(int value){
	LNode ln = new LNode(value);
	add(ln);
    }

    public void add(int index, int value){
	
    }

    public int remove(int index){
	LNode toRemove
	
    }
    
    public void add(LNode ln){
	if(size() == 0){
	    this.start = ln;
	    this.end = ln;
	    size++;
	} else {
	    end.setNext(ln);
	    end = ln;
	    size++;
	}
    }

    public String toString(){
	String ret = "[";
	LNode current = start;

        for(int i=0; i<size(); i++){
	    if(i < size()-1){
		ret += current.value + ",";
		current = current.next;
	    } else {
		ret += current.value;
		current = current.next;
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
	LNode current = start;
	while(index > 0){
	    current = current.next;
	    index--;
	}	
	return current;
    }

    public int set(int index, int newValue){
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

    public static void main(String[] args) {
	MyLinkedList linked = new MyLinkedList();
	
	linked.add(10);
	linked.add(5);
	linked.add(7);

	System.out.println(linked);
	System.out.println(linked.set(2,11));
	System.out.println(linked.get(2));

	System.out.println(linked);
	System.out.println(linked.indexOf(11));
    }

    /*
      Inner class for Node 
    */
    public static class LNode {
	public int value;
	public LNode next, previous;
	
	public LNode(int value){
	    this.value = value;	    
	}

	public LNode(int value, LNode next){
	    this(value);
	    this.next = next;
	}

	public void setValue(int value){
	    this.value = value;
	}

	public void setNext(LNode next){
	    this.next = next;
	}

	public void setPrevious(LNode previous){
	    this.previous = previous;
	}
    }
}
