package com.bio;

import static org.junit.Assert.*;

import org.junit.Test;

public class SequenceTest {

	@Test
	public void testGenerate() {
		int n = 10;
		String generated = new Sequence().generate(n);
		
		// test
		assertTrue(generated.length()==n);
	}
	
	@Test
	public void testGenerateWithZeroLetters() {
		int n = 0;
		String generated = new Sequence().generate(n);
		
		// test
		assertTrue(generated.length()==n);
	}

}
