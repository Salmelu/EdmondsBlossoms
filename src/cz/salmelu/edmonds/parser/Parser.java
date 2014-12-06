package cz.salmelu.edmonds.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import cz.salmelu.edmonds.Edge;
import cz.salmelu.edmonds.EdmondsAlgorithm;
import cz.salmelu.edmonds.Graph;
import cz.salmelu.edmonds.Matching;
import cz.salmelu.edmonds.Vertex;

public class Parser {
	public static Graph parseGraph(File f) {
		Graph g = new Graph();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)))) {
			String[] strVertices = br.readLine().split(",");
			Map<String, Vertex> vertices = new TreeMap<String, Vertex>();
			for(int i=0; i<strVertices.length; i++) {
				vertices.put(strVertices[i], new Vertex(strVertices[i]));
			}
			for(Vertex v : vertices.values()) {
				g.addVertex(v);
			}
			String line;
			while((line = br.readLine()) != null) {
				if(line.matches("\\s*")) continue;
				String[] edge = line.trim().split("\\s*---\\s*");
				if(!vertices.containsKey(edge[0]) || !vertices.containsKey(edge[1])) continue;
				g.addEdge(vertices.get(edge[0]), vertices.get(edge[1]));
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return g;
	}
	
	public static void main(String args[]) {
		boolean printDot = true;
		boolean foundFile = false;
		for(String fileName : args) {
			if(fileName.equals("-n")) {
				printDot = false;
				continue;
			}
			foundFile = true;
			File f = new File(fileName);
			if(!f.exists() || !f.canRead()) {
				System.out.println("File " + fileName + " doesn't exist, or isn't readable.");
			}
			else {
				Graph g = parseGraph(f);
				EdmondsAlgorithm ea = new EdmondsAlgorithm();
				Matching m = ea.findMaxMatching(g, ea.findDumbMatching(g));
				System.out.println("Matching:");
				for(Edge e : m.getEdges()) {
					System.out.println(e.v1.getId() + " --- " + e.v2.getId());
				}
				if(printDot) {
					File out = null;
					if(fileName.contains(".")) {
						out = new File(fileName.substring(0, fileName.lastIndexOf(".")) + ".dot");
					}
					else {
						out = new File(fileName + ".dot");
					}
					try (PrintStream ps = new PrintStream(new FileOutputStream(out))) {
						ps.println("Graph G {");
						int size = g.getVertexMap().keySet().size();
						int i = 0;
						for(Vertex v : g.getVertexMap().keySet()) {
							i++;
							ps.print(v.getId());
							if(i != size) ps.print(",");
						}
						ps.println();
						Set<Set<Vertex>> added = new HashSet<>();
						for(Edge e : m.getEdges()) {
							HashSet<Vertex> hs = new HashSet<>();
							hs.add(e.v1);
							hs.add(e.v2);
							added.add(hs);
							ps.println(e.v1.getId() + " -- " + e.v2.getId() + "  [color=\"red\"]");
						}
						for(Vertex v : g.getVertexMap().keySet()) {
							for(Vertex w : g.getVertexMap().get(v)) {
								HashSet<Vertex> hs = new HashSet<>();
								hs.add(v);
								hs.add(w);
								int sizeHS = added.size();
								added.add(hs);
								if(sizeHS != added.size()) {
									ps.println(v.getId() + " -- " + w.getId() + "  [color=\"black\"]");
								}
							}
						}
						ps.println("}");
					} catch (FileNotFoundException e) {
						System.out.println("Couldn't generate a dot file " + out.getPath());
					}
				}
			}
		}
		if(!foundFile) {
			System.out.println("No arguments found. Pass file names as arguments.");
			System.out.println("If the first argument is -n, doesn't create dot files");
			System.out.println("Format of the files:");
			System.out.println("List of vertices split by a comma, e.g. 'a,b,c,d'");
			System.out.println("Each following line contains one edge, in format 'a --- c'");
		}
	}
}
