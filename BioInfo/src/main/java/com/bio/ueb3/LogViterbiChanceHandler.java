package com.bio.ueb3;

import java.util.List;

public class LogViterbiChanceHandler extends ViterbiChanceHandler {

	public LogViterbiChanceHandler(List<State> possibleStates) {
		super(possibleStates);
	}

	/**
	 * MAX(State chance at position + LOG(TransitionChance to the target state)) for all states TO the given target state
	 * @param toState
	 * @param position at which the last state is saved 
	 * @return max value over all possible transitions
	 */
	public double getMaxM(State toState, int position) {
		double max = Double.NEGATIVE_INFINITY;
		for (State fromState : states) {
			logger.info("From " + fromState.getId() + " to " + toState.getId() + ": " + getStateChanceAtPosition(fromState, position) + " + "
					+ fromState.getLogChanceForTransition(toState));
			// naechstes M = zuvorige Chance des aktuellen States + logarithmische Uebergangs-WSK
			double nextChance = getStateChanceAtPosition(fromState, position) + fromState.getLogChanceForTransition(toState);
			if (nextChance > max) {
				max = nextChance;
			}
		}
		return max;
	}

	/**
	 * hier wird die naechste Chance fuer einen bestimmten State berechnet. Dieser berechnet sich aus der Chance des gewuerfelten Auges
	 * und des maximalen M-Werts aller States, aus denen der Wuerfel uebergegangen sein kann (Transition)
	 */
	public double getNextChanceForState(State state, int eyeNumber, int position) {
		double maxM = getMaxM(state, position);
		logger.info(state.getChanceForEye(eyeNumber) + " + " + maxM);

		// naechste Chance = maxM + logarithmische Chance pro fuer Augenwurf
		return state.getLogChanceForEye(eyeNumber) + maxM;
	}
}
