package com.bio.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class HamiltonPath<T extends Node, K extends Edge> implements Comparable<HamiltonPath<T, K>> {

	private List<T> nodes;
	private Set<K> edges;
	private Integer weight;
	private boolean valid;

	/**
	 * checks the list of nodes for the edges between them and checks their valid state and the full edges weight (if valid)
	 * @param nodes
	 */
	public HamiltonPath(List<T> nodes) {
		this.nodes = nodes;
		this.edges = new TreeSet<K>();
		valid = true;
		weight = 0;
		for (int i = 0; i < nodes.size() - 1; i++) {
			T currentNode = nodes.get(i);
			@SuppressWarnings("unchecked")
			K edge = (K) currentNode.getEdgeToNode(nodes.get(i + 1));
			if (edge == null) {
				valid = false;
			} else {
				weight += edge.getWeight();
				edges.add(edge);
			}
		}
	}
	
	public static <T extends Node, K extends Edge> HamiltonPath<T,K> fromIds(List<Integer> nodeIds, Map<Integer, T> nodeMap){
		List<T> nodes = new LinkedList<T>();
		for(Integer nodeId: nodeIds){
			nodes.add(nodeMap.get(nodeId));
		}
		
		return new HamiltonPath<T, K>(nodes);
	}

	public List<T> getNodes() {
		return nodes;
	}

	public void setNodes(List<T> nodes) {
		this.nodes = nodes;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Set<K> getEdges() {
		return edges;
	}

	@Override
	public String toString() {
		return "NodeSequence [nodes=" + nodes + ", weight=" + weight + ", valid=" + valid + "]";
	}

	/**
	 * natural order: valid - descending weight - toString
	 */
	@Override
	public int compareTo(HamiltonPath<T, K> o) {
		if (valid) {
			int compareWeight = o.getWeight().compareTo(getWeight());
			if (compareWeight == 0) {
				return toString().compareTo(o.toString());
			} else {
				return compareWeight;
			}
		} else {
			return toString().compareTo(o.toString());
		}
	}
}
