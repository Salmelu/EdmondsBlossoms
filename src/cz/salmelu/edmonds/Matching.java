package cz.salmelu.edmonds;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a graph's matching, by a list of edges
 * @author salmelu
 */
public class Matching {
	private List<Edge> edges;
	
	/**
	 * Creates a new empty matching
	 */
	public Matching() {
		edges = new ArrayList<>();
	}
	
	/**
	 * Gets a neighbour vertex of v, which is matched along with v
	 * @param v a vertex in this matching
	 * @return matched v's neighbour, null if v isn't matched
	 */
	public Vertex getMatchedVertex(Vertex v) {
		for(Edge e : edges) {
			if(e.v1 == v) {
				return e.v2;
			}
			else if(e.v2 == v) {
				return e.v1;
			}
		}
		return null;
	}
	
	/**
	 * Adds a new edge to matching
	 * @param e an edge
	 */
	public void addEdge(Edge e) {
		edges.add(e);
		e.v1.setMatched(true);
		e.v2.setMatched(true);
	}
	
	/**
	 * Adds a new edge to matching
	 * @param v1 first vertex of the edge
	 * @param v2 second vertex of the edge
	 */
	public void addEdge(Vertex v1, Vertex v2) {
		edges.add(new Edge(v1,v2));
		v1.setMatched(true);
		v2.setMatched(true);
	}
	
	/**
	 * Removes an edge from matching
	 * @param e an edge
	 */
	public void removeEdge(Edge e) {
		edges.remove(e);
		e.v1.setMatched(false);
		e.v2.setMatched(false);
	}
	
	/**
	 * Removes an edge from matching
	 * @param v1 first vertex of the edge
	 * @param v2 second vertex of the edge
	 */
	public void removeEdge(Vertex v1, Vertex v2) {
		edges.removeIf(new Predicate<Edge>() {
			// A nice way to avoid using for loop with if
			@Override
			public boolean test(Edge t) {
				if((t.v1 == v1 && t.v2 == v2) || (t.v1 == v2 && t.v2 == v1)) {
					v1.setMatched(false);
					v2.setMatched(false);
					return true;
				}
				return false;
			}
		});
	}
	/**
	 * Removes an edge from matching containing v 
	 * @param v a vertex of the edge
	 */
	public void removeEdge(Vertex v) {
		edges.removeIf(new Predicate<Edge>() {
			@Override
			public boolean test(Edge t) {
				if(t.v1 == v || t.v2 == v) {
					t.v1.setMatched(false);
					t.v2.setMatched(false);
					return true;
				}
				return false;
			}
		});
	}
	
	/**
	 * Gets a list of edges of the matching
	 * @return a list of edges 
	 */
	public List<Edge> getEdges() {
		return edges;
	}
}
