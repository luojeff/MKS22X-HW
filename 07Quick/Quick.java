public class Quick {
    public static int part(int[] data, int start, int end){
	int[] vals = new int[end-start];
		
	for(int i=start; i<end; i++){
	    vals[i-start] = data[i];
	}

	int pivot = vals[(int)(Math.random() * vals.length)];	
       
	int scount = start;
	int ecount = end-1;
	
	for(int i=0; i<vals.length; i++){
	    if(vals[i] <= pivot){
		data[scount] = vals[i];
		scount++;
	    } else if (vals[i] > pivot){
		data[ecount] = vals[i];
		ecount--;
	    }
	}
	
	data[scount] = pivot;
	
        return scount;
    }

    public static int quickselect(int[] data, int k){
	int start = 0;
	int end = data.length;
        
	int piv = part(data, start, end);
	System.out.println("Current piv: " + piv);

	k++;
        while (piv != k){
	    if(piv < k){
		start = piv;
	    } else if (piv > k){
		end = piv;
	    }
	    piv = part(data, start, end);
	    System.out.println("Current piv: " + piv);
	}

	return data[piv];
    }

    public static void main(String[] args){
        int[] a = new int[]{9, 3, 11, 4, 20, 1, 7, 4};

	
	System.out.println("Element: " + quickselect(a, 0));

	/*
	for(int i : a){
	    System.out.println(i);
	}
	*/
    }
}
