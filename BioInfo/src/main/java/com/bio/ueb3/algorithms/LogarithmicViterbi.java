package com.bio.ueb3.algorithms;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.ueb3.State;

/**
 * Viterbi with logarithmic values
 */
public class LogarithmicViterbi extends ChanceCalculator {

	private static final Logger logger = LogManager.getLogger(LogarithmicViterbi.class);

	public LogarithmicViterbi(List<State> possibleStates) {
		super(possibleStates);
	}

	/**
	 * MAX(State chance at position + LOG(TransitionChance to the target state)) of all states TO the given target state
	 * @param toState
	 * @param position at which the last state is saved 
	 * @return max value over all possible transitions
	 */
	public double getNextMCalculation(State toState, int position) {
		double max = Double.NEGATIVE_INFINITY;
		for (State fromState : states) {
			logger.debug("From " + fromState.getId() + " to " + toState.getId() + ": " + getStateChanceAtPosition(fromState, position) + " + "
					+ fromState.getLogChanceForTransition(toState));
			// next M = last chance of current state + logarithmic transition chance
			double nextChance = getStateChanceAtPosition(fromState, position) + fromState.getLogChanceForTransition(toState);
			if (nextChance > max) {
				max = nextChance;
			}
		}
		return max;
	}

	/**
	 * calculation: max(m) + log chance for dice roll
	 */
	public double getNextChanceForState(State state, int eyeNumber, int position) {
		double maxM = getNextMCalculation(state, position);
		logger.debug(state.getChanceForEye(eyeNumber) + " + " + maxM);

		// naechste Chance = maxM + logarithmische Chance pro fuer Augenwurf
		return state.getLogChanceForEye(eyeNumber) + maxM;
	}
}
