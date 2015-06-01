package com.bio.utilities;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.bio.graph.Edge;
import com.bio.graph.HamiltonPath;
import com.bio.graph.Node;

/**
 * INFO: too slow, couldn't finish it once
 *
 * @param <T>
 * @param <K>
 */
public class IntegerHamiltonPathCalculator<T extends Node, K extends Edge> implements HamiltonPathCalculator<T, K> {
	private List<Integer> inputIdList;
	private Map<Integer, T> nodeMap;

	public IntegerHamiltonPathCalculator(List<T> inputList, Map<Integer, T> nodeMap) {
		inputIdList = new LinkedList<Integer>();
		for (Node n : inputList) {
			inputIdList.add((int) n.getId());
		}
		this.nodeMap = nodeMap;
	}

	public HamiltonPath<T, K> getMaxHamiltonPath() {
		long start = System.currentTimeMillis();
		Set<HamiltonPath<T, K>> finished = combine(new LinkedList<Integer>(), inputIdList);
		System.out.println("Time: " + (System.currentTimeMillis() - start));
		if (finished.size() > 0) {
			return finished.iterator().next();
		} else {
			return null;
		}
	}

	/**
	 * stops if the so far build path is not valid to improve runtime
	 * @param nodes
	 * @param subnodes
	 * @return Set of Hamilton paths
	 */
	private Set<HamiltonPath<T, K>> combine(List<Integer> nodes, List<Integer> subnodes) {
		Set<HamiltonPath<T, K>> fullList = new TreeSet<HamiltonPath<T, K>>();
		boolean currentPathValid = HamiltonPath.fromIds(nodes, nodeMap).isValid();
		if (!currentPathValid) {
			return fullList;
		}
		for (int i = 0; i < subnodes.size(); i++) {
			List<Integer> subnodesCopy = new LinkedList<Integer>(subnodes);
			LinkedList<Integer> nodesCopy = new LinkedList<Integer>(nodes);
			nodesCopy.add(subnodesCopy.remove(i));
			fullList.addAll(combine(nodesCopy, subnodesCopy));
		}
		if (subnodes.size() == 0) {
			HamiltonPath<T, K> seq = HamiltonPath.fromIds(nodes, nodeMap);
			fullList.add(seq);
			return fullList;
		}
		return fullList;
	}
}
