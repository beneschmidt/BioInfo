package com.bio.ueb3;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class StateChances {

	protected static final Logger logger = LogManager.getLogger(StateChances.class);

	protected Map<Integer, List<Double>> stateLists;
	protected List<State> states;

	public StateChances(List<State> possibleStates) {
		stateLists = new TreeMap<>();
		states = new LinkedList<>();
		for (State state : possibleStates) {
			stateLists.put(state.getId(), new LinkedList<Double>());
			states.add(state);
		}
	}

	public void initStatesWithZeroChanceExceptFirst() {
		// first is defined as initState with 100% chance
		for (int i = 0; i < states.size(); i++) {
			if (i == 0) {
				addNextChanceForState(states.get(i), 1.0);
			} else {
				addNextChanceForState(states.get(i), 0.0);
			}
		}
	}

	public List<Double> getListForState(State state) {
		return stateLists.get(state.getId());
	}

	public void addNextChanceForState(State state, double chance) {
		getListForState(state).add(chance);
	}

	public void addNextChanceForState(int id, double chance) {
		stateLists.get(id).add(chance);
	}

	public double getStateChanceAtPosition(State state, int position) {
		return getListForState(state).get(position);
	}

	public State getStateWithMaxChanceAtPosition(int position) {
		double max = Double.MIN_VALUE;
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

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	/**
	 * MAX(State chance at position + LOG(TransitionChance to the target state)) for all states TO the given target state
	 * @param targetState
	 * @param position
	 * @return max value over all possible transitions
	 */
	public abstract double getMForTargetState(State targetState, int position);

	public abstract double getNextChanceForState(State state, int eyeNumber, int position);

}
