package com.bio.ueb2.graph;

/**
 * GraphViz creator class for directed DNA graphs
 */
public class DNAGraphVizCreator extends GraphVizCreator {

	private DNAGraph graph;

	public DNAGraphVizCreator(DNAGraph graph) {
		this.graph = graph;
	}

	@Override
	public GraphType getGraphType() {
		return GraphType.digraph;
	}

	@Override
	public String createGraphContent() {
		StringBuilder s = new StringBuilder();

		s.append("rankdir = TB;").append(NEW_LINE);
		for (SequenceNode node : graph.getNodes()) {
			s.append(node.getId()).append(" [label=\"").append(node.getId()).append(": ").append(node.getSequence().getValue()).append("\"];").append(NEW_LINE);
		}
		for (DirectedEdge edge : graph.getEdges()) {
			s.append(edge.getPredecessor().getId()).append(" -> ").append(edge.getSuccessor().getId()).append(" [label=\"").append(edge.getWeight())
					.append("\"]").append(";").append(NEW_LINE);
		}
		return s.toString();
	}

}
