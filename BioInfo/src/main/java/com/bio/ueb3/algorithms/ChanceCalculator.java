package com.bio.ueb3.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.ueb3.State;

/**
 * Chance handler that gives the direction of how to work with the different possible chance calculations.
 * For every possible algorithm inherited from here, the steps are the same. The final path taken is always the concatenation of states with
 * the highest chance at a position. 
 */
public abstract class ChanceCalculator {

	private static final Logger logger = LogManager.getLogger(ChanceCalculator.class);

	/** map with all the chances for every possible state. A chance list can be accessed by the ID of the state */
	protected Map<Integer, List<Double>> stateLists;
	protected List<State> states;

	public ChanceCalculator(List<State> possibleStates) {
		stateLists = new TreeMap<>();
		states = possibleStates;
		for (State state : possibleStates) {
			stateLists.put(state.getId(), new LinkedList<Double>());
		}
	}

	/**
	 * there should be an initial state that has 100% chance to start with. This is by definition the first item in the given list
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
			logger.debug(next + "> " + max + ", " + (next > max));
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
	 * Note: this could be something like a normal addition or multiplikation with a transition and the last possible chance.
	 * @param targetState
	 * @param position
	 * @return next possible M value
	 */
	public abstract double getNextMCalculation(State targetState, int position);

	/**
	 * calculate the next chance for a given state and an eye number. For usage of the last state, the current position is also given
	 * @param state
	 * @param eyeNumber
	 * @param position
	 * @return the next chance
	 */
	public abstract double getNextChanceForState(State state, int eyeNumber, int position);

}
