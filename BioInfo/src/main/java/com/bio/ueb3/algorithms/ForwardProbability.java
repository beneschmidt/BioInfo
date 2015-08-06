package com.bio.ueb3.algorithms;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.ueb3.State;

public class ForwardProbability extends ChanceHandler {

	private static final Logger logger = LogManager.getLogger(ForwardProbability.class);

	private int factor;

	private ForwardProbability(List<State> possibleStates, int factor) {
		super(possibleStates);
		this.factor = factor;
	}

	/**
	 * @param possibleStates
	 * @param factor
	 * @return new ForwardProbability with factor
	 */
	public static ForwardProbability withFactor(List<State> possibleStates, int factor) {
		return new ForwardProbability(possibleStates, factor);
	}

	/**
	 * without factor means the factor is 1
	 * @param possibleStates
	 * @return new ForwardProbability
	 */
	public static ForwardProbability withoutFactor(List<State> possibleStates) {
		return new ForwardProbability(possibleStates, 1);
	}

	@Override
	public double getNextMCalculation(State targetState, int position) {
		double sum = 0;
		for (State state : states) {
			logger.debug("From " + state.getId() + " to " + targetState.getId() + ": " + getStateChanceAtPosition(state, position) + " * "
					+ state.getChanceForTransition(targetState));
			double nextChance = getStateChanceAtPosition(state, position) * state.getChanceForTransition(targetState);
			sum += nextChance;
		}
		return sum;
	}

	@Override
	public double getNextChanceForState(State state, int eyeNumber, int position) {
		double m = getNextMCalculation(state, position);
		double calculatedChance = state.getChanceForEye(eyeNumber) * m;
		logger.debug("CHANCE TAKEN: " + m + " * " + state.getChanceForEye(eyeNumber) + " = " + calculatedChance);
		return calculatedChance * factor;
	}
}
