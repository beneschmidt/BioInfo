package com.bio.ueb3;

import java.util.Map;
import java.util.TreeMap;

public abstract class State {

	protected int id;
	protected String alias;
	protected Map<Integer, Double> transitions;
	protected Map<Integer, Double> logTransitions;
	protected double[] chances;
	protected double[] logChances;

	public State(int id, double[] chances, String alias) {
		if (chances.length != 6) {
			throw new RuntimeException("Dices always have 6 sides");
		}
		this.id = id;
		this.chances = chances;
		this.alias = alias;
		transitions = new TreeMap<Integer, Double>();
		logTransitions = new TreeMap<Integer, Double>();

		logChances = new double[chances.length];
		for (int i = 0; i < logChances.length; i++) {
			logChances[i] = Math.log10(chances[i]);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		logTransitions.put(toState.getId(), transition);
		return this;
	}

	public State addTransitionToSelf(double transition) {
		return addTransition(this, transition);
	}

	public double getChanceForTransition(State state) {
		return transitions.get(state.getId());
	}

	public double getLogChanceForTransition(State state) {
		return logTransitions.get(state.getId());
	}

	public double getChanceForEye(int eyeNumber) {
		return chances[eyeNumber - 1];
	}

	public double getLogChanceForEye(int eyeNumber) {
		return logChances[eyeNumber - 1];
	}

}
