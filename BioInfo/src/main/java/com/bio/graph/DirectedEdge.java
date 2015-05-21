package com.bio.graph;

public class DirectedEdge implements Edge, Comparable<DirectedEdge> {

	private Node predecessor;
	private Node successor;
	private Integer weight;

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
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
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

	/**
	 * natural order: weight descending - sequence predecessor - sequence successor
	 */
	@Override
	public int compareTo(DirectedEdge o) {
		int weightCompare = o.getWeight().compareTo(getWeight());
		if (weightCompare == 0) {
			int sequencePredCompare = ((SequenceNode) getPredecessor()).getSequence().getValue()
					.compareTo(((SequenceNode) o.getPredecessor()).getSequence().getValue());
			if (sequencePredCompare == 0) {
				return ((SequenceNode) getSuccessor()).getSequence().getValue().compareTo(((SequenceNode) o.getSuccessor()).getSequence().getValue());
			} else {
				return sequencePredCompare;
			}
		} else {
			return weightCompare;
		}
	}
}
