package com.bio.ueb3;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ViterbiChanceHandler {

	protected static final Logger logger = LogManager.getLogger(ViterbiChanceHandler.class);

	protected Map<Integer, List<Double>> stateLists;
	protected List<State> states;

	public ViterbiChanceHandler(List<State> possibleStates) {
		stateLists = new TreeMap<>();
		states = possibleStates;
		for (State state : possibleStates) {
			stateLists.put(state.getId(), new LinkedList<Double>());
		}
	}

	/**
	 * there should be an initial state that has 100% to start with. This is by definition the first item
	 */
	public void initStatesWithZeroChanceExceptFirst() {
		addNextChanceForState(states.get(0), 1.0);
		// first is defined as initState with 100% chance
		for (int i = 1; i < states.size(); i++) {
			addNextChanceForState(states.get(i), 0.0);
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
		double max = Double.NEGATIVE_INFINITY;
		State maxState = null;
		//		List<State> reverseList = new LinkedList<State>(states);
		//		Collections.copy(reverseList, states);
		//		Collections.reverse(reverseList);
		//		for (State state : reverseList) {
		for (State state : states) {
			double next = getStateChanceAtPosition(state, position);
			logger.info(next + "> " + max + ", " + (next > max));
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
	public abstract double getMaxM(State targetState, int position);

	public abstract double getNextChanceForState(State state, int eyeNumber, int position);

}
