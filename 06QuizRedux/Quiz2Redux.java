import java.util.*;

public class Quiz2Redux {
    
    /*Returns an ArrayList<String> that contains all subsets of the
     *characters of String s. Assume s has no duplicate characters.
     *the characters should appear in the same order that they occur 
     *in the original string.
     */
    public static ArrayList<String> combinations(String s){
	ArrayList<String> words = new ArrayList<String>();
	help(words, s, 0);
	Collections.sort(words);
	return words;
    }
    
    private static void help(ArrayList<String> words, String s, int count){
	if(count == s.length()){
	} else {
	    int size = words.size();
	    if(size == 0){
		words.add("");
		words.add("" + s.charAt(count));
		help(words, s, count+1);
	    } else {
		for(int i=0; i<size; i++){
		    words.add(words.get(i)+s.charAt(count));	
		}
		help(words, s, count+1);
	    }
	}
    }
}
