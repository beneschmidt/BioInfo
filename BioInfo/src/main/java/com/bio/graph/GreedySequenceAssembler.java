package com.bio.graph;

import com.bio.utilities.FileWriterHelper;

public class GreedySequenceAssembler implements SequenceAssembler<DNAGraph> {

	@Override
	public DNAGraph assembleGraph(DNAGraph g) {
		int i = 0;

		GraphVizCreator creator = new DNAGraphVizCreator(g);

		while (!g.isCompletelyMerged()) {
			g.mergeNodesOfHighestEdge();
			String s = creator.toString();
			FileWriterHelper.writeToFile("graphviz_" + i + ".gv", s);
			i++;
		}

		return g;
	}

}
