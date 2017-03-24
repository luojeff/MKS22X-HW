public class Merge {

    public static void mergesort(int[] data){

	if(data.length == 1){	    
	} else {	    
	    int splitInd = data.length / 2;
	
	    // mergesort left
	    int[] left = new int[splitInd];
	    for(int i=0; i<splitInd; i++){
		left[i] = data[i];
	    }
	    
	    mergesort(left);
	    
	    // mergesort right
	    int[] right = new int[data.length - splitInd];
	    for(int i=splitInd; i<data.length; i++){
		right[i-splitInd] = data[i];
	    }
	    
	    mergesort(right);
	    
	    // merge left and right into array
	    merge(left, right, data);
	}
    }

    /*
      Merges two arrays that are pre-sorted into one
      that is sorted, containing the elements from
      both input arrays
    */    
    public static void merge(int[] a, int[] b, int[] destination){
	int aC = 0;
	int bC = 0;
	int curr = 0;

	while(a.length - aC > 0 && b.length - bC > 0){
	    if(a[aC] < b[bC]){
		destination[curr++] = a[aC++];
	    } else {
		destination[curr++] = b[bC++];
	    }
	}

	// If remaining array has values, these are added
	// to the destination array	
	if(!(a.length - aC == 0 && b.length - bC == 0)){
	    if(a.length - aC == 0){
		for(int i=bC; i<b.length; i++){
		    destination[curr++] = b[i];
		}		
	    } else {
		for(int i=aC; i<a.length; i++){
		    destination[curr++] = a[i];
		}
	    }
	}
    }

    public static void main(String[] args){
	// MERGESORT TEST CASE
	  
	int[] test = new int[1000000];
	
	for(int i=0; i<test.length; i++){
	    test[i] = (int)(Math.random()*10);
	}	
	
	long startTime = System.nanoTime();

	mergesort(test);	

	long endTime = System.nanoTime();
	long elapsedTimeMS = (endTime - startTime)/(long)Math.pow(10,6);
	System.out.println("Time: " +  elapsedTimeMS + "ms");

	/*
	for(int i : test){
	    System.out.print(i + " ");
	}	
	*/
	
	/*
	// MERGE function TEST CASE

	int[] a = new int[]{-3,1,2,5,10,11,25,100};
	int[] b = new int[]{1,2,11,13,25,101};

	int[] c = new int[a.length + b.length];

	merge(a,b,c);

	for (int i = 0; i < c.length; i++) {
	System.out.print(c[i] + " ");
	}

	*/
    }
}
