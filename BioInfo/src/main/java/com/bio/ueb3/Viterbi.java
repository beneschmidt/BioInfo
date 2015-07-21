package com.bio.ueb3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Viterbi {
	private static final Logger logger = LogManager.getLogger(Viterbi.class);

	private String numberSequence;
	private StateChances chances;

	public Viterbi(String numberSequence, StateChances chances) {
		this.numberSequence = numberSequence;
		this.chances = chances;
	}

	public String getNumberSequence() {
		return numberSequence;
	}

	public void setNumberSequence(String numberSequence) {
		this.numberSequence = numberSequence;
	}

	public StateChances getChances() {
		return chances;
	}

	public void setChances(StateChances chances) {
		this.chances = chances;
	}

	public String calculate() {
		StringBuilder path = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int nextValue = Integer.parseInt(numberSequence.charAt(i) + "");
			logger.info("[NEXT VALUE] " + nextValue);

			// check the chances for every state and update their state list
			for (State state : chances.getStates()) {
				double nextChance = chances.getNextChanceForState(state, nextValue, i);
				chances.addNextChanceForState(state, nextChance);
				logger.info("State Chances of " + state.getId() + ": " + chances.getListForState(state));
			}

			path.append(chances.getStateWithMaxChanceAtPosition(i + 1).getAlias());
		}
		return path.toString();
	}
}
