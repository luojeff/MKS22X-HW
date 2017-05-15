public class Quick {
    
    /*
      Partition method that implements the dutch-flag splitting method; 
      values less than the pivot are on one side, those greater than the
      pivot are on the other, and those equal are clumped in the middle. 
      Returns an array containing the indices of lt and gt
    */
    public static int[] partNew(int[] data, int start, int end){
	
	int lt = start;
	int gt = end;
	    
	// Chooses pivot
	int pivotIndex = (int)(Math.random() * (end - start + 1)) + start;
	int pivot = data[pivotIndex];

	// Counter that goes through "unprocessed" portion of the array
	int curr = start;

	// Makes first element equal to pivot
	swap(data, start, pivotIndex);

	// Swaps accordingly
	while(curr <= gt){
	    if(data[curr] > pivot){	        
		swap(data, curr, gt--);
	    } else if (data[curr] < pivot){
		swap(data, curr++, lt++);	        
	    } else {
		curr++;
	    }
	}

	int[] ret = new int[]{lt, gt};

	return ret;

	// return curr-1;
    }

    /* 
       Swaps elements of array at indices a and b 
       respectively
    */
    private static void swap(int[] data, int a, int b){
	int bVal = data[b];
	data[b] = data[a];
	data[a] = bVal;
    }

    /*
      Finds k-th smallest element by partitioning until the 
      pivot generated is the k-th index, in which it returns
      the value at that certain pivot point
    */
    public static int quickselect(int[] data, int k){
	
	int start = 0;
	int end = data.length-1;

	// Initial partition
	int[] piv = partNew(data, start, end);

        while (k < piv[0] || k > piv[1]){
	    if(k < piv[0]){
		end = piv[0];
		piv = partNew(data, start, end);
	    } else {
		start = piv[1];
		piv = partNew(data, start, end);
	    }
	}

	return data[k];
    }

    /*
      Sorts by continuously partitioning partitions (recursively)
      until each element is separated by a partition; by this point
      the array is sorted
    */
    public static void quicksort(int[] data){
	quickSortH(data, 0, data.length-1);
    }

    /*
      Modified partition to quicksort data array directly. Excludes
      repeating element clumps so this should be more efficient.
    */
    public static void quickSortH(int[] data, int start, int end){	

	if(end <= start+1){
	} else {
	    int lt = start;
	    int gt = end;
	    int pivotIndex = (int)(Math.random() * (end - start + 1)) + start;
	    int pivot = data[pivotIndex];
	    int curr = start;
	    
	    swap(data, start, pivotIndex);
	
	    while(curr <= gt){
		if(data[curr] > pivot){	        
		    swap(data, curr, gt--);
		} else if (data[curr] < pivot){
		    swap(data, curr++, lt++);	        
		} else {
		    curr++;
		}
	    }

	    // Sort is called on both sides of the partitioned array
	    quickSortH(data, start, lt);
	    quickSortH(data, gt, end);
	}
    }
    
    public static void main(String[] args){
	/*
	int[] b = new int[]{19, 3, 14, 2, 0, 1, 2, 14, 11, 20, 0, 14, 2};
	
	long startTime = System.nanoTime();

	System.out.println(quickselect(b, 10));
	quickSort(b);
	  
	long endTime = System.nanoTime();

	System.out.println("Time: " + (endTime - startTime)/Math.pow(10,6) + "ms");

	for(int i : b){
	    System.out.print(i + " ");
	}
	*/
    }
}
