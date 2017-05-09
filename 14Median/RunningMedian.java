public class RunningMedian {
    private MyHeap small, large;
    private int smallSize, largeSize;
    private double median;
    
    public RunningMedian(){
	small = new MyHeap(true); // max heap
	large = new MyHeap(false); // min heap       
	smallSize = 0;
	largeSize = 0;
    };

    public void add(int i){
	if(i < median){
	    small.add(i);
	    smallSize++;
	} else {
 	    large.add(i);
	    largeSize++;
	}

	balanceHeaps();
	calculateMedian();	
    }

    private void balanceHeaps(){
	if(smallSize < largeSize){
	    small.add(large.remove());
	    smallSize++;
	    largeSize--;
	} else if (largeSize > smallSize){
	    large.add(small.remove());
	    largeSize++;
	    smallSize--;
	}
    }

    private void calculateMedian(){
	if(smallSize == largeSize){
	    median = (double)(small.peek() + large.peek())/2;
	} else if (Math.abs(smallSize - largeSize) == 1){
	    if(smallSize < largeSize){
		median = (double)large.peek();
	    } else {
		median = (double)small.peek();
	    }
	}
    }

    public double getMedian(){
	return median;
    }

    // For debugging
    public String toString(){
	return "small: " + small + "\nlarge: " + large;
    }

    public static void main(String[] args) {
	RunningMedian rm = new RunningMedian();

	rm.add(10);
	rm.add(34);
	rm.add(5);
	rm.add(20);
	rm.add(-10);
	rm.add(1);
	rm.add(19);
	rm.add(0);
	rm.add(35);
	rm.add(-4);
	rm.add(12);

	System.out.println(rm.getMedian());	
    }
}
