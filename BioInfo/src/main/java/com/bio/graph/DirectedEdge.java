package com.bio.graph;

public class DirectedEdge implements Edge {

	private Node predecessor;
	private Node successor;
	private int weight;

	@Override
	public Node getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Node predecessor) {
		this.predecessor = predecessor;
	}

	@Override
	public Node getSuccessor() {
		return successor;
	}

	public void setSuccessor(Node successor) {
		this.successor = successor;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DirectedEdge [");
		if (predecessor != null)
			builder.append("predecessor=").append(predecessor).append(", ");
		if (successor != null)
			builder.append("successor=").append(successor).append(", ");
		builder.append("weight=").append(weight).append("]");
		return builder.toString();
	}
}
