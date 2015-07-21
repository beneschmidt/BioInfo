package com.bio.ueb3;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateChances {

	private static final Logger logger = LogManager.getLogger(StateChances.class);

	private Map<Integer, List<Double>> stateLists;
	private List<State> states;

	public StateChances(State... possibleStates) {
		stateLists = new TreeMap<>();
		states = new LinkedList<>();
		for (State state : possibleStates) {
			stateLists.put(state.getId(), new LinkedList<Double>());
			states.add(state);
		}
	}

	public List<Double> getListForState(State state) {
		return stateLists.get(state.getId());
	}

	public void addNextChanceForState(State state, Double chance) {
		getListForState(state).add(chance);
	}

	public double getChanceForTransition(State from, State to) {
		return from.getChanceForTransition(to);
	}

	public double getStateChanceAtPosition(State state, int position) {
		return getListForState(state).get(position);
	}

	public State getStateWithMaxChanceAtPosition(int position) {
		double max = 0.0;
		State maxState = null;
		for (State state : states) {
			double next = getStateChanceAtPosition(state, position);
			if (next > max) {
				max = next;
				maxState = state;
			}
		}
		return maxState;
	}

	/**
	 * MAX(State chance at position * TransitionChance to the target state) for all states TO the given target state
	 * @param targetState
	 * @param position
	 * @return max value over all possible transitions
	 */
	public double getMForTargetState(State targetState, int position) {
		double max = 0;
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
		return state.getChanceForEye(eyeNumber) * getMForTargetState(state, position);
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
}
