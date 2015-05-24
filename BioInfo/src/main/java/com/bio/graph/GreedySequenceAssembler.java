package com.bio.graph;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

		while (!g.isCompletelyMerged()) {
			logger.info("merge with current graph size: " + g.getNodes().size());
			HamiltonPathCalculator<SequenceNode, DirectedEdge> p = new HamiltonPathCalculator<>(g.getNodes());
			Set<HamiltonPath<SequenceNode, DirectedEdge>> permutation = p.calculateHamiltonPaths();
			HamiltonPath<SequenceNode, DirectedEdge> firstSeq = permutation.iterator().next();
			g.mergeNodesOfEdge(firstSeq.getEdges().iterator().next());
			String s = creator.toString();
			FileWriterHelper.writeToFile("graphviz_" + i + ".gv", s);
			i++;
		}

		logger.info("Number of merges: " + i);
		return g;
	}
}