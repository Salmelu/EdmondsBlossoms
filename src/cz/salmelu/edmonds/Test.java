package cz.salmelu.edmonds;

public class Test {
	public static void test1() {
		Graph g = new Graph();
		Vertex[] vs = new Vertex[14];
		vs[0] = new Vertex("a");
		vs[1] = new Vertex("b");
		vs[2] = new Vertex("c");
		vs[3] = new Vertex("d");
		vs[4] = new Vertex("e");
		vs[5] = new Vertex("f");
		vs[6] = new Vertex("g");
		vs[7] = new Vertex("h");
		vs[8] = new Vertex("i");
		vs[9] = new Vertex("j");
		vs[10] = new Vertex("k");
		vs[11] = new Vertex("l");
		vs[12] = new Vertex("m");
		vs[13] = new Vertex("n");
		g.addVertex(vs[0]);
		g.addVertex(vs[1]);
		g.addVertex(vs[2]);
		g.addVertex(vs[3]);
		g.addVertex(vs[4]);
		g.addVertex(vs[5]);
		g.addVertex(vs[6]);
		g.addVertex(vs[7]);
		g.addVertex(vs[8]);
		g.addVertex(vs[9]);
		g.addVertex(vs[10]);
		g.addVertex(vs[11]);
		g.addVertex(vs[12]);
		g.addVertex(vs[13]);
		g.addEdge(vs[0], vs[1]);
		g.addEdge(vs[1], vs[2]);
		g.addEdge(vs[2], vs[3]);
		g.addEdge(vs[3], vs[4]);
		g.addEdge(vs[4], vs[0]);
		g.addEdge(vs[0], vs[5]);
		g.addEdge(vs[5], vs[6]);
		g.addEdge(vs[6], vs[7]);
		g.addEdge(vs[3], vs[8]);
		g.addEdge(vs[8], vs[9]);
		g.addEdge(vs[9], vs[10]);
		g.addEdge(vs[9], vs[11]);
		g.addEdge(vs[10], vs[12]);
		g.addEdge(vs[11], vs[12]);
		g.addEdge(vs[11], vs[13]);
		EdmondsAlgorithm ea = new EdmondsAlgorithm();
		Matching m = ea.findDumbMatching(g);
		System.out.println("1");
		for(Edge e : m.getEdges()) {
			System.out.println(e.v1.getId() + " - " + e.v2.getId());
		}
		m = ea.findMaxMatching(g, m);
		System.out.println("2");
		for(Edge e : m.getEdges()) {
			System.out.println(e.v1.getId() + " - " + e.v2.getId());
		}
	}

	public static void test2() {
		Graph g = new Graph();
		Vertex[] vs = new Vertex[22];
		vs[0] = new Vertex("a");
		vs[1] = new Vertex("b");
		vs[2] = new Vertex("c");
		vs[3] = new Vertex("d");
		vs[4] = new Vertex("e");
		vs[5] = new Vertex("f");
		vs[6] = new Vertex("g");
		vs[7] = new Vertex("h");
		vs[8] = new Vertex("i");
		vs[9] = new Vertex("j");
		vs[10] = new Vertex("k");
		vs[11] = new Vertex("l");
		vs[12] = new Vertex("m");
		vs[13] = new Vertex("n");
		vs[14] = new Vertex("o");
		vs[15] = new Vertex("p");
		vs[16] = new Vertex("q");
		vs[17] = new Vertex("r");
		vs[18] = new Vertex("s");
		vs[19] = new Vertex("t");
		vs[20] = new Vertex("u");
		vs[21] = new Vertex("v");
		for(int i=0; i<22; i++) {
			g.addVertex(vs[i]);
		}
		g.addEdge(vs[0], vs[1]);
		g.addEdge(vs[1], vs[2]);
		g.addEdge(vs[2], vs[3]);
		g.addEdge(vs[3], vs[4]);
		g.addEdge(vs[2], vs[4]);
		g.addEdge(vs[4], vs[5]);
		g.addEdge(vs[5], vs[6]);
		g.addEdge(vs[5], vs[7]);
		g.addEdge(vs[5], vs[19]);
		g.addEdge(vs[6], vs[7]);
		g.addEdge(vs[6], vs[17]);
		g.addEdge(vs[7], vs[8]);
		g.addEdge(vs[8], vs[9]);
		g.addEdge(vs[8], vs[10]);
		g.addEdge(vs[9], vs[14]);
		g.addEdge(vs[10], vs[21]);
		g.addEdge(vs[10], vs[11]);
		g.addEdge(vs[11], vs[12]);
		g.addEdge(vs[11], vs[14]);
		g.addEdge(vs[12], vs[13]);
		g.addEdge(vs[12], vs[20]);
		g.addEdge(vs[13], vs[14]);
		g.addEdge(vs[14], vs[15]);
		g.addEdge(vs[15], vs[16]);
		g.addEdge(vs[16], vs[17]);
		g.addEdge(vs[18], vs[19]);
		g.addEdge(vs[18], vs[21]);
		g.addEdge(vs[19], vs[20]);
		g.addEdge(vs[20], vs[21]);
		EdmondsAlgorithm ea = new EdmondsAlgorithm();
		Matching m = ea.findDumbMatching(g);
		System.out.println("1");
		for(Edge e : m.getEdges()) {
			System.out.println(e.v1.getId() + " - " + e.v2.getId());
		}
		m = ea.findMaxMatching(g, m);
		System.out.println("2");
		for(Edge e : m.getEdges()) {
			System.out.println(e.v1.getId() + " - " + e.v2.getId());
		}
	}
	
	public static void main(String args[]) {
		test1();
		System.out.println("-----------------------");
		test2();
	}
}
