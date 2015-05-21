package com.bio.sequence;

import com.bio.Alphabet;

public class Sequence {
	
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String generate(int n) throws SequenceTooShortException{
		if(n < 1){
			throw new SequenceTooShortException(n);
		}
		StringBuilder s = new StringBuilder();
		for(int i = 0; i <n; i++){
			s.append(Alphabet.getRandomChar());
		}
		return s.toString();
	}
	
	/**
	 * @param s
	 * @param i
	 * @return
	 * @throws SequenceIndexTooLowException
	 */
	public boolean compare(Sequence s, int i) throws SequenceIndexTooLowException{
		if(i < 1){
			throw new SequenceIndexTooLowException(i);
		}
		if(value.length()-i!=s.getValue().length()){
			return false;
		}
		
		return value.substring(i).equals(s.getValue());
	}
}
