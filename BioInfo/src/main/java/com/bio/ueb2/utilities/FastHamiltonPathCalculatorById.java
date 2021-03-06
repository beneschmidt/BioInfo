package com.bio.ueb2.utilities;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.ueb2.graph.Edge;
import com.bio.ueb2.graph.HamiltonPath;
import com.bio.ueb2.graph.Node;

/**
 * INFO: this is the currently fastest implementation we made. It creates hamilton paths with the ids of the node (lesser references on objects).
 * A recursive approach is used, creating the permutations by adding all possible node ids after every valid permutation suffix. This means that for every
 * suffix it is first checked if the current permutation is valid so far. If not it is discarded. Only following edges of a node are taken into consideration 
 * for the next id to add, which reduces iterations.
 *
 * @param <T>
 * @param <K>
 */
public class FastHamiltonPathCalculatorById<T extends Node, K extends Edge> implements HamiltonPathCalculator<T, K> {

	private static final Logger logger = LogManager.getLogger(FastHamiltonPathCalculatorById.class);
	private Map<Integer, T> nodeMap;

	public FastHamiltonPathCalculatorById(Map<Integer, T> nodeMap) {
		this.nodeMap = nodeMap;
	}

	@Override
	public HamiltonPath<T, K> getMaxHamiltonPath() {
		long start = System.currentTimeMillis();
		HamiltonPath<T, K> finished = combine();
		logger.debug("Time: " + (System.currentTimeMillis() - start));

		return finished;
	}

	/**
	 * Recursion start. To keep memory usage low, only the path with the highest weight is saved and returned.
	 * @return highest hamilton path
	 */
	private HamiltonPath<T, K> combine() {
		List<List<Integer>> fullList = new LinkedList<List<Integer>>();
		for (T next : nodeMap.values()) {
			List<Integer> newNodes = new LinkedList<Integer>();
			newNodes.add(next.getId());
			fullList.addAll(combine(newNodes));
		}
		logger.info("Number of paths: " + fullList.size());
		int maxWeight = 0;
		HamiltonPath<T, K> maxPath = null;
		for (List<Integer> list : fullList) {
			HamiltonPath<T, K> seq = HamiltonPath.fromIds(list, nodeMap);
			if (seq.getWeight() > maxWeight) {
				maxPath = seq;
				maxWeight = seq.getWeight();
			}
		}
		return maxPath;
	}

	/**
	 * combine the current node path with the next possible node. This node must not be in the current list and must be a part of the edges of the last
	 * node in the current list. This actually saves a lot of time. If the current node list reached its end, the list added to a list of lists. This list of lists
	 * is created over all possible paths in a recursive way.
	 * 
	 * @param currentNodeIds
	 * @return Set of Hamilton paths
	 */
	private List<List<Integer>> combine(List<Integer> currentNodeIds) {
		List<List<Integer>> fullList = new LinkedList<List<Integer>>();
		if (currentNodeIds.size() == nodeMap.size()) {
			// if end is reached, return full list
			fullList.add(currentNodeIds);
			return fullList;
		}

		// check for following nodes
		Node node = nodeMap.get(Integer.parseInt(currentNodeIds.get(currentNodeIds.size() - 1) + "")); // get last item
		for (Edge e : node.getEdges()) {
			// if it's not in the current path, it may be used
			if (!currentNodeIds.contains(e.getSuccessor().getId())) {
				// add currently found node to a copied list of the current nodes
				List<Integer> currentNodesCopy = new LinkedList<Integer>(currentNodeIds);
				currentNodesCopy.add(e.getSuccessor().getId());
				// recursive call for next node
				fullList.addAll(combine(currentNodesCopy));
			}
		}
		return fullList;
	}
}
