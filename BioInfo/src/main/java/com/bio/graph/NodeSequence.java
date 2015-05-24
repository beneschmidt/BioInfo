package com.bio.graph;

import java.util.LinkedList;
import java.util.List;

public class NodeSequence<T extends Node> implements Comparable<NodeSequence<T>> {

	private List<T> nodes;
	private Integer weight;
	private boolean valid;

	public NodeSequence() {
		this.nodes = new LinkedList<>();
	}

	public NodeSequence(List<T> nodes, Integer weight) {
		this.nodes = nodes;
		this.weight = weight;
	}

	public NodeSequence(List<T> nodes) {
		this.nodes = nodes;
		valid = true;
		weight = 0;
		for (int i = 0; i < nodes.size() - 1; i++) {
			T currentNode = nodes.get(i);
			Edge edge = currentNode.getEdgeToNode(nodes.get(i + 1));
			if (edge == null) {
				valid = false;
			} else {
				weight += edge.getWeight();
			}
		}
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

	@Override
	public String toString() {
		return "NodeSequence [nodes=" + nodes + ", weight=" + weight + ", valid=" + valid + "]";
	}

	public String toSequenceString() {
		StringBuilder s = new StringBuilder();
		if (!valid) {
			s.append("INVALID: ");
			for (T t : nodes) {
				s.append(t.getId()).append("->");
			}
		} else {
			s.append("VALID(").append(weight).append("): ");
			for (T t : nodes) {
				s.append(t.getId()).append("->");
			}
		}
		s.delete(s.length() - 2, s.length());
		return s.toString();
	}

	public NodeSequence<T> copy() {
		NodeSequence<T> copy = new NodeSequence<T>(new LinkedList<T>(nodes), weight);
		return copy;
	}

	/**
	 * natural order: valid - weight - toString
	 */
	@Override
	public int compareTo(NodeSequence<T> o) {
		if (valid) {
			int compareWeight = getWeight().compareTo(o.getWeight());
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
