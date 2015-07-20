package com.bio.ueb3;

import java.util.Arrays;

public class Dice extends State {

	// INITIALIZATION OF DICES 
	public static final Dice UNFAIR_DICE = new Dice(1, new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.5 });

	/**
	 * @param chances
	 */
	public Dice(int id, double[] chances) {
		super(id, chances);
		if (chances.length != 6) {
			throw new RuntimeException("Dices always have 6 sides");
		}

	}

	public double getChanceForEye(int eyeNumber) {
		return chances[eyeNumber - 1];
	}

	@Override
	public String toString() {
		return "Dice [chances=" + Arrays.toString(chances) + "]";
	}

}
