package com.bio.ueb3;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StateChances {

	private Map<Integer, List<Double>> stateLists;

	public StateChances(State... possibleStates) {
		stateLists = new TreeMap<>();
		for (State state : possibleStates) {
			stateLists.put(state.getId(), new LinkedList<Double>());
		}
	}

	public List<Double> getListForState(State state) {
		return stateLists.get(state.getId());
	}

	public void addNextChanceForState(State state, Double chance) {
		getListForState(state).add(chance);
	}
}
