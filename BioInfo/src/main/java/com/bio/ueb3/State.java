package com.bio.ueb3;

import java.util.Map;
import java.util.TreeMap;

public abstract class State {

	protected int id;
	protected String alias;
	protected Map<Integer, Double> transitions;
	protected double[] chances;

	public State(int id, double[] chances, String alias) {
		if (chances.length != 6) {
			throw new RuntimeException("Dices always have 6 sides");
		}
		this.id = id;
		this.chances = chances;
		this.alias = alias;
		transitions = new TreeMap<Integer, Double>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Integer, Double> getTransitions() {
		return transitions;
	}

	public void setTransitions(Map<Integer, Double> transitions) {
		this.transitions = transitions;
	}

	public double[] getChances() {
		return chances;
	}

	public void setChances(double[] chances) {
		this.chances = chances;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @param toState
	 * @param transition
	 * @return self (fluent interface)
	 */
	public State addTransition(State toState, double transition) {
		transitions.put(toState.getId(), transition);
		return this;
	}

	public State addTransitionToSelf(double transition) {
		return addTransition(this, transition);
	}

	public double getChanceForTransition(State dice) {
		return transitions.get(dice.getId());
	}

	public double getChanceForEye(int eyeNumber) {
		return chances[eyeNumber - 1];
	}

}
