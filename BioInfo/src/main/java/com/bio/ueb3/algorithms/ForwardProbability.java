package com.bio.ueb3.algorithms;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.ueb3.State;

/**
 * forward probability contains two possible methods to do this:<br>
 * 1. default calucalation<br>
 * 2. calculation with factor<br>
 * The factor can be defined. If it's a default calculation, the factor is 1
 */
public class ForwardProbability extends ChanceCalculator {

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

	/**
	 * Sum over all states with the given calculation: SUM(last chance of state * transition from state)
	 */
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

	/**
	 * calculation: m * chance for dice roll * factor
	 */
	@Override
	public double getNextChanceForState(State state, int eyeNumber, int position) {
		double m = getNextMCalculation(state, position);
		double calculatedChance = state.getChanceForEye(eyeNumber) * m;
		logger.debug("CHANCE TAKEN: " + m + " * " + state.getChanceForEye(eyeNumber) + " = " + calculatedChance);
		return calculatedChance * factor;
	}
}
