package com.bio;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.graph.DNAGraph;
import com.bio.graph.DNAGraphVizCreator;
import com.bio.graph.GraphFactory;
import com.bio.graph.GraphVizCreator;
import com.bio.graph.GreedySequenceAssembler;
import com.bio.graph.SequenceAssembler;
import com.bio.sequence.Sequence;
import com.bio.utilities.DNAGraphValidator;
import com.bio.utilities.FileReaderHelper;
import com.bio.utilities.FileWriterHelper;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		List<String> lines = FileReaderHelper.readFile("resources/frag.dat");
		DNAGraph graph = GraphFactory.createFromSequences(lines.subList(0, 16));
		String s2 = "";
		for (String n : lines) {
			s2 += n;
		}

		logger.info("Overall size: " + s2.length());

		GraphVizCreator creator = new DNAGraphVizCreator(graph);
		String s = creator.toString();
		FileWriterHelper.writeToFile("graphviz.gv", s);

		logger.info("Assemble graph to create the sequence...");
		SequenceAssembler<DNAGraph> greedy = new GreedySequenceAssembler();
		greedy.assembleGraph(graph);
		logger.info("graph assembled, here's the finished sequence");
		Sequence sequence = graph.getNodes().iterator().next().getSequence();
		logger.info(sequence.getValue().length() + ": " + sequence.getValue());

		boolean isValid = DNAGraphValidator.isValidGraphForInputStrings(lines, graph);
		logger.info("Is valid:" + isValid);
	}

}
