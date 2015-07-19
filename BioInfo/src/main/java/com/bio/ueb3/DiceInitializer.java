package com.bio.ueb3;

public class DiceInitializer {
	private static final double FAIR_CHANCE = 1.0 / 6.0;

	private Dice fairDice;
	private Dice unfairDice;

	public DiceInitializer() {
		fairDice = new Dice(0, new double[] { FAIR_CHANCE, FAIR_CHANCE, FAIR_CHANCE, FAIR_CHANCE, FAIR_CHANCE, FAIR_CHANCE });
		unfairDice = new Dice(1, new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.5 });

		// transition
		fairDice.addTransitionToSelf(0.95).addTransition(unfairDice, 0.05);
		unfairDice.addTransitionToSelf(0.95).addTransition(fairDice, 0.05);
	}

	public Dice getFairDice() {
		return fairDice;
	}

	public void setFairDice(Dice fairDice) {
		this.fairDice = fairDice;
	}

	public Dice getUnfairDice() {
		return unfairDice;
	}

	public void setUnfairDice(Dice unfairDice) {
		this.unfairDice = unfairDice;
	}

}
