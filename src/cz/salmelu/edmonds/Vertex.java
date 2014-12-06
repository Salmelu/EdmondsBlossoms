package cz.salmelu.edmonds;

/**
 * A simple holder of the vertices
 * @author salmelu
 */
public class Vertex {
	private String id;
	private boolean matched = false;
	
	/**
	 * Creates a new vertex
	 * @param id a unique string identifier of this vertex
	 */
	public Vertex(String id) {
		this.id = id;
	}
	
	/**
	 * Gets an identifier
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Checks, if this vertex is used in matching
	 * @return true, if it's matched
	 */
	public boolean isMatched() {
		return matched;
	}
	
	/**
	 * Sets, if v is matched
	 * @param matched
	 */
	public void setMatched(boolean matched) {
		this.matched = matched;
	}
}
