package com.bio.ueb3;

import java.util.List;

public class ForwardProbability extends ChanceHandler {

	public ForwardProbability(List<State> possibleStates) {
		super(possibleStates);
	}

	@Override
	public double getNextMCalculation(State targetState, int position) {
		double sum = 0;
		for (State state : states) {
			logger.info("From " + state.getId() + " to " + targetState.getId() + ": " + getStateChanceAtPosition(state, position) + " * "
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
		logger.info("CHANCE TAKEN: " + m + " * " + state.getChanceForEye(eyeNumber) + " = " + calculatedChance);
		return calculatedChance;
	}
}
