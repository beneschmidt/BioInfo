package com.bio.graph;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.utilities.FastHamiltonPathCalculatorById;
import com.bio.utilities.FileWriterHelper;
import com.bio.utilities.HamiltonPathCalculator;

public class GreedySequenceAssembler implements SequenceAssembler<DNAGraph> {
	private static final Logger logger = LogManager.getLogger(GreedySequenceAssembler.class);

	/**
	 * Calculate the hamilton path with the highest weight (all possible paths need to be calculated). Then pick the path with the highest weight and take it into
	 * consideration. In this path the nodes having the highest weighted edge between them are merged into one. This is done as long as there are multiple
	 * edges in the graph (merging nodes shrinks the graph, so it should always end)
	 */
	@Override
	public DNAGraph assembleGraph(DNAGraph g) {
		int i = 0;

		GraphVizCreator creator = new DNAGraphVizCreator(g);

		long start = System.currentTimeMillis();

		while (!g.isCompletelyMerged()) {
			HamiltonPathCalculator<SequenceNode, DirectedEdge> p = new FastHamiltonPathCalculatorById<SequenceNode, DirectedEdge>(g.getNodeMap());
			HamiltonPath<SequenceNode, DirectedEdge> hamiltonPath = p.getMaxHamiltonPath();
			DirectedEdge e = hamiltonPath.getEdges().iterator().next();
			logger.info("merge two nodes: " + e.getPredecessor().getId() + " - " + e.getSuccessor().getId());
			g.mergeNodesOfEdge(e);
			String s = creator.toString();
			FileWriterHelper.writeToFile("graphviz_" + i + ".gv", s);
			i++;
		}

		logger.info("Number of merges: " + i);
		logger.info("Time taken: " + ((System.currentTimeMillis() - start) / 1000) + " sec");
		return g;
	}
}
