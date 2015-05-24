package com.bio.graph;

/**
 * GraphViz creator class for directed DNA graphs
 * @author Benne
 *
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
		for (DirectedEdge edge : graph.getEdges()) {
			s.append(((SequenceNode) edge.getPredecessor()).getSequence().getValue()).append(" -> ")
					.append(((SequenceNode) edge.getSuccessor()).getSequence().getValue()).append(" [label=\"").append(edge.getWeight()).append("\"]")
					.append(";").append(NEW_LINE);
		}
		for (SequenceNode node : graph.getNodes()) {
			if (node.getEdges().size() == 0) {
				s.append(node.getSequence().getValue()).append(";").append(NEW_LINE);
			}
		}
		return s.toString();
	}

}
