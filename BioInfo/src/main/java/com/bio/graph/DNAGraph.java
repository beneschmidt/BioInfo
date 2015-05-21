package com.bio.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.sequence.Sequence;

public class DNAGraph implements Graph<SequenceNode, DirectedEdge> {

	private Logger logger = LogManager.getLogger(DNAGraph.class);

	private List<SequenceNode> nodes;
	private SortedSet<DirectedEdge> edges;

	public DNAGraph() {
		nodes = new LinkedList<SequenceNode>();
		edges = new TreeSet<DirectedEdge>();
	}

	public void addNode(SequenceNode node) {
		nodes.add(node);
	}

	@Override
	public List<SequenceNode> getNodes() {
		return nodes;
	}

	public SortedSet<DirectedEdge> getEdges() {
		return edges;
	}

	public void addEdge(DirectedEdge edge) {
		edges.add(edge);
	}

	public void insertNewSequence(Sequence sequence) {
		SequenceNode newNode = new SequenceNode();
		newNode.setSequence(sequence);

		for (SequenceNode otherNode : nodes) {
			int firstOverlap = newNode.overlaps(otherNode);
			if (firstOverlap > 0) {
				insertNewEdge(newNode, otherNode, firstOverlap);
			}
			int secondOverlap = otherNode.overlaps(newNode);
			if (secondOverlap > 0) {
				insertNewEdge(otherNode, newNode, secondOverlap);
			}
		}
		this.addNode(newNode);

	}

	private void insertNewEdge(SequenceNode firstNode, SequenceNode secondNode, int weight) {
		DirectedEdge newEdge = new DirectedEdge();
		newEdge.setWeight(weight);
		newEdge.setPredecessor(firstNode);
		newEdge.setSuccessor(secondNode);
		firstNode.addEdge(newEdge);
		secondNode.addIncomingEdge(newEdge);
		this.addEdge(newEdge);
		logger.info("new Edge: " + newEdge);
	}

	public String createGraphViz() {
		StringBuilder s = new StringBuilder();
		s.append("digraph G {\n");
		for (DirectedEdge edge : getEdges()) {
			s.append(((SequenceNode) edge.getPredecessor()).getSequence().getValue()).append(" -> ")
					.append(((SequenceNode) edge.getSuccessor()).getSequence().getValue()).append(" [label=\"").append(edge.getWeight()).append("\"]")
					.append(";\n");
		}
		s.append("}");
		return s.toString();
	}
}
