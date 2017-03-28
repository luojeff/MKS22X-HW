class MyLinkedList {
    public LNode start, end;
    public int size;

    public MyLinkedList(){
	size = 0;
    }

    public void add(int value){
	LNode newNode = new LNode(value);
	add(newNode);
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

    public void add(int index, int value){
	LNode newNode = new LNode(value);
	
	if(index < size()-1 && index > 0){
	    LNode curr = getNode(index);
	    LNode bef = getNode(index-1);
	    
	    newNode.setNext(curr);
	    bef.setNext(newNode);
	} else if (index == size()){
	    LNode bef = getNode(index-1);
	    bef.setNext(newNode);
	    newNode = end;
	} else {
	    LNode aft = getNode(index+1);
	    newNode.setNext(aft);
	    newNode = start;
	}

	size++;
    }

    public int remove(int index){
	LNode curr = getNode(index);
	LNode bef;
	LNode aft;

	if(size() == 0){
	    start = null;
	    end = null;
	    return curr.value;
	}

	if(index == 0){	    
	    aft = getNode(index+1);
	    curr.setNext(null);
	    start = aft;
	} else if (index == size()-1){
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
	
	linked.add(3);
	linked.add(5);
	linked.add(7);
	// [3, 5, 7]
	
	System.out.println(linked);
	
        linked.set(2, 11);
	linked.set(1, 9);
	// [3, 9, 11]

        linked.add(2);
	linked.add(4,5);
	linked.add(5,7);
	linked.remove(0);
	linked.remove(4);
	// [9, 11, 2, 5]

	System.out.println(linked);
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
