package com.bio.ueb3;

public class NormalStateChances extends StateChances {

	public NormalStateChances(State... possibleStates) {
		super(possibleStates);
	}

	/**
	 * MAX(State chance at position * (TransitionChance to the target state)) for all states TO the given target state
	 * @param targetState
	 * @param position
	 * @return max value over all possible transitions
	 */
	public double getMForTargetState(State targetState, int position) {
		double max = Double.MIN_VALUE;
		for (State state : states) {
			logger.info("From " + state.getId() + " to " + targetState.getId() + ": " + getStateChanceAtPosition(state, position) + " * "
					+ state.getChanceForTransition(targetState));
			double nextChance = getStateChanceAtPosition(state, position) * state.getChanceForTransition(targetState);
			if (nextChance > max) {
				max = nextChance;
			}
		}
		return max;
	}

	public double getNextChanceForState(State state, int eyeNumber, int position) {
		double m = getMForTargetState(state, position);
		double calculatedChance = state.getChanceForEye(eyeNumber) * m;
		logger.info("CHANCE TAKEN: " + m + " * " + state.getChanceForEye(eyeNumber) + " = " + calculatedChance);
		return calculatedChance;
	}
}
