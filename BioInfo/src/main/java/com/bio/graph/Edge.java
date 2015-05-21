package com.bio.graph;

/**
 * directed edge
 */
public interface Edge {
	
	int getWeight();
	
	Node getSuccessor();
	
	Node getPredecessor();

}
