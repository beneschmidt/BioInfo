package com.bio.ueb3;

public class Ueb3Main {

	public static void main(String[] args) {

		// init the states
		StateInitializer initializer = new StateInitializer("resources/init.txt");

		// init the state chances including an initial chances to be selected
		ViterbiChanceHandler chances = new LogViterbiChanceHandler(initializer.getStates());
		chances.initStatesWithZeroChanceExceptFirst();

		ConfigFile configFile = new ConfigFile("resources/wuerfel.txt");

		Viterbi viterbi = new Viterbi(configFile.getNumberSequence(), chances);
		System.out.println(viterbi.calculate());
	}
}
