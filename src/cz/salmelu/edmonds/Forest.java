package cz.salmelu.edmonds;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple class representing a forest
 * @author salmelu
 */
public class Forest {
	private List<Tree> forest = new ArrayList<>();
	
	/**
	 * Creates a new forest
	 */
	public Forest() {
		
	}
	
	/**
	 * Associate a new tree with this forest
	 * @param t a tree to be associated
	 */
	public void addTree(Tree t) {
		forest.add(t);
	}
	
	/**
	 * Checks if there is a vertex v in any of this forest's trees
	 * @param v a vertex
	 * @return true, if v is in any of the trees
	 */
	public boolean containsVertex(Vertex v) {
		for(Tree t : forest) {
			if(t.containsVertex(v)) return true;
		}
		return false;
	}
	
	/**
	 * Gets a tree with supplied vertex
	 * @param v a vertex
	 * @return a tree containing vertex v
	 */
	public Tree getContainingTree(Vertex v) {
		for(Tree t : forest) {
			if(t.containsVertex(v)) return t;
		}
		return null;
	}
	
	/**
	 * Returns an unmarked vertex in the forest, 
	 * for which the distance from the root is even
	 * @param g graph associated with the forest for marking purposes
	 * @return an unmarked vertex, if exists, null otherwise
	 */
	public Vertex getEvenUnmarkedVertex(Graph g) {
		Vertex v = null;
		for(Tree t : forest) {
			v = t.getEvenUnmarkedVertex(g);
			if(v != null) break;
		}
		return v;
	}
}
