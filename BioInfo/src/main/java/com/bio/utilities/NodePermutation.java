package com.bio.utilities;

import java.util.LinkedList;
import java.util.List;

import com.bio.graph.Node;
import com.bio.graph.NodeSequence;

/**
 * creates full permutations of Nodes
 *
 * @param <T>
 */
public class NodePermutation<T extends Node> {

	private List<T> inputList;

	public NodePermutation(List<T> inputList) {
		this.inputList = inputList;
	}

	public List<NodeSequence<T>> combine() {
		long start = System.currentTimeMillis();
		List<NodeSequence<T>> finished = combine(new LinkedList<T>(), inputList);
		System.out.println("Time: " + (System.currentTimeMillis() - start));

		return finished;
	}

	public List<NodeSequence<T>> combine(List<T> alreadyList, List<T> sublist) {
		List<NodeSequence<T>> fullList = new LinkedList<NodeSequence<T>>();
		if (!new NodeSequence<T>(alreadyList).isValid()) {
			return fullList;
		}
		for (int i = 0; i < sublist.size(); i++) {
			List<T> newL = new LinkedList<T>(sublist);
			LinkedList<T> alreadyList2 = new LinkedList<T>(alreadyList);
			alreadyList2.add(newL.remove(i));
			fullList.addAll(combine(alreadyList2, newL));
		}
		if (sublist.size() == 0) {
			NodeSequence<T> seq = new NodeSequence<T>(alreadyList);
			fullList.add(seq);
		}
		return fullList;
	}

	public void print(List<NodeSequence<T>> finished) {
		for (NodeSequence<T> subList : finished) {
			if (subList.isValid())
				System.out.println(subList.toSequenceString());
		}
	}
}
