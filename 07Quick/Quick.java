import java.lang.StringBuilder;
import java.io.*;

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
	int piv = part(data, start, end);

        while (piv != k){
	    if(piv < k){
		start = piv;
	    } else if (piv > k){
		end = piv;
	    }

	    piv = part(data, start, end);	    
	}
	
	return data[piv];
    }

    /*
      Sorts by continuously partitioning partitions (recursively)
      until each element is separated by a partition; by this point
      the array is sorted
    */
    public static void quickSort(int[] data){
	quickSortH(data, 0, data.length-1);
    }
    
    private static void quickSortH(int[] data, int start, int end){
	int indPart = part(data, start, end);

	/* Recursive call on two subsequent partitions */
	if(end > indPart){
	    quickSortH(data, indPart+1, end);	    
	}

	if(indPart > start){
	    quickSortH(data, start, indPart-1);
	}
    }


    public static void main(String[] args){
    }    
}
