package com.bio.ueb3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ueb3Main {
	private static final Logger logger = LogManager.getLogger(Ueb3Main.class);

	public static void main(String[] args) {

		StateInitializer initializer = new StateInitializer();
		StateChances chances = new StateChances(initializer.getInitialState(), initializer.getFairDice(), initializer.getUnfairDice());
		chances.addNextChanceForState(initializer.getInitialState(), 1.0);
		chances.addNextChanceForState(initializer.getFairDice(), 0.0);
		chances.addNextChanceForState(initializer.getUnfairDice(), 0.0);

		StringBuilder path = new StringBuilder();
		String s = "216";
		for (int i = 0; i < s.length(); i++) {
			int nextValue = Integer.parseInt(s.charAt(i) + "");
			System.out.println("[NEXT VALUE] " + nextValue);

			for (State state : chances.getStates()) {
				double nextChance = chances.getNextChanceForState(state, nextValue, i);
				chances.addNextChanceForState(state, nextChance);
				logger.info("State Chances of " + state.getId() + ": " + chances.getListForState(state));
			}

			path.append(chances.getStateWithMaxChanceAtPosition(i + 1).getAlias());
		}
		logger.info(path.toString());

	}
}
