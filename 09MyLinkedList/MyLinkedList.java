public class MyLinkedList {
    LNode start, end;
    int size;

    public MyLinkedList(){}
    
    public MyLinkedList(LNode start){
        this.start = start;
	size = 1;
    }

    public void add(int value){
	LNode ln = new LNode(value);
	add(ln);
    }
    
    public void add(LNode ln){
	if(start != null){
	    ln.setNext(start);
	    start = ln;	    	    
	} else {
	    start = ln;
	}
	size++;
    }

    public String toString(){
	String ret = "";
	LNode current = start;

	while(current != null){
	    ret += current.value + " ";
	    current = current.next;
	}
	
	return retString;
    }

    public static void main(String[] args) {
	MyLinkedList linked = new MyLinkedList();
	
	linked.add(10);
	linked.add(5);
	linked.add(7);

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
