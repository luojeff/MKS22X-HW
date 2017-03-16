public class Quick {
    public static int part(int[] data, int start, int end){
	/* goes only through subsection */
	int[] vals = new int[end-start];
		
	for(int i=start; i<end; i++){
	    vals[i-start] = data[i];	    
	}

	int pivotIndex = (int)(Math.random() * vals.length);
	int pivot = vals[pivotIndex];
	end--;
	
	for(int i=0; i<vals.length; i++){
	    if(i != pivotIndex){
		if(vals[i] <= pivot){
		    data[start++] = vals[i];	        
		} else if (vals[i] > pivot){
		    data[end--] = vals[i];
		}
	    }
	}

	data[start] = pivot;
		
        return start;
    }

    public static int quickselect(int[] data, int k){
	int start = 0;
	int end = data.length;
        
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

    public static void main(String[] args){
        int[] a = new int[]{9, 4, 3, 7, 1, 8, 6, 2, 5, 0};

	/* testing */
        System.out.println("\nElement: " + quickselect(a, 0));

	System.out.print("Partitioned Array:\n");
	for(int i : a){
	    System.out.print(i + " ");
	}
    }
}
