public class MyHeap {
    String[] arr;
    int size;

    int multiplier = 1;

    public MyHeap(){
	arr = new String[1];
	size = arr.length; 
    }

    public MyHeap(boolean min){
	arr = new String[1];
	size = arr.length; 
	if(min){
	    multiplier = -1;
	}
    }

    public void add(String s){
    }

    public String remove(){
	return "";
    }

    public String peek(){
	return "";
    }

    private void resize(){
	size*=2;
	arr = new String[size];
    }

    private void pushUp(){}
    private void pushDown(){}

    public static void main(String[] args){	
    }
}
