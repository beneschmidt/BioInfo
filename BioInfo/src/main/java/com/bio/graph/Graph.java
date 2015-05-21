package com.bio.graph;

import java.util.List;
import java.util.SortedSet;

public interface Graph<T extends Node, K extends Edge> {

	List<T> getNodes();

	SortedSet<K> getEdges();

}
