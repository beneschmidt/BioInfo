package com.bio.ueb2.utilities;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.bio.ueb2.graph.Edge;
import com.bio.ueb2.graph.HamiltonPath;
import com.bio.ueb2.graph.Node;

/**
 * similar to the fast hamiltonPath calculator by id, just that the path itself is based on integer values but full objects which is slower than the id
 * implementation because of the references that need to be copied.
 *
 * @param <T>
 * @param <K>
 */
public class FastHamiltonPathCalculatorByNode<T extends Node, K extends Edge> implements HamiltonPathCalculator<T, K> {
	private Map<Integer, T> nodeMap;

	public FastHamiltonPathCalculatorByNode(List<T> inputList, Map<Integer, T> nodeMap) {
		this.nodeMap = nodeMap;
	}

	@Override
	public HamiltonPath<T, K> getMaxHamiltonPath() {
		long start = System.currentTimeMillis();
		HamiltonPath<T, K> finished = combine();
		System.out.println("Time: " + (System.currentTimeMillis() - start));
		return finished;
	}

	private HamiltonPath<T, K> combine() {
		Set<HamiltonPath<T, K>> fullList = new TreeSet<HamiltonPath<T, K>>();
		List<String> nodeIds = new LinkedList<String>();
		for (T next : nodeMap.values()) {
			List<T> newNodes = new LinkedList<T>();
			newNodes.add(next);
			nodeIds.add(next.getId() + "");
			System.out.println("Iteration: " + next.getId());
			fullList.addAll(combine(newNodes));
		}
		if (!fullList.isEmpty()) {
			return fullList.iterator().next();
		} else {
			return null;
		}
	}

	/**
	 * combine the current node path with the next possible node. This node must not be in the current list and must be a part of the edges of the last
	 * node in the current list. This actually saves a lot of time. If the current node list reached its end, the list added to a list of lists. This list of lists
	 * is created over all possible paths in a recursive way.
	 * 
	 * @param currentNodes
	 * @return Set of Hamilton paths
	 */
	@SuppressWarnings("unchecked")
	private Set<HamiltonPath<T, K>> combine(List<T> currentNodes) {
		Set<HamiltonPath<T, K>> fullList = new TreeSet<HamiltonPath<T, K>>();
		if (currentNodes.size() == nodeMap.size()) {
			HamiltonPath<T, K> seq = new HamiltonPath<T, K>(currentNodes);
			fullList.add(seq);
			return fullList;
		}
		Node node = currentNodes.get(currentNodes.size() - 1); // get last item
		for (Edge e : node.getEdges()) {
			if (!currentNodes.contains(e.getSuccessor())) {
				List<T> currentNodesCopy = new LinkedList<T>(currentNodes);
				currentNodesCopy.add((T) e.getSuccessor());
				fullList.addAll(combine(currentNodesCopy));
			}
		}
		return fullList;
	}
}
