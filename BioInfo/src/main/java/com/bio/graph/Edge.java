package com.bio.graph;

/**
 * directed edge
 */
public interface Edge {
	
	Integer getWeight();
	
	Node getSuccessor();
	
	Node getPredecessor();

}
