package com.bio.ueb3;

public class Ueb3Main {

	public static void main(String[] args) {

		StateInitializer initializer = new StateInitializer();
		StateChances chances = new StateChances(initializer.getInitialState(), initializer.getFairDice(), initializer.getUnfairDice());
		String s = "123";
		for (int i = 0; i < s.length(); i++) {
			int nextValue = Integer.parseInt(s.charAt(i) + "");
			System.out.println(nextValue);
		}
	}
}
