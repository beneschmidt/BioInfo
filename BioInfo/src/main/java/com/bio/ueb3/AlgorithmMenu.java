package com.bio.ueb3;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.bio.common.ConsoleReader;

public class AlgorithmMenu {

	private static final int FACTOR_FB = 4;
	private static final int NORMAL_FB = 3;
	private static final int LOG_VITERBI = 2;
	private static final int NORMAL_VITERBI = 1;

	/** all menu points */
	private static Map<Integer, String> menuPoints;
	static {
		menuPoints = new TreeMap<Integer, String>();
		menuPoints.put(NORMAL_VITERBI, "normal Viterbi");
		menuPoints.put(LOG_VITERBI, "logarithmic Viterbi");
		menuPoints.put(NORMAL_FB, "normal forward probability");
		menuPoints.put(FACTOR_FB, "forward probability with factor");
	}

	public ChanceHandler selectChanceHandler(List<State> possibleStates) {

		ConsoleReader reader = new ConsoleReader();
		boolean valid = false;
		while (!valid) {
			valid = true;
			try {
				int selection = reader.readInteger(this.toString());
				// switch the menu to select the algorithm
				switch (selection) {
					case NORMAL_VITERBI:
						return new NormalViterbiChanceHandler(possibleStates);
					case LOG_VITERBI:
						return new LogViterbiChanceHandler(possibleStates);
					case NORMAL_FB:
						return new ForwardProbability(possibleStates);
					case FACTOR_FB:
						return new ForwardProbabilityWithFactor(possibleStates);
					default:
						// default case resets the valid flag to redo the menu
						System.out.println(selection + " is not a valid selection");
						valid = false;
				}
			} catch (IOException e) {
				System.out.println("something went wrong, please try again");
			}
		}

		// if you get here, something went really really wrong
		System.out.println("We're sorry, but something went terribly wrong");
		throw new RuntimeException("this should not happen");
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("Please select:\n");
		for (Map.Entry<Integer, String> entry : menuPoints.entrySet()) {
			s.append(entry.getKey()).append(") ").append(entry.getValue()).append("\n");
		}
		return s.toString();
	}

}
