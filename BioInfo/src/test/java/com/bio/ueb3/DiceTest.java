package com.bio.ueb3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class DiceTest {

	private static final double DELTA = 0.0000000000000001;

	@Test
	public void testDiceChanceForEye() {
		Dice dice = new Dice(0, new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 });
		assertEquals(2.0, dice.getChanceForEye(2), DELTA);
	}

	@Test
	public void testDiceNot6Eyes() {
		for (int i = 0; i < 6; i++) {
			try {
				new Dice(0, new double[i]);
				fail("should not be working with less than 6 eyes! number of eyes: " + i);
			} catch (RuntimeException e) {
				// fine
			}
		}
	}

	@Test
	public void testDiceTransition() {
		Dice dice1 = new Dice(0, new double[6]);
		Dice dice2 = new Dice(1, new double[6]);

		dice1.addTransition(dice2, 1.5);

		assertEquals(1.5, dice1.getChanceForTransition(dice2), DELTA);
	}

	@Test
	public void testDiceTransitionToSelf() {
		Dice dice1 = new Dice(0, new double[6]);

		dice1.addTransitionToSelf(1.5);

		assertEquals(1.5, dice1.getChanceForTransition(dice1), DELTA);
	}

}
