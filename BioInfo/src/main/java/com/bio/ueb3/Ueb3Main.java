package com.bio.ueb3;

public class Ueb3Main {

	public static void main(String[] args) {

		// init the states
		StateInitializer initializer = new StateInitializer();

		// init the state chances including an initial chances to be selected
		StateChances chances = new LogStateChances(initializer.getInitialState(), initializer.getFairDice(), initializer.getUnfairDice());
		chances.addNextChanceForState(initializer.getInitialState(), 1.0);
		chances.addNextChanceForState(initializer.getFairDice(), 0.0);
		chances.addNextChanceForState(initializer.getUnfairDice(), 0.0);

		ConfigFile configFile = new ConfigFile("resources/wuerfel.txt");

		Viterbi viterbi = new Viterbi(configFile.getNumberSequence(), chances);
		System.out.println(viterbi.calculate());
	}
}
