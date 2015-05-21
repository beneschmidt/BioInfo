package com.bio.sequence;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bio.sequence.SequenceCharacter;

public class SequenceCharacterTest {
	
	private static final int NUMBER_OF_RANDOM_CHECKS = 100;

	@Test
	public void testGetRandomCharA() {
		boolean found= checkForLetter(SequenceCharacter.A);
		assertTrue(found);
	}
	
	@Test
	public void testGetRandomCharT() {
		boolean found= checkForLetter(SequenceCharacter.T);
		assertTrue(found);
	}
	
	@Test
	public void testGetRandomCharC() {
		boolean found= checkForLetter(SequenceCharacter.C);
		assertTrue(found);
	}
	
	@Test
	public void testGetRandomCharG() {
		boolean found= checkForLetter(SequenceCharacter.G);
		assertTrue(found);
	}

	private boolean checkForLetter(SequenceCharacter letter) {
		for(int i = 0; i < NUMBER_OF_RANDOM_CHECKS; i++){
			SequenceCharacter is = SequenceCharacter.getRandomChar();
			if(is == letter){
				return true;
			}
		}
		return false;
	}

}
