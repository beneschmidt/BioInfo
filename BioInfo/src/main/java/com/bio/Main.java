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
import com.bio.graph.SequenceNode;
import com.bio.utilities.FileReaderHelper;
import com.bio.utilities.FileWriterHelper;
import com.bio.utilities.NodePermutation;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		List<String> lines = FileReaderHelper.readFile("resources/frag.dat");
		DNAGraph graph = GraphFactory.createFromSequences(lines);

		GraphVizCreator creator = new DNAGraphVizCreator(graph);
		String s = creator.toString();
		FileWriterHelper.writeToFile("graphviz.gv", s);

		NodePermutation<SequenceNode> p = new NodePermutation<SequenceNode>(graph.getNodes().subList(0, 3));
		List<List<SequenceNode>> permutation = p.combine();
		p.print(permutation);

		SequenceAssembler<DNAGraph> greedy = new GreedySequenceAssembler();
		greedy.assembleGraph(graph);

	}
}
