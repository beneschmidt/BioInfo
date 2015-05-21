package com.bio;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.graph.DNAGraph;
import com.bio.sequence.Sequence;
import com.bio.utilities.FileReaderHelper;
import com.bio.utilities.FileWriterHelper;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		DNAGraph graph = new DNAGraph();
		List<String> lines = FileReaderHelper.readFile("resources/frag.dat");
		for (String nextLine : lines) {
			Sequence sequence = new Sequence(nextLine);
			graph.insertNewSequence(sequence);
		}
		String s = graph.createGraphViz();
		FileWriterHelper.writeToFile("graphviz.gv", s);

	}
}
