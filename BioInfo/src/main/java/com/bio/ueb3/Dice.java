package com.bio.ueb3;

import java.util.Arrays;

public class Dice extends State {

	/**
	 * @param chances
	 */
	public Dice(int id, double[] chances, String alias) {
		super(id, chances, alias);
	}

	@Override
	public String toString() {
		return "Dice [chances=" + Arrays.toString(chances) + "]";
	}

}
