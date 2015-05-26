package com.bio.graph;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.sequence.Sequence;

public class GraphFactory {
	
	private static final Logger logger = LogManager.getLogger(GraphFactory.class);

	public static DNAGraph createFromSequences(List<String> rawSequences) {
		DNAGraph graph = new DNAGraph();
		long i = 1l;
		long errorCount=0;
		for (String nextLine : rawSequences) {
			Sequence sequence = new Sequence(nextLine);
			if (sequence.isValid()) {
				graph.insertNewSequence(sequence, i);
				i++;
			} else {
				errorCount++;
			}
		}
		if(errorCount>0){
			logger.warn(errorCount+" sequences were not valid!");
		}
		return graph;
	}
}
