package cz.salmelu.edmonds;

import java.util.Iterator;

/**
 * Main class giving all important algorithm methods
 * @author salmelu
 */
public class EdmondsAlgorithm {
	
	/**
	 * Uses a pretty dumb algorithm for finding an initial matching
	 * Takes a full list of vertices and for each vertex, it tries to find
	 * an unmarked neighbor to add a new matching edge. This should suit 
	 * enough to create an initial matching for Edmonds' algorithm and 
	 * save some time.
	 * @param g graph to find matching for
	 * @return a new matching for g
	 */
	public Matching findDumbMatching(Graph g) {
		Matching m = new Matching();
		for(Vertex v : g.getVertexMap().keySet()) {
			if(!v.isMatched()) {
				for(Vertex u : g.getVertexMap().get(v)) {
					if(!u.isMatched()) {
						u.setMatched(true);
						v.setMatched(true);
						m.addEdge(u,v);
						break;
					}
				}
			}
		}
		return m;
	}
	
	/**
	 * Augments a matching using an augmenting path p
	 * @param m augmented matching
	 * @param p augmenting path for the matching
	 * @return a new, augmented matching
	 */
	public Matching augmentMatching(Matching m, AugmentingPath p) {
		if(p.isEmpty()) return m;
		Vertex u, v;
		// Two steps : Deal with the unmarking
		Iterator<Vertex> pathIt = p.getPath();
		v = pathIt.next();
		boolean mark = true;
		while(pathIt.hasNext()) {
			u = v;
			v = pathIt.next();
			if(mark) {
				mark = false;
			}
			else {
				m.removeEdge(u, v);
				mark = true;
			}
		}
		// Deal with adding new edges
		pathIt = p.getPath();
		v = pathIt.next();
		mark = true;
		while(pathIt.hasNext()) {
			u = v;
			v = pathIt.next();
			if(mark) {
				m.addEdge(u, v);
				mark = false;
			}
			else {
				mark = true;
			}
		}
		return m;
	}
	
	/**
	 * Finds a maximum matching in a graph using an Edmonds' 
	 * blossom algorithm. 
	 * @param g checked graph
	 * @param m an initial matching in graph g
	 * @return a maximum possible matching
	 */
	public Matching findMaxMatching(Graph g, Matching m) {
		AugmentingPath p = findAugmentingPath(g, m);
		while(p != null) {
			m = augmentMatching(m, p);
			p = findAugmentingPath(g, m);
		}
		return m;
	}
	
	/**
	 * A procedure of Edmonds' blossom algorithm for 
	 * finding an augmenting path
	 * @param g a graph used for perfect matching
	 * @param m a matching
	 * @return an augmenting path for matching m if exists, null otherwise
	 */
	public AugmentingPath findAugmentingPath(Graph g, Matching m) {
		// Creates a forest and fills it with not matched vertices
		Forest forest = new Forest();
		for(Vertex u : g.getVertexMap().keySet()) {
			g.setVertexMarked(u, false);
			for(Vertex v : g.getVertexMap().keySet()) {
				if(u == v) continue;
				g.setEdgeMarked(u, v, false);
			}
			if(!u.isMatched()) {
				Tree f = new Tree(u);
				forest.addTree(f);
			}
		}
		
		// Get unmarked vertex, proceed by the steps of the algorithm
		Vertex v1 = forest.getEvenUnmarkedVertex(g);
		while(v1 != null) {
			for(Vertex v2 : g.getVertexMap().get(v1)) {
				if(g.isEdgeMarked(v1, v2)) continue;
				Tree t1 = forest.getContainingTree(v2);
				if(t1 == null) {
					Vertex v3 = m.getMatchedVertex(v2);
					Tree tv = forest.getContainingTree(v1);
					tv.addVertex(v2, v1);
					tv.addVertex(v3, v2);
				}
				else {
					if(t1.getDistance(v2) % 2 == 0) {
						if(forest.getContainingTree(v1).getRoot() != t1.getRoot()) {
							AugmentingPath p = new AugmentingPath();
							Vertex x = v1;
							Tree t2 = forest.getContainingTree(v1);
							while(x != null) {
								p.addVertexFront(x);
								x = t2.getParent(x);
							}
							x = v2;
							while(x != null) {
								p.addVertexBack(x);
								x = t1.getParent(x);
							}
							return p;
						}
						else {
							Blossom b = t1.getBlossom(g, v1, v2);
							Contraction ct = new Contraction(b);
							ct.contract(g, m);
							AugmentingPath p;
							p = findAugmentingPath(g, m);
							if(p == null) {
								ct.decontract(g, m);
								return null;
							}
							else {
								m = augmentMatching(m, p);
								ct.decontract(g, m);
								return new AugmentingPath();
							}
						}
					}
				}
				g.setEdgeMarked(v1, v2, true);
			}
			g.setVertexMarked(v1, true);
			v1 = forest.getEvenUnmarkedVertex(g);
		}
		return null;
	}
}
