package com.bio;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlphabetTest {
	
	private static final int NUMBER_OF_RANDOM_CHECKS = 100;

	@Test
	public void testGetRandomCharA() {
		boolean found= checkForLetter(Alphabet.A);
		assertTrue(found);
	}
	
	@Test
	public void testGetRandomCharT() {
		boolean found= checkForLetter(Alphabet.T);
		assertTrue(found);
	}
	
	@Test
	public void testGetRandomCharC() {
		boolean found= checkForLetter(Alphabet.C);
		assertTrue(found);
	}
	
	@Test
	public void testGetRandomCharG() {
		boolean found= checkForLetter(Alphabet.G);
		assertTrue(found);
	}

	private boolean checkForLetter(Alphabet letter) {
		for(int i = 0; i < NUMBER_OF_RANDOM_CHECKS; i++){
			Alphabet is = Alphabet.getRandomChar();
			if(is == letter){
				return true;
			}
		}
		return false;
	}

}
