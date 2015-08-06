package com.bio.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {

	public String readLine(String outputText) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(outputText);
		return br.readLine();
	}

	public int readInteger(String outputText) throws IOException {
		String s = readLine(outputText);
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			System.out.println("You have to enter a number...");
			return readInteger(outputText);
		}
	}
}
