package cz.salmelu.edmonds.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
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
		if(args.length < 1) {
			System.out.println("No arguments found. Pass file names as arguments.");
			System.out.println("Format of the files:");
			System.out.println("List of vertices split by a comma, e.g. 'a,b,c,d'");
			System.out.println("Each following line contains one edge, in format 'a --- c'");
		}
		for(String fileName : args) {
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
			}
		}
	}
}
