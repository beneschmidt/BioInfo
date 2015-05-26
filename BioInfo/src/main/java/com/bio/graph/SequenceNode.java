package com.bio.graph;

import java.util.LinkedList;
import java.util.List;

import com.bio.sequence.Sequence;

public class SequenceNode implements Node {

	private Integer id;
	private List<Edge> edges;
	private List<Edge> incomingEdges;
	private Sequence sequence;

	public SequenceNode(Integer id) {
		edges = new LinkedList<Edge>();
		incomingEdges = new LinkedList<Edge>();
		this.id = id;
	}

	@Override
	public int getGrade() {
		return edges.size();
	}

	public void addEdge(DirectedEdge edge) {
		edges.add(edge);
	}

	public void addIncomingEdge(DirectedEdge edge) {
		incomingEdges.add(edge);
	}

	@Override
	public List<Edge> getEdges() {
		return edges;
	}

	public List<Edge> getIncomingEdges() {
		return incomingEdges;
	}

	public void clearIncomingEdges() {
		incomingEdges = new LinkedList<Edge>();
	}

	public void clearEdges() {
		edges = new LinkedList<Edge>();
	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int overlaps(SequenceNode otherNode) {
		return getSequence().overlap(otherNode.getSequence());
	}

	public Edge getEdgeToNode(Node node) {
		for (Edge edge : edges) {
			if (edge.getSuccessor().equals(node)) {
				return edge;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "SequenceNode [sequence=" + sequence + "]";
	}

}
