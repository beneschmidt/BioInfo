package com.bio.ueb3;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Dice {

	private Map<Integer, Double> transitions;

	// INITIALIZATION OF DICES 
	public static final Dice UNFAIR_DICE = new Dice(1, new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.5 });

	private double[] chances;

	private Integer id;

	/**
	 * @param chances
	 */
	public Dice(int id, double[] chances) {
		if (chances.length != 6) {
			throw new RuntimeException("Dices always have 6 sides");
		}
		this.id = id;
		this.chances = chances;

		transitions = new TreeMap<Integer, Double>();
	}

	/**
	 * @param toDice
	 * @param transition
	 * @return self (fluent interface)
	 */
	public Dice addTransition(Dice toDice, double transition) {
		transitions.put(toDice.getId(), transition);
		return this;
	}

	public Dice addTransitionToSelf(double transition) {
		return addTransition(this, transition);
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getChanceForEye(int eyeNumber) {
		return chances[eyeNumber - 1];
	}

	public double getChanceForTransition(Dice dice) {
		return transitions.get(dice.getId());
	}

	@Override
	public String toString() {
		return "Dice [chances=" + Arrays.toString(chances) + "]";
	}

}
