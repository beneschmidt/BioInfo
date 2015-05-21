package com.bio.graph;

import java.util.LinkedList;
import java.util.List;

public class SequenceNode implements Node{

	private List<Edge> edges;
	
	
	public SequenceNode(){
		edges = new LinkedList<Edge>();
	}
	
	@Override
	public int getGrade() {
		return edges.size();
	}
	
	public void addEdge(DirectedEdge edge){
		edges.add(edge);
	}

	@Override
	public List<Edge> getEdges() {
		return edges;
	}

}
