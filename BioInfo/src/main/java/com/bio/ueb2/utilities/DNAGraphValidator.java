package com.bio.ueb2.utilities;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.ueb2.graph.DNAGraph;
import com.bio.ueb2.graph.SequenceNode;

/**
 * validation class that checks if a dna graph is a valid graph for the given input sequence strings. All strings are checked if they exist anywhere in 
 * the graphs node sequences.
 */
public class DNAGraphValidator {
	
	private static final Logger logger = LogManager.getLogger(DNAGraphValidator.class);

	public static boolean isValidGraphForInputStrings(List<String> input, DNAGraph g) {
		boolean valid = true;
		for (String nextString : input) {
			boolean included = false;
			for (SequenceNode nextNode : g.getNodes()) {
				if (nextNode.getSequence().getValue().contains(nextString)) {
					included=true;
				}
			}
			if(!included){
				logger.warn("String "+nextString+" not included! Graph is not valid!");
				valid = false;
			}
		}
		return valid;
	}
}
