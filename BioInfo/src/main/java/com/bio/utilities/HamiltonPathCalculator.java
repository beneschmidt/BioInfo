package com.bio.utilities;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.bio.graph.Edge;
import com.bio.graph.Node;
import com.bio.graph.HamiltonPath;

/**
 * creates full permutations of Nodes
 *
 * @param <T>
 */
public class HamiltonPathCalculator<T extends Node, K extends Edge> {

	private List<T> inputList;

	public HamiltonPathCalculator(List<T> inputList) {
		this.inputList = inputList;
	}

	public Set<HamiltonPath<T, K>> calculateHamiltonPaths() {
		long start = System.currentTimeMillis();
		Set<HamiltonPath<T, K>> finished = combine(new LinkedList<T>(), inputList);
		System.out.println("Time: " + (System.currentTimeMillis() - start));

		return finished;
	}

	private Set<HamiltonPath<T, K>> combine(List<T> alreadyList, List<T> sublist) {
		Set<HamiltonPath<T, K>> fullList = new TreeSet<>();
		if (!new HamiltonPath<T, K>(alreadyList).isValid()) {
			return fullList;
		}
		for (int i = 0; i < sublist.size(); i++) {
			List<T> newL = new LinkedList<T>(sublist);
			LinkedList<T> alreadyList2 = new LinkedList<T>(alreadyList);
			alreadyList2.add(newL.remove(i));
			fullList.addAll(combine(alreadyList2, newL));
		}
		if (sublist.size() == 0) {
			HamiltonPath<T, K> seq = new HamiltonPath<>(alreadyList);
			fullList.add(seq);
		}
		return fullList;
	}
}
