package com.bio.ueb3;

public class InitialState extends State {
	static final double zero = 0.0;

	public InitialState() {
		super(0, new double[] { zero, zero, zero, zero, zero, zero }, "Q");
	}
}
