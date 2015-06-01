package com.bio.graph;

import java.util.List;

/**
 * Node in a graph
 */
public interface Node {
	
	int getGrade();

	List<Edge> getEdges();

	Integer getId();

	Edge getEdgeToNode(Node node);
}
