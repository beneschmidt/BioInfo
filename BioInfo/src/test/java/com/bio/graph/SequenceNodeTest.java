package com.bio.graph;

import static org.junit.Assert.*;

import org.junit.Test;

public class SequenceNodeTest {

	@Test
	public void testGetGrade() {
		SequenceNode node = new SequenceNode();
		node.addEdge(new DirectedEdge());
		node.addEdge(new DirectedEdge());
		
		assertEquals(2, node.getGrade());
	}

}
