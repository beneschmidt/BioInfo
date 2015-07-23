package com.bio.ueb3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Viterbi {
	private static final Logger logger = LogManager.getLogger(Viterbi.class);

	private String numberSequence;
	private ViterbiChanceHandler chances;

	/**
	 * @param numberSequence
	 * @param chances special handler for Viterbi chance calculations
	 */
	public Viterbi(String numberSequence, ViterbiChanceHandler chances) {
		this.numberSequence = numberSequence;
		this.chances = chances;
	}

	public String getNumberSequence() {
		return numberSequence;
	}

	public void setNumberSequence(String numberSequence) {
		this.numberSequence = numberSequence;
	}

	public ViterbiChanceHandler getChances() {
		return chances;
	}

	public void setChances(ViterbiChanceHandler chances) {
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
