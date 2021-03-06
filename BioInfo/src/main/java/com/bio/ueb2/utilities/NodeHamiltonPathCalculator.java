package com.bio.ueb2.utilities;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.bio.ueb2.graph.Edge;
import com.bio.ueb2.graph.HamiltonPath;
import com.bio.ueb2.graph.Node;

/**
 * INFO: too slow, couldn't finish it once
 *
 * @param <T>
 */
public class NodeHamiltonPathCalculator<T extends Node, K extends Edge> implements HamiltonPathCalculator<T, K> {

	private List<T> inputList;

	public NodeHamiltonPathCalculator(List<T> inputList) {
		this.inputList = inputList;
	}

	public HamiltonPath<T, K> getMaxHamiltonPath() {
		long start = System.currentTimeMillis();
		Set<HamiltonPath<T, K>> finished = combine(new LinkedList<T>(), inputList);
		System.out.println("Time: " + (System.currentTimeMillis() - start));

		if (finished.size() > 0) {
			return finished.iterator().next();
		} else {
			return null;
		}
	}

	/**
	 * stops if the so far build path is not valid to improve runtime
	 * @param alreadyList
	 * @param sublist
	 * @return Set of Hamilton paths
	 */
	private Set<HamiltonPath<T, K>> combine(List<T> alreadyList, List<T> sublist) {
		Set<HamiltonPath<T, K>> fullList = new TreeSet<HamiltonPath<T, K>>();
		boolean currentPathValid = new HamiltonPath<T, K>(alreadyList).isValid();
		if (!currentPathValid) {
			return fullList;
		}
		for (int i = 0; i < sublist.size(); i++) {
			List<T> newL = new LinkedList<T>(sublist);
			LinkedList<T> alreadyList2 = new LinkedList<T>(alreadyList);
			alreadyList2.add(newL.remove(i));
			fullList.addAll(combine(alreadyList2, newL));
		}
		if (sublist.size() == 0) {
			HamiltonPath<T, K> seq = new HamiltonPath<T, K>(alreadyList);
			fullList.add(seq);
		}
		return fullList;
	}
}
