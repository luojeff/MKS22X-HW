public class MyLinkedList {
    LNode start, end;
    int size;
    
    public MyLinkedList(LNode start){
        this.start = start;
    }

    public void add(){
    }

    public String toString(){
	String retString = "";

	LNode current = start;

	return retString;
    }

    public static void main(String[] args) {	
    }

    
    public class LNode {
	
	private int value;
	
	public LNode(int value){
	    this.value = value;
	}

	public int getValue(){
	    return value;
	}
    }
}
