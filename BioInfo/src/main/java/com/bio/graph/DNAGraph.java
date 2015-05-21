package com.bio.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class DNAGraph implements Graph{
	
	private List<Node> nodes;
	private SortedSet<Edge> edges;
	
	public DNAGraph(){
		nodes = new LinkedList<Node>();
		edges = new TreeSet<Edge>();
	}

	public void addNode(SequenceNode node){
		nodes.add(node);
	}
	
	@Override
	public List<Node> getNodes() {
		return nodes;
	}

	public SortedSet<Edge> getEdges() {
		return edges;
	}
	
	public void addEdge(DirectedEdge edge){
		edges.add(edge);
	}

}
