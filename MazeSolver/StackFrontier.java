import java.util.*;

public class StackFrontier implements Frontier {
    LinkedList<Location> linked;

    public StackFrontier(){
	linked = new LinkedList<Location>();
    }
    
    public void add(Location loc){
	linked.add(loc);
    }

    public Location next(){
	return linked.remove();
    }

    public boolean hasNext(){
	return linked.size() > 0;
    }
}
