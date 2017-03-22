public class Quick {
    public static int part(int[] data, int start, int end){
	
	/* Chooses pivot */
	int pivotIndex = (int)(Math.random() * (end - start + 1)) + start;
	int pivot = data[pivotIndex];        

	/* Goes through "unprocessed" portion of the array */
	int curr = start;

	/* Makes first element equal to pivot */
	swap(data, start, pivotIndex);
	
	while(curr <= end){
	    if(data[curr] > pivot){	        
		swap(data, curr, end--);	        
	    } else if (data[curr] < pivot){
		swap(data, curr++, start++);	        
	    } else {
		curr++;
	    }
	}
	
        return curr-1;
    }

    /*
      Same as partition method but implements dutch-flag partition
      and returns an array containing the indices of lt and gt;
    */
    public static int partNew(int[] data, int start, int end){
	
	int lt = start;
	int gt = end;
	    
	/* Chooses pivot */
	int pivotIndex = (int)(Math.random() * (end - start + 1)) + start;
	int pivot = data[pivotIndex];

	/* Goes through "unprocessed" portion of the array */
	int curr = start;

	/* Makes first element equal to pivot */
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

	/*
	int rand = (int)(Math.random()*9);
	if(rand % 3 == 0){
	    return lt;
	} else if (rand % 3 == 1){
	    return gt;
	}
	*/
	return curr-1;
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

	/* initial partition */	
	int piv = partNew(data, start, end);

        while (piv != k){	    
	    if(piv < k){
		System.out.println("New piv: " + piv);
		piv = partNew(data, piv, end);
	    } else if (piv > k){
		piv = partNew(data, start, piv);
	    }
	}
	return data[piv];
    }

    /*
      Sorts by continuously partitioning partitions (recursively)
      until each element is separated by a partition; by this point
      the array is sorted
    */
    public static void quickSort(int[] data){
	// quickSortH(data, 0, data.length-1);
	partQuickSort(data, 0, data.length-1);
    }

    /*
      Modified partition to quicksort data array directly. Excludes
      repeating element clumps so this should be more efficient.
    */
    public static void partQuickSort(int[] data, int start, int end){	

	if(end <= start+1){
	} else {

	    int lt = start;
	    int gt = end;
	    
	    /* Chooses pivot */
	    int pivotIndex = (int)(Math.random() * (end - start + 1)) + start;
	    int pivot = data[pivotIndex];

	    /* Goes through "unprocessed" portion of the array */
	    int curr = start;

	    /* Makes first element equal to pivot */
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

	    partQuickSort(data, start, lt);
	    partQuickSort(data, gt, end);
	}
    }

    /*
      Quick sort helper method, used alongside the regular partition
    
    private static void quickSortH(int[] data, int start, int end){
	int indPart = part(data, start, end);

	// Recursive call on two subsequent partitions
	if(end > indPart){
	    quickSortH(data, indPart+1, end);	    
	}

	if(indPart > start){
	    quickSortH(data, start, indPart-1);
	}
    }
    */
    
    public static void main(String[] args){


	
	  int[] b = new int[10000];
	  for(int i=0; i<1000; i++){
	  b[i] = (int)(Math.random()*10000);
	  }

	  long startTime = System.nanoTime();
	  System.out.println(quickselect(b, 1000));
	  //quickSort(b);
	  long endTime = System.nanoTime();

	  System.out.println("Time: " + (endTime - startTime)/Math.pow(10,9) + "s");
    }
}
