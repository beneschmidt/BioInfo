package com.bio.ueb2.utilities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bio.ueb2.utilities.NumberUtils;

public class NumberUtilsTest {

	@Test
	public void testRandIntInNumberRange() {
		int min = 0;
		int max = 10;
		
		// generate
		int random = NumberUtils.randInt(min, max);
		
		// test
		assertTrue(random >= min && random <= max);
	}
	
	@Test
	public void testRandIntInNumberRangeWithOnlyOnePossibleOutcome() {
		int min = 1;
		int max = 1;
		
		// generate
		int random = NumberUtils.randInt(min, max);
		
		// test
		assertEquals(1, random);
	}


}
