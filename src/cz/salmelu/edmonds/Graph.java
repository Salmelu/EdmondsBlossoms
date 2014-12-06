package cz.salmelu.edmonds;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A class representing a graph used for edmonds algorithm.
 * Also contains private fields for algorithm's markings
 * @author salmelu
 */
public class Graph {
	private Map<Vertex, Set<Vertex>> vertices;
	private Map<Vertex, Boolean> markedVertices;
	private Map<Vertex, Map<Vertex,Boolean>> markedEdges;
	
	/**
	 * Constructs a new empty graph
	 */
	public Graph() {
		vertices = new HashMap<>();
		markedVertices = new HashMap<>();
		markedEdges = new HashMap<>();
	}
	
	/**
	 * Adds a new vertex 
	 * @param v added vertex
	 */
	public void addVertex(Vertex v) {
		vertices.put(v, new HashSet<>());
	}
	
	/**
	 * Removes Vertex v from the graph
	 * @param v removed vertex
	 */
	public void removeVertex(Vertex v) {
		if(vertices.containsKey(v)) {
			for(Vertex u : vertices.get(v)) {
				// Remove from all the neighbours
				vertices.get(u).remove(v);
			}
			// Remove the vertex
			vertices.remove(v);
		}
	}
	
	/**
	 * Adds a new edge
	 * @param u first vertex of the edge
	 * @param v second vertex of the edge
	 */
	public void addEdge(Vertex u, Vertex v) {
		if(!vertices.containsKey(u) || !vertices.containsKey(v)) {
			return;
		}
		vertices.get(u).add(v);
		vertices.get(v).add(u);
	}
	
	/**
	 * Removes an edge
	 * @param u first vertex of the edge
	 * @param v second vertex of the edge
	 */
	public void removeEdge(Vertex u, Vertex v) {
		if(!vertices.containsKey(u) || !vertices.containsKey(v)) {
			return;
		}
		vertices.get(u).remove(v);
		vertices.get(v).remove(u);
	}
	
	/**
	 * Returns a map representing this graph
	 * @return a map with keys as vertices and values as a sets of neighbours
	 */
	public Map<Vertex,Set<Vertex>> getVertexMap() {
		return vertices;
	}
	
	/**
	 * Checks if a vertex is marked in this graph
	 * @param v checked vertex
	 * @return true, if it's marked, false otherwise
	 */
	public boolean isVertexMarked(Vertex v) {
		return (markedVertices.containsKey(v) && markedVertices.get(v) == true);
	}
	
	/**
	 * Sets a marked property of the v
	 * @param v a vertex
	 * @param marked a new marked value of vertex 
	 */
	public void setVertexMarked(Vertex v, boolean marked) {
		markedVertices.put(v, marked);
	}
	
	/**
	 * Checks, if an edge is marked
	 * @param v1 first vertex
	 * @param v2 second vertex
	 * @return true, if it's matched, false otherwise
	 */
	public boolean isEdgeMarked(Vertex v1, Vertex v2) {
		if(v1.getId().compareTo(v2.getId()) < 0) {
			if(!markedEdges.containsKey(v1)) return false;
			if(!markedEdges.get(v1).containsKey(v2)) return false;
			return markedEdges.get(v1).get(v2);
		}
		else {
			if(!markedEdges.containsKey(v2)) return false;
			if(!markedEdges.get(v2).containsKey(v1)) return false;
			return markedEdges.get(v2).get(v1);
		}
	}
	
	/**
	 * Sets a marked property of the edge
	 * @param v1 first vertex
	 * @param v2 second vertex
	 * @param marked a new marked value of edge 
	 */
	public void setEdgeMarked(Vertex v1, Vertex v2, boolean marked) {
		if(v1.getId().compareTo(v2.getId()) < 0) {
			if(!markedEdges.containsKey(v1)) {
				markedEdges.put(v1, new HashMap<>());
			}
			markedEdges.get(v1).put(v2, marked);
		}
		else {
			if(!markedEdges.containsKey(v2)) {
				markedEdges.put(v2, new HashMap<>());
			}
			markedEdges.get(v2).put(v1, marked);
		}
	}
}
