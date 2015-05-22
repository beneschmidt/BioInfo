package com.bio.graph;

import com.bio.utilities.FileWriterHelper;

public class GreedySequenceAssembler implements SequenceAssembler<DNAGraph> {

	@Override
	public DNAGraph assembleGraph(DNAGraph g) {
		int i = 0;

		while (!g.isCompletelyMerged()) {
			g.mergeNodesOfHighestEdge();
			String s = g.createGraphViz();
			FileWriterHelper.writeToFile("graphviz_" + i + ".gv", s);
			i++;
		}

		return g;
	}

}
