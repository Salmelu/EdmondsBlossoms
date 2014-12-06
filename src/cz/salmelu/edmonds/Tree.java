package cz.salmelu.edmonds;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Tree {
	// Private class holding tree nodes, shouldn't be messed with
	private class Node {
		private Vertex v;
		private Node parent = null;
		private Set<Node> sons = new HashSet<>();

		public Node(Vertex v) {
			this.v = v;
		}
		
		public Vertex getV() {
			return v;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}
		
		public void addSon(Node u) {
			this.sons.add(u);
		}
	}
	
	// A root of this tree
	private Node root;
	// Mapping of vertices to nodes for fast searching
	private HashMap<Vertex, Node> mapping;
	
	/**
	 * Creates a new empty tree
	 * @param root a vertex which will be a root
	 */
	public Tree(Vertex root) {
		this.root = new Node(root);
		mapping = new HashMap<>();
		mapping.put(root, this.root);
	}
	
	/**
	 * Gets a root of this tree
	 * @return a root vertex
	 */
	public Vertex getRoot() {
		return root.getV();
	}
	
	/**
	 * Gets a parent of v
	 * @param v queried vertex
	 * @return a parent of v, if exists, otherwise null
	 */
	public Vertex getParent(Vertex v) {
		if(mapping.get(v).parent == null) return null;
		return mapping.get(v).parent.getV();
	}
	
	/**
	 * Adds a new vertex to the tree
	 * @param newVertex added vertex
	 * @param parent parent of newVertex
	 */
	public void addVertex(Vertex newVertex, Vertex parent) {
		if(!mapping.containsKey(parent)) return;
		Node n = new Node(newVertex);
		mapping.put(newVertex, n);
		n.setParent(mapping.get(parent));
		mapping.get(parent).addSon(n);
	}
	
	/**
	 * Checks if v is in this tree
	 * @param v desired vertex
	 * @return true, if v is in this tree
	 */
	public boolean containsVertex(Vertex v) {
		return mapping.keySet().contains(v);
	}
	
	/**
	 * Returns v's distance from the root
	 * @param v queried vertex
	 * @return length of the path root --> v
	 */
	public int getDistance(Vertex v) {
		if(!mapping.containsKey(v)) return -1;
		Node n = mapping.get(v);
		int dist = 0;
		while(n.parent != null) {
			n = n.parent;
			dist++;
		}
		return dist;
	}
	
	/**
	 * Looks for any unmarked vertex with even distance
	 * @param g graph to be searched, used for marking
	 * @return an unmarked vertex with odd distance, null if it doesn't exist
	 */
	public Vertex getEvenUnmarkedVertex(Graph g) {
		for(Vertex v : mapping.keySet()) {
			if(!g.isVertexMarked(v) && getDistance(v) % 2 == 0) return v;
		}
		return null;
	}
	
	/**
	 * Creates a new blossom between two vertices
	 * @param g a graph associated with vertices u and v
	 * @param u a vertex
	 * @param v a vertex 
	 * @return a new blossom with u and v using edges from this tree
	 */
	public Blossom getBlossom(Graph g, Vertex u, Vertex v) {
		// Create paths
		LinkedList<Vertex> path1 = new LinkedList<>();
		LinkedList<Vertex> path2 = new LinkedList<>();
		Vertex x = u;
		// Get path from u --> root
		while(x != null) {
			path1.addLast(x);
			if(x != root.getV())
				x = mapping.get(x).getParent().getV();
			else 
				x = null;
		}
		x = v;
		// Get path from v --> root
		while(x != null) {
			path2.addLast(x);
			if(x != root.getV())
				x = mapping.get(x).getParent().getV();
			else 
				x = null;
		}
		// Get latest intersection x
		while(!path1.isEmpty() && !path2.isEmpty() && path1.getLast() == path2.getLast()) {
			x = path1.pollLast();
			path2.pollLast();
		}
		// Create blossom using path1 and path2, by u --> x --> v --> u
		Blossom b = new Blossom();
		Map<Vertex, Set<Vertex>> vertexMap = g.getVertexMap();
		Vertex y = path1.pollFirst();
		HashSet<Vertex> hs;
		// Copy all the neighbors (need a copy to mess with original graph)
		while(y != null) {
			hs = new HashSet<>();
			for(Vertex f : vertexMap.get(y)) {
				hs.add(f);
			}
			b.addVertex(y, hs);
			y = path1.pollFirst();
		}
		hs = new HashSet<>();
		for(Vertex f : vertexMap.get(x)) {
			hs.add(f);
		}
		b.addVertex(x, hs);
		Vertex z = path2.pollLast();
		while(z != null) {
			hs = new HashSet<>();
			for(Vertex f : vertexMap.get(z)) {
				hs.add(f);
			}
			b.addVertex(z, hs);
			z = path2.pollLast();
		}
		return b;
	}
}
