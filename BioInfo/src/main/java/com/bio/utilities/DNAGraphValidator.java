package com.bio.utilities;

import java.util.List;

import com.bio.graph.DNAGraph;
import com.bio.graph.SequenceNode;

public class DNAGraphValidator {

	public static boolean isValidGraphForInputStrings(List<String> input, DNAGraph g) {
		for (String nextString : input) {
			for (SequenceNode nextNode : g.getNodes()) {
				if (!nextNode.getSequence().getValue().contains(nextString)) {
					System.out.println(nextNode.getSequence());
					System.out.println("not included: " + nextString);
					return false;
				}
			}
		}
		return true;
	}
}
