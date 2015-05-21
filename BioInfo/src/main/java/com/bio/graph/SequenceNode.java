package com.bio.graph;

import java.util.LinkedList;
import java.util.List;

import com.bio.sequence.Sequence;

public class SequenceNode implements Node{

	private List<Edge> edges;
	private List<Edge> incomingEdges;
	private Sequence sequence;
	
	
	public SequenceNode(){
		edges = new LinkedList<Edge>();
		incomingEdges = new LinkedList<Edge>();
	}
	
	@Override
	public int getGrade() {
		return edges.size();
	}
	
	public void addEdge(DirectedEdge edge){
		edges.add(edge);
	}
	
	public void addIncomingEdge(DirectedEdge edge){
		incomingEdges.add(edge);
	}

	@Override
	public List<Edge> getEdges() {
		return edges;
	}
	
	public List<Edge> getIncomingEdges(){
		return incomingEdges;
	}
	
	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public void merge(DirectedEdge edge){
		SequenceNode nextNode = (SequenceNode)edge.getSuccessor();
		this.sequence.merge(nextNode.getSequence(), edge.getWeight());
		// TODO: remove own node from the graph
		clearEdges(this);
		
		// TODO: remove next node from the graph
		clearEdges(nextNode);
		
		// TODO: add merged node to the graph
	}

	
	/**
	 * clears all incoming and outgoing edges. First the incoming edges are cleared from the successors and then cleared here, next the outgoing edges are cleared
	 * from the predecessors and then cleared here. Because we have a double linked list implementation of the edges this is important.
	 * @param sequenceNode
	 */
	private void clearEdges(SequenceNode sequenceNode) {
		// remove incoming edges
		for(Edge edge : sequenceNode.incomingEdges){
			edge.getPredecessor().getEdges().remove(edge);
		}
		incomingEdges = new LinkedList<Edge>();
		
		// remove outgoing edges
		for(Edge edge : sequenceNode.edges){
			SequenceNode node = (SequenceNode)edge.getSuccessor();
			node.getIncomingEdges().remove(edge);
		}
		edges = new LinkedList<Edge>();
	}
}
