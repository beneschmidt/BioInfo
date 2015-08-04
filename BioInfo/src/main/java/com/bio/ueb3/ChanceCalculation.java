package com.bio.ueb3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * generic chance calculation for algorithmus that go from first char to the last (forward). It's pretty much straight forward in these case.
 * You have a sequence of numbers and a  ChanceHandler that gives the correct calculation at every point of the sequence. The calculation that is done here
 * is always the same: iterate through the sequence and calculate all the possible chances to "be at in the current state" for every given state. This calculation
 * is done by the given ChanceHandler, so you just have to call it. After calculation at a point in the sequence, the highest chance of all chances at the current position
 * is taken and added to the current path.
 *
 */
public class ChanceCalculation {
	private static final Logger logger = LogManager.getLogger(ChanceCalculation.class);

	private ChanceHandler chances;
	private String numberSequence;

	/**
	 * @param numberSequence
	 * @param chances special handler for Viterbi chance calculations
	 */
	public ChanceCalculation(String numberSequence, ChanceHandler chances) {
		this.numberSequence = numberSequence;
		this.chances = chances;
	}

	public ChanceHandler getChances() {
		return chances;
	}

	public void setChances(ChanceHandler chances) {
		this.chances = chances;
	}

	/**
	 * calculate all chances of all states to occur at every possible position
	 * @return calculated path with max chances at every position
	 */
	public String calculate() {
		StringBuilder path = new StringBuilder();
		for (int i = 0; i < numberSequence.length(); i++) {
			int nextValue = Integer.parseInt(numberSequence.charAt(i) + "");
			logger.info("[NEXT VALUE] " + nextValue);

			// check the chances for every state and update their state list
			for (State state : chances.getStates()) {
				double nextChance = chances.getNextChanceForState(state, nextValue, i);
				chances.addNextChanceForState(state, nextChance);
			}

			logger.info("==================");
			// append the max chance of the current position to the path
			path.append(chances.getStateWithMaxChanceAtPosition(i + 1).getAlias());
		}

		for (State state : chances.getStates()) {
			logger.info("State Chances of " + state.getAlias() + ": " + chances.getListForState(state));
		}
		return path.toString();
	}
}