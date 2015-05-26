package com.bio.utilities;

import com.bio.graph.Edge;
import com.bio.graph.HamiltonPath;
import com.bio.graph.Node;

public interface HamiltonPathCalculator<T extends Node, K extends Edge> {

	public HamiltonPath<T, K> getMaxHamiltonPath();
}
