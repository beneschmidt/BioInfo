package com.bio.ueb2.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.ueb2.sequence.Sequence;

public class DNAGraph implements Graph<SequenceNode, DirectedEdge> {

	private Logger logger = LogManager.getLogger(DNAGraph.class);

	private List<SequenceNode> nodes;
	private SortedSet<DirectedEdge> edges;

	public DNAGraph() {
		nodes = new LinkedList<SequenceNode>();
		edges = new TreeSet<DirectedEdge>();
	}

	@Override
	public List<SequenceNode> getNodes() {
		return nodes;
	}

	@Override
	public SortedSet<DirectedEdge> getEdges() {
		return edges;
	}

	private void addEdge(DirectedEdge edge) {
		edges.add(edge);
	}

	public void addNode(SequenceNode node) {
		nodes.add(node);
	}

	public void insertNewSequence(Sequence sequence, Integer id) {
		SequenceNode newNode = new SequenceNode(id);
		newNode.setSequence(sequence);
		insertNewNode(newNode);
	}

	/**
	 * inserts a new node into the graph by checking all other Nodes. If it
	 * overlaps to another node or the other way around, those nodes are linked
	 * with an edge. This can be done in both directions meaning both the new
	 * sequence is checked to all other nodes and the other way around. The
	 * created Node and all created edges are then added to the graph's lists
	 * 
	 * @param sequence
	 */
	@Override
	public void insertNewNode(SequenceNode newNode) {
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
	 * 
	 * @param firstNode
	 * @param secondNode
	 * @param weight
	 */
	private void insertNewEdge(SequenceNode firstNode, SequenceNode secondNode,
			int weight) {
		DirectedEdge newEdge = new DirectedEdge();
		newEdge.setWeight(weight);
		newEdge.setPredecessor(firstNode);
		newEdge.setSuccessor(secondNode);
		firstNode.addEdge(newEdge);
		secondNode.addIncomingEdge(newEdge);
		this.addEdge(newEdge);
		logger.debug("new Edge: " + newEdge);
	}

	public boolean isCompletelyMerged() {
		return edges.size() == 0;
	}

	/**
	 * merge the nodes of an edge (predecessor and successor) to a single node.
	 * All outgoing and incoming edges have to be remove and the node is
	 * reinserted
	 * 
	 * @param edge
	 */
	public void mergeNodesOfEdge(DirectedEdge edge) {
		SequenceNode node = mergeAndClear(edge);
		logger.debug("Merge " + edge.getPredecessor().getId() + " and "
				+ edge.getSuccessor().getId() + " to "
				+ node.getSequence().getValue() + ", weight: "
				+ edge.getWeight());
		insertNewNode(node);
	}

	/**
	 * merge the nodes of an edge to a single node. All surrounding edges
	 * (incoming and outgoing including the edge between the nodes) are removed
	 * 
	 * @param edge
	 * @return clean new Node without edges
	 */
	public SequenceNode mergeAndClear(DirectedEdge edge) {
		SequenceNode pre = (SequenceNode) edge.getPredecessor();
		nodes.remove(pre);
		SequenceNode suc = (SequenceNode) edge.getSuccessor();
		nodes.remove(suc);
		clearEdges(pre);
		clearEdges(suc);
		pre.getSequence().merge(suc.getSequence(), edge.getWeight());

		return pre;
	}

	/**
	 * clears all incoming and outgoing edges. First the incoming edges are
	 * cleared from the successors and then cleared here, next the outgoing
	 * edges are cleared from the predecessors and then cleared here. Because we
	 * have a double linked list implementation of the edges this is important.
	 * 
	 * @param sequenceNode
	 */
	private void clearEdges(SequenceNode sequenceNode) {
		// remove incoming edges
		for (Edge edge : sequenceNode.getIncomingEdges()) {
			edge.getPredecessor().getEdges().remove(edge);
			this.getEdges().remove(edge);
		}
		sequenceNode.clearIncomingEdges();

		// remove outgoing edges
		for (Edge edge : sequenceNode.getEdges()) {
			SequenceNode node = (SequenceNode) edge.getSuccessor();
			node.getIncomingEdges().remove(edge);
			this.getEdges().remove(edge);
		}
		sequenceNode.clearEdges();
	}

	/**
	 * @return the nodes of graph as a map with the node ids as keys
	 */
	public Map<Integer, SequenceNode> getNodeMap() {
		Map<Integer, SequenceNode> nodeMap = new TreeMap<>();
		for (Node node : nodes) {
			nodeMap.put(Integer.parseInt(node.getId() + ""),
					(SequenceNode) node);
		}
		return nodeMap;
	}
}
