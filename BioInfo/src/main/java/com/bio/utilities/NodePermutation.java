package com.bio.utilities;

import java.util.LinkedList;
import java.util.List;

import com.bio.graph.Node;

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

	public List<List<T>> combine() {
		List<List<T>> finished = combine(new LinkedList<T>(), inputList);

		return finished;
	}

	public List<List<T>> combine(List<T> alreadyList, List<T> sublist) {
		List<List<T>> fullList = new LinkedList<List<T>>();
		for (int i = 0; i < sublist.size(); i++) {
			List<T> newL = new LinkedList<T>(sublist);
			LinkedList<T> alreadyList2 = new LinkedList<T>(alreadyList);
			alreadyList2.add(newL.remove(i));
			fullList.addAll(combine(alreadyList2, newL));
		}
		if (sublist.size() == 0) {
			fullList.add(alreadyList);
		}
		return fullList;
	}

	public void print(List<List<T>> finished) {
		for (List<T> subList : finished) {
			String s = "";
			for (T t : subList) {
				s += t.getId();
			}
			System.out.println(s);
		}
	}
}
