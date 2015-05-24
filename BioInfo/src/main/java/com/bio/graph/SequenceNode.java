package com.bio.graph;

import java.util.LinkedList;
import java.util.List;

import com.bio.sequence.Sequence;

public class SequenceNode implements Node {

	private long id;
	private List<Edge> edges;
	private List<Edge> incomingEdges;
	private Sequence sequence;

	public SequenceNode(long id) {
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

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int overlaps(SequenceNode otherNode) {
		return getSequence().overlap(otherNode.getSequence());
	}

	@Override
	public String toString() {
		return "SequenceNode [sequence=" + sequence + "]";
	}

}
