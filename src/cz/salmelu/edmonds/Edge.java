package cz.salmelu.edmonds;

/**
 * Simple class with two public properties for representing the edge
 * @author salmelu
 */
public class Edge {
	public Vertex v1;
	public Vertex v2;
	
	public Edge(Vertex v1, Vertex v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
}
