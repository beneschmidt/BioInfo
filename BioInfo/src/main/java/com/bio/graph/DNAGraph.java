package com.bio.graph;

import java.util.LinkedList;
import java.util.List;

public class DNAGraph implements Graph{
	
	private List<Node> nodes;
	
	public DNAGraph(){
		nodes = new LinkedList<Node>();
	}

	public void addNode(SequenceNode node){
		nodes.add(node);
	}
	
	@Override
	public List<Node> getNodes() {
		return nodes;
	}

}
