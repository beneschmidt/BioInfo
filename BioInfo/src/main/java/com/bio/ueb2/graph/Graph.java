package com.bio.ueb2.graph;

import java.util.List;
import java.util.SortedSet;

public interface Graph<T extends Node, K extends Edge> {

	List<T> getNodes();

	SortedSet<K> getEdges();

	/**
	 * insert a new node into the graph and add all needed edges to and from the other nodes
	 * @param newNode
	 */
	void insertNewNode(T newNode);

}
