package com.bio.ueb2.graph;

/**
 * Interface for an edge in a graph. A weight is necessary.
 */
public interface Edge {
	
	Integer getWeight();
	
	Node getSuccessor();
	
	Node getPredecessor();

}
