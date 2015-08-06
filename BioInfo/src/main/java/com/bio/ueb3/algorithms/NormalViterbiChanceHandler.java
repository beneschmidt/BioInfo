package com.bio.ueb3.algorithms;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.ueb3.State;

/**
 * Handles chances and their calculation with normal double values
 * @author Benne
 *
 */
public class NormalViterbiChanceHandler extends ChanceHandler {

	private static final Logger logger = LogManager.getLogger(NormalViterbiChanceHandler.class);

	public NormalViterbiChanceHandler(List<State> possibleStates) {
		super(possibleStates);
	}

	/**
	 * MAX(State chance at position * (TransitionChance to the target state)) for all states TO the given target state
	 * @param targetState
	 * @param position
	 * @return max value over all possible transitions
	 */
	public double getNextMCalculation(State targetState, int position) {
		double max = Double.NEGATIVE_INFINITY;
		for (State state : states) {
			logger.debug("From " + state.getId() + " to " + targetState.getId() + ": " + getStateChanceAtPosition(state, position) + " * "
					+ state.getChanceForTransition(targetState));
			double nextChance = getStateChanceAtPosition(state, position) * state.getChanceForTransition(targetState);
			if (nextChance > max) {
				max = nextChance;
			}
		}
		return max;
	}

	public double getNextChanceForState(State state, int eyeNumber, int position) {
		double m = getNextMCalculation(state, position);
		double calculatedChance = state.getChanceForEye(eyeNumber) * m;
		logger.debug("CHANCE TAKEN: " + m + " * " + state.getChanceForEye(eyeNumber) + " = " + calculatedChance);
		return calculatedChance;
	}
}
