package com.bio.utilities;

import java.util.Set;

import com.bio.graph.Edge;
import com.bio.graph.HamiltonPath;
import com.bio.graph.Node;

public interface HamiltonPathCalculator<T extends Node, K extends Edge> {

	public Set<HamiltonPath<T, K>> calculateHamiltonPaths();
}
