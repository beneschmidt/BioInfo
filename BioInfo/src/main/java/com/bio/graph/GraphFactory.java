package com.bio.graph;

import java.util.List;

import com.bio.sequence.Sequence;

public class GraphFactory {

	public static DNAGraph createFromSequences(List<String> rawSequences) {
		DNAGraph graph = new DNAGraph();
		long i = 1l;
		for (String nextLine : rawSequences) {
			Sequence sequence = new Sequence(nextLine);
			graph.insertNewSequence(sequence, i);
			i++;
		}
		return graph;
	}
}
