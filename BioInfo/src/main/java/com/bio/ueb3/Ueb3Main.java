package com.bio.ueb3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.ueb3.algorithms.ChanceCalculator;

public class Ueb3Main {

	private static final Logger logger = LogManager.getLogger(Ueb3Main.class);

	public static void main(String[] args) {

		// init the states
		StateInitializer initializer = new StateInitializer("resources/init.txt");

		// init the state chances including an initial chances to be selected
		AlgorithmMenu menu = new AlgorithmMenu();

		// select the chance calculation algorithm
		ChanceCalculator calculator = menu.selectChanceHandler(initializer.getStates());

		// init the first "column" with zero chances, except the first state, it has 100% chance
		calculator.initStatesWithZeroChanceExceptFirst();

		// read the chances and transitions from a config file
		ConfigFile configFile = new ConfigFile("resources/wuerfel.txt");

		// calculate
		ChanceCalculation calculation = new ChanceCalculation(configFile.getNumberSequence(), calculator);
		String path = calculation.calculate();
		logger.info("\n\nFINAL PATH: " + path);
	}
}
