package com.bio.ueb3;

import java.util.LinkedList;
import java.util.List;

import com.bio.common.FileReaderHelper;

/**
 * Initializes all states with their possible chances and their transition possibilities, read from a file
 */
public class StateInitializer {
	private static final double ZERO = 0.0;
	private static final double FAIR_CHANCE = 1.0 / 6.0;
	private static final double ONE_TENTH = 0.1;

	private List<State> states;

	public StateInitializer(String fileName) {
		states = new LinkedList<State>();

		initFromFile(fileName);
	}

	private void initFromFile(String fileName) {
		List<String> rows = FileReaderHelper.readFile(fileName);
		boolean diceInitReady = false;

		for (String row : rows) {
			if (row.length() == 0) {
				// switch from normal dice chances to transitions in file
				diceInitReady = true;
			} else if (!diceInitReady) {
				// state init with its own chances
				//id=alias chances
				//0=F 0.0;0.0;0.0;0.0;0.0;0.0
				int id = Integer.parseInt(row.split("=")[0]);
				String alias = row.split("=")[1].split(" ")[0];
				String chances = row.split(" ")[1];

				List<Double> list = new LinkedList<Double>();
				for (String s : chances.split(";")) {
					list.add(Double.valueOf(s));
				}
				double[] array = new double[list.size()];
				int pos = 0;
				for (Double next : list) {
					array[pos++] = next;
				}
				states.add(new State(id, array, alias));
			} else {
				// transition from one state to another
				// id1 id2;transition chance
				// 0 0;0.0
				String ids = row.split(";")[0];
				double transitionChance = Double.valueOf(row.split(";")[1]);
				int id1 = Integer.valueOf(ids.split(" ")[0]);
				int id2 = Integer.valueOf(ids.split(" ")[1]);
				getStateForId(id1).addTransition(getStateForId(id2), transitionChance);
			}
		}
	}

	/**
	 * @deprecated changes, should be read from file now
	 */
	@Deprecated
	public void initOriginal() {
		State initialState = new InitialState(0, new double[] { ZERO, ZERO, ZERO, ZERO, ZERO, ZERO }, "Q");
		states.add(initialState);
		State fairDice = new Dice(1, new double[] { FAIR_CHANCE, FAIR_CHANCE, FAIR_CHANCE, FAIR_CHANCE, FAIR_CHANCE, FAIR_CHANCE }, "F");
		states.add(fairDice);
		State unfairDice = new Dice(2, new double[] { ONE_TENTH, ONE_TENTH, ONE_TENTH, ONE_TENTH, ONE_TENTH, 0.5 }, "U");
		states.add(unfairDice);

		// initial state can only get to fair or unfair dice state, both with 50% chance
		initialState.addTransitionToSelf(0).addTransition(fairDice, 0.5).addTransition(unfairDice, 0.5);
		// both dice states stay the same with 95% chance and only switch with 5% chance
		fairDice.addTransitionToSelf(0.95).addTransition(unfairDice, 0.05).addTransition(initialState, 0);
		unfairDice.addTransitionToSelf(0.95).addTransition(fairDice, 0.05).addTransition(initialState, 0);
	}

	public List<State> getStates() {
		return states;
	}

	public State getStateForId(int id) {
		for (State s : states) {
			if (s.getId() == id) {
				return s;
			}
		}
		return null;
	}

}
