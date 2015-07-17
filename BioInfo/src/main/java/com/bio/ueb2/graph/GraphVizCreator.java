package com.bio.ueb2.graph;

/**
 * GraphViz creator class for Graphs
 */
public abstract class GraphVizCreator {

	protected String NEW_LINE = "\n";

	public enum GraphType {
		graph, digraph
	}

	/**
	 * @return GraphViz graph type
	 */
	public abstract GraphType getGraphType();

	/**
	 * @return GraphViz content rows
	 */
	public abstract String createGraphContent();

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getGraphType()).append(" G {").append(NEW_LINE);
		s.append(createGraphContent());
		s.append("}");
		return s.toString();
	}
}
