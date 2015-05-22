package com.bio.sequence;

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
	public void testCompareWithValidSequenceTrue() throws SequenceIndexTooLowException{
		Sequence a = new Sequence("ATGATG");
		Sequence b = new Sequence("GATG");
		
		assertTrue(a.compare(b, 2));
	}
	
	@Test
	public void testCompareWithValidSequenceNotTrue() throws SequenceIndexTooLowException{
		Sequence a = new Sequence("ATGATG");
		Sequence b = new Sequence("AAAA");
		
		assertFalse(a.compare(b, 2));
	}
	
	@Test
	public void testCompareWithValidSequenceAndLesserSizeOfSequence() throws SequenceIndexTooLowException{
		Sequence a = new Sequence("ATGATG");
		// only one character, should be 4 though
		Sequence b = new Sequence("G");
		
		assertFalse(a.compare(b, 2));
	}
	
	@Test
	public void testCompareWithValidSequenceAndBiggerSizeOfSequence() throws SequenceIndexTooLowException{
		Sequence a = new Sequence("ATGATG");
		// five characters, should be 4 though
		Sequence b = new Sequence("TGATG");
		
		assertFalse(a.compare(b, 2));
	}
	
	@Test (expected=SequenceIndexTooLowException.class)
	public void testCompareWithZeroIndex() throws SequenceIndexTooLowException{
		Sequence a = new Sequence("ATGATG");
		Sequence b = new Sequence("ATGATG");
		
		// exception
		a.compare(b, 0);
	}
	
	@Test (expected=SequenceIndexTooLowException.class)
	public void testCompareWithNegativeIndex() throws SequenceIndexTooLowException{
		Sequence a = new Sequence("ATGATG");
		Sequence b = new Sequence("ATGATG");

		// exception
		a.compare(b, -1);
	}
	
	@Test
	public void testOverlapWithSingleOverlap(){
		Sequence a = new Sequence("ATGATG");
		Sequence b = new Sequence("TGA");
		// overlap should be 2
		int shouldBe = 2;
		
		int actuallyIs = a.overlap(b);
		assertEquals(shouldBe, actuallyIs);
	}
	
	@Test
	public void testOverlapWithMultipleOverlap(){
		Sequence a = new Sequence("ATGTGT");
		Sequence b = new Sequence("TGT");
		// overlap should be 3
		int shouldBe = 3;
		
		int actuallyIs = a.overlap(b);
		assertEquals(shouldBe, actuallyIs);
	}
	

	@Test
	public void testOverlapWithNoOverlap(){
		Sequence a = new Sequence("ATGTGT");
		Sequence b = new Sequence("AAA");
		// overlap should be 0
		int shouldBe = 0;
		
		int actuallyIs = a.overlap(b);
		assertEquals(shouldBe, actuallyIs);
	}
	
	@Test
	public void testOverlapWithFullOverlap(){
		Sequence a = new Sequence("ATGTGT");
		Sequence b = new Sequence("ATGTGT");
		// overlap should be 6
		int shouldBe = 6;
		
		int actuallyIs = a.overlap(b);
		assertEquals(shouldBe, actuallyIs);
	}
	
	@Test
	public void testOverlapWithEmptySequence(){
		Sequence a = new Sequence("ATGTGT");
		Sequence b = new Sequence("");
		int shouldBe = 0;
		
		int actuallyIs = a.overlap(b);
		assertEquals(shouldBe, actuallyIs);
	}

	@Test
	public void testMerge(){
		Sequence a = new Sequence("ATGT");
		Sequence b = new Sequence("GTAAA");
		
		a.merge(b, 2);
		
		assertEquals("ATGTAAA", a.getValue());
	}
	
	@Test
	public void testIsValidWithValidString(){
		Sequence a = new Sequence("ATGCTATCT");
		assertTrue(a.isValid());
	}
	
	@Test
	public void testIsValidWithEmptyString(){
		Sequence a = new Sequence("");
		assertTrue(a.isValid());
	}
	
	@Test
	public void testIsValidWithInvalidString(){
		//B is not allowed
		Sequence a = new Sequence("ABC");
		assertFalse(a.isValid());
	}
}
