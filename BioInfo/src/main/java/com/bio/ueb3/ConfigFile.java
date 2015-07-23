package com.bio.ueb3;

import java.util.List;

import com.bio.common.FileReaderHelper;

public class ConfigFile {

	private String numberSequence;
	private String originalDiceSequence;
	private String viterbiDiceSequence;

	public ConfigFile(String fileName) {
		List<String> rows = FileReaderHelper.readFile(fileName);
		System.out.println(rows.get(0));
		numberSequence = new StringBuilder(rows.get(0).split(" ")[1]).toString();
		//		numberSequence = new StringBuilder(rows.get(0).split(" ")[1]).reverse().toString();
		originalDiceSequence = rows.get(1).split(" ")[1];
		viterbiDiceSequence = rows.get(1).split(" ")[1];
	}

	public String getNumberSequence() {
		return numberSequence;
	}

	public void setNumberSequence(String numberSequence) {
		this.numberSequence = numberSequence;
	}

	public String getOriginalDiceSequence() {
		return originalDiceSequence;
	}

	public void setOriginalDiceSequence(String originalDiceSequence) {
		this.originalDiceSequence = originalDiceSequence;
	}

	public String getViterbiDiceSequence() {
		return viterbiDiceSequence;
	}

	public void setViterbiDiceSequence(String viterbiDiceSequence) {
		this.viterbiDiceSequence = viterbiDiceSequence;
	}

}
