package com.bio.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SequenceNodeTest {

	@Test
	public void testGetGrade() {
		SequenceNode node = new SequenceNode(1);
		node.addEdge(new DirectedEdge());
		node.addEdge(new DirectedEdge());

		assertEquals(2, node.getGrade());
	}

}
