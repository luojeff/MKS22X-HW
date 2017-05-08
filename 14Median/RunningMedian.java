public class RunningMedian {
    private MyHeap small;
    private MyHeap large;
    private int smallSize, largeSize, median;
    
    public RunningMedian(){
	small = new MyHeap(true); // max heap
	large = new MyHeap(false); // min heap
	smallSize = 0;
	largeSize = 0;
    };

    public void add(int i){
	if(i < median){
	    small.add(i);
	} else {
	    large.add(i);
	}
    }

    public double getMedian(){
	return 1.0;
    }
}
