package com.bio;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bio.sequence.Sequence;
import com.bio.sequence.SequenceIndexTooLowException;
import com.bio.sequence.SequenceTooShortException;

public class SequenceTest {

	@Test
	public void testGenerate() throws SequenceTooShortException {
		int n = 12;
		String generated = Sequence.generate(n);
		
		// test
		assertTrue(generated.length()==n);
	}
	
	@Test (expected=SequenceTooShortException.class)
	public void testGenerateWithZeroLetters() throws SequenceTooShortException {
		int n = 0;
		Sequence.generate(n);
	}
	
	@Test (expected=SequenceTooShortException.class)
	public void testGenerateWithNegativeNumber() throws SequenceTooShortException {
		int n = -12;
		Sequence.generate(n);
	}
	
	@Test
	public void compareWithValidSequenceTrue() throws SequenceIndexTooLowException{
		Sequence a = new Sequence();
		a.setValue("ATGATG");
		
		Sequence b = new Sequence();
		b.setValue("GATG");
		
		assertTrue(a.compare(b, 2));
	}
	
	@Test
	public void compareWithValidSequenceNotTrue() throws SequenceIndexTooLowException{
		Sequence a = new Sequence();
		a.setValue("ATGATG");
		
		Sequence b = new Sequence();
		b.setValue("AAAA");
		
		assertFalse(a.compare(b, 2));
	}
	
	@Test
	public void compareWithValidSequenceAndLesserSizeOfSequence() throws SequenceIndexTooLowException{
		Sequence a = new Sequence();
		a.setValue("ATGATG");
		
		Sequence b = new Sequence();
		// only one character, should be 4 though
		b.setValue("G");
		
		assertFalse(a.compare(b, 2));
	}
	
	@Test
	public void compareWithValidSequenceAndBiggerSizeOfSequence() throws SequenceIndexTooLowException{
		Sequence a = new Sequence();
		a.setValue("ATGATG");
		
		Sequence b = new Sequence();
		// five characters, should be 4 though
		b.setValue("TGATG");
		
		assertFalse(a.compare(b, 2));
	}
	
	@Test (expected=SequenceIndexTooLowException.class)
	public void compareWithZeroIndex() throws SequenceIndexTooLowException{
		Sequence a = new Sequence();
		a.setValue("ATGATG");
		
		Sequence b = new Sequence();
		b.setValue("ATGATG");
		
		// exception
		a.compare(b, 0);
	}
	
	@Test (expected=SequenceIndexTooLowException.class)
	public void compareWithNegativeIndex() throws SequenceIndexTooLowException{
		Sequence a = new Sequence();
		a.setValue("ATGATG");
		
		Sequence b = new Sequence();
		b.setValue("ATGATG");

		// exception
		a.compare(b, -1);
	}

}
