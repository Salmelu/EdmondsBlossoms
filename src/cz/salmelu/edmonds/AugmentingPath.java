package cz.salmelu.edmonds;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Represents an augmenting path in graph as a queue of vertices
 * @author salmelu
 */
public class AugmentingPath {

	/**
	 * Holds the queue of vertices
	 */
	private Deque<Vertex> path;
	
	/**
	 * Creates new augmenting path
	 */
	public AugmentingPath() {
		path = new LinkedList<>();
	}
	
	/**
	 * Checks if this path is empty
	 * @return true if this path is empty
	 */
	public boolean isEmpty() {
		return path.isEmpty();
	}
	
	/**
	 * Adds vertex to the front of the path
	 * @param v added vertex
	 */
	public void addVertexFront(Vertex v) {
		path.addFirst(v);
	}

	/**
	 * Adds vertex to the back of the path
	 * @param v added vertex
	 */
	public void addVertexBack(Vertex v) {
		path.addLast(v);
	}
	
	/**
	 * Gets an iterator on this path
	 * @return iterator on this augmenting path
	 */
	public Iterator<Vertex> getPath() {
		return path.iterator();
	}
	
	/**
	 * Gets size of this path
	 * @return size of this path
	 */
	public int getSize() {
		return path.size();
	}
	
}
