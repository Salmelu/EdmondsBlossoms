package cz.salmelu.edmonds;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Represents a blossom, which will be contracted
 * @author salmelu
 */
public class Blossom {
	/**
	 * List of vertices in the blossom, 
	 * with all the neighbours for reconstructing original graph later
	 */
	private Map<Vertex, Set<Vertex>> vertices;
	/**
	 * Contains a path around this blossom, for new constructing new pairing
	 */
	private LinkedList<Vertex> path;
	
	/**
	 * Creates a new blossom
	 */
	public Blossom() {
		vertices = new HashMap<>();
		path = new LinkedList<>();
	}
	
	/**
	 * Adds a new vertex to the blossom. 
	 * One should be careful and put vertices in the order they 
	 * lie in the blossom, so the path reconstructs correctly!
	 * @param v added vertex
	 * @param neighbours a set of v's neighbours
	 */
	public void addVertex(Vertex v, Set<Vertex> neighbours) {
		// Put it in the list
		vertices.put(v, neighbours);
		// And in the path
		path.add(v);
	}
	
	/**
	 * Checks if vertex is in the blossom
	 * @param v checked vertex
	 * @return true if v is in the blossom
	 */
	public boolean contains(Vertex v) {
		return vertices.keySet().contains(v);
	}

	/**
	 * Gets a set of vertices
	 * @return a set of vertices of this blossom
	 */
	public Set<Vertex> getVertices() {
		return vertices.keySet();
	}
	
	/**
	 * Gets a full vertex map, in format Map<Vertex, Set of neighbours>
	 * @return full vertex map
	 */
	public Map<Vertex, Set<Vertex>> getVertexMap() {
		return vertices;
	}
	
	/**
	 * Gets a path surrounding this blossom
	 * @return path
	 */
	public LinkedList<Vertex> getPath() {
		return path;
	}
}

