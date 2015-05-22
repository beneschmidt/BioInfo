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

	/**
	 * inserts a new sequence into the graph by checking all other Nodes. If there are overlaps to another node,
	 * those nodes are linked with an edge. This can be done in both directions meaning both the new sequence is checked to all other nodes and the other way around.
	 * The created Node and all created edges are then added to the graph's lists
	 * @param sequence
	 */
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

	/**
	 * creates a new edge between two Nodes with a given weight
	 * @param firstNode
	 * @param secondNode
	 * @param weight
	 */
	private void insertNewEdge(SequenceNode firstNode, SequenceNode secondNode, int weight) {
		DirectedEdge newEdge = new DirectedEdge();
		newEdge.setWeight(weight);
		newEdge.setPredecessor(firstNode);
		newEdge.setSuccessor(secondNode);
		firstNode.addEdge(newEdge);
		secondNode.addIncomingEdge(newEdge);
		this.addEdge(newEdge);
		logger.debug("new Edge: " + newEdge);
	}

	public String createGraphViz() {
		StringBuilder s = new StringBuilder();
		s.append("digraph G {\n");
		s.append("rankdir = TB;\n");
		for (DirectedEdge edge : getEdges()) {
			s.append(((SequenceNode) edge.getPredecessor()).getSequence().getValue()).append(" -> ")
					.append(((SequenceNode) edge.getSuccessor()).getSequence().getValue()).append(" [label=\"").append(edge.getWeight()).append("\"]")
					.append(";\n");
		}
		for(SequenceNode node : nodes){
			if(node.getEdges().size()==0){
				s.append(node.getSequence().getValue()).append(";\n");
			}
		}
		s.append("}");
		return s.toString();
	}
	
	public boolean isCompletelyMerged(){
		return edges.size()==0;
	}
	
	public void mergeNodesOfHighestEdge(){
		DirectedEdge highestEdge = edges.first();
		String s1 = ((SequenceNode)highestEdge.getPredecessor()).getSequence().getValue();
		String s2 = ((SequenceNode)highestEdge.getSuccessor()).getSequence().getValue();
		Sequence sequence = mergeAndClear(highestEdge);
		logger.info("Merge "+s1+" and "+s2+" to "+sequence.getValue()+", weight: "+highestEdge.getWeight());
		insertNewSequence(sequence);
	}
	
	public Sequence mergeAndClear(DirectedEdge edge) {
		SequenceNode pre = (SequenceNode) edge.getPredecessor();
		nodes.remove(pre);
		SequenceNode suc = (SequenceNode) edge.getSuccessor();
		nodes.remove(suc);
		pre.getSequence().merge(suc.getSequence(), edge.getWeight());
		clearEdges(pre);
		clearEdges(suc);
		
		return pre.getSequence();
	}

	/**
	 * clears all incoming and outgoing edges. First the incoming edges are cleared from the successors and then cleared here, next the outgoing edges are cleared
	 * from the predecessors and then cleared here. Because we have a double linked list implementation of the edges this is important.
	 * @param sequenceNode
	 */
	private void clearEdges(SequenceNode sequenceNode) {
		// remove incoming edges
		for (Edge edge : sequenceNode.getIncomingEdges()) {
			edge.getPredecessor().getEdges().remove(edge);
			this.getEdges().remove(edge);
		}

		// remove outgoing edges
		for (Edge edge : sequenceNode.getEdges()) {
			SequenceNode node = (SequenceNode) edge.getSuccessor();
			node.getIncomingEdges().remove(edge);
			this.getEdges().remove(edge);
		}
	}
}
