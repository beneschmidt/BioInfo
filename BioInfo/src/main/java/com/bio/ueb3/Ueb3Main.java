package com.bio.ueb3;

public class Ueb3Main {

	public static void main(String[] args) {

		// init the states
		StateInitializer initializer = new StateInitializer("resources/init.txt");

		// init the state chances including an initial chances to be selected
		AlgorithmMenu menu = new AlgorithmMenu();
		ChanceHandler chances = menu.selectChanceHandler(initializer.getStates());
		chances.initStatesWithZeroChanceExceptFirst();

		ConfigFile configFile = new ConfigFile("resources/wuerfel.txt");

		ChanceCalculation viterbi = new ChanceCalculation(configFile.getNumberSequence(), chances);
		System.out.println("\n\nFINAL PATH: " + viterbi.calculate());
	}
}
