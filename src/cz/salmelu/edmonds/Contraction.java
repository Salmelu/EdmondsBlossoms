package cz.salmelu.edmonds;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Represents each contraction in the graph
 * @author salmelu
 */
public class Contraction {
	/**
	 * Blossom which was contracted
	 */
	private Blossom b;
	/**
	 * Vertex which substitutes the blossom
	 */
	private Vertex s;
	
	/**
	 * Creates a new Contraction instance with associated blossom
	 * @param b Blossom which will be used for contraction
	 */
	public Contraction(Blossom b) {
		this.b = b;
	}
	
	/**
	 * Contracts a graph by this instance's blossom
	 * @param g contracted graph
	 * @param m matching of graph g
	 */
	public void contract(Graph g, Matching m) {
		// Create a new Vertex with random id
		Random rand = new Random();
		s = new Vertex(Integer.toString(rand.nextInt()));
		g.addVertex(s);
		// Get all vertices of g
		Map<Vertex, Set<Vertex>> vertexMap = g.getVertexMap();
		for(Vertex v : b.getVertices()) {
			// Remove each vertex from matching if needed
			if(v.isMatched()) {
				Vertex w = m.getMatchedVertex(v);
				if(!b.contains(w)) {
					m.removeEdge(v, w);
					m.addEdge(w, s);
				}
				else {
					m.removeEdge(v, w);
				}
			}
			// create edges to the new vertex
			for(Vertex w : vertexMap.get(v)) {
				if(!b.contains(w)) {
					g.addEdge(s, w);
				}
			}
			g.removeVertex(v);
		}
	}
	
	/**
	 * Reverses contraction done by contract, recreates a new matching using blossom's edges
	 * @param g contracted graph
	 * @param m a matching of the contracted graph g
	 */
	public void decontract(Graph g, Matching m) {
		// Deal with matching, use the path to circulate the blossom and create a matching
		Vertex u = m.getMatchedVertex(s);
		Iterator<Vertex> it = b.getPath().iterator();
		Vertex v = null;
		for(Vertex q : b.getVertices()) {
			if(b.getVertexMap().get(q).contains(u)) {
				v = q;
			}
		}
		// There is no edge matched, something like C_2k+1 happened
		if(u != null) {
			m.removeEdge(u, s);
			m.addEdge(v, u);
		}
		Vertex w = null;
		// Simulate starting edge in the circle
		if(v == null) {
			v = it.next();
			w = v;
		}
		while(w != v) {
			w = it.next();
		}
		if(it.hasNext()) {
			w = it.next();
		}
		else {
			it = b.getPath().iterator();
			w = it.next();
		}
		// We have found the "entrying" edge, let's reconstruct it
		while(w != v) {
			if(it.hasNext()) {
				m.addEdge(w, it.next());
				if(it.hasNext()) {
					w = it.next();
				}
				else {
					it = b.getPath().iterator();
					w = it.next();
				}
			}
			else {
				it = b.getPath().iterator();
				m.addEdge(w, it.next());
				w = it.next();
			}
		}
		
		// And deal with reconstruction of the graph
		for(Vertex x : b.getVertices()) {
			g.addVertex(x);
		}
		for(Vertex x : b.getVertices()) {
			for(Vertex y : b.getVertexMap().get(x)) {
				g.addEdge(x, y);
			}
		}
		g.removeVertex(s);
	}
}
