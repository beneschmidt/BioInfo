package com.bio.utilities;

import com.bio.graph.Edge;
import com.bio.graph.HamiltonPath;
import com.bio.graph.Node;

/**
 * Calcualation of a hamilton path. To keep memory usage as low as possible not all paths are selected, but just the one with maximum weight
 *
 * @param <T>
 * @param <K>
 */
public interface HamiltonPathCalculator<T extends Node, K extends Edge> {

	public HamiltonPath<T, K> getMaxHamiltonPath();
}
