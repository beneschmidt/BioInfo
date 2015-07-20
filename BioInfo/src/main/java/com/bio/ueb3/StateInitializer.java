package com.bio.ueb3;

public class StateInitializer {
	private static final double FAIR_CHANCE = 1.0 / 6.0;

	private final Dice fairDice;
	private final Dice unfairDice;
	private final State initialState;

	public StateInitializer() {
		fairDice = new Dice(0, new double[] { FAIR_CHANCE, FAIR_CHANCE, FAIR_CHANCE, FAIR_CHANCE, FAIR_CHANCE, FAIR_CHANCE });
		unfairDice = new Dice(1, new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.5 });
		initialState = new InitialState();

		// initial state can only get to fair or unfair dice state, both with 50% chance
		initialState.addTransitionToSelf(0).addTransition(fairDice, 0.5).addTransition(unfairDice, 0.5);
		// both dice states stay the same with 95% chance and only switch with 5% chance
		fairDice.addTransitionToSelf(0.95).addTransition(unfairDice, 0.05).addTransition(initialState, 0);
		unfairDice.addTransitionToSelf(0.95).addTransition(fairDice, 0.05).addTransition(initialState, 0);
	}

	public Dice getFairDice() {
		return fairDice;
	}

	public Dice getUnfairDice() {
		return unfairDice;
	}

	public State getInitialState() {
		return initialState;
	}

}
