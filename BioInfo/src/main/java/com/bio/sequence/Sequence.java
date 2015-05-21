package com.bio.sequence;


public class Sequence {
	
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 
	 * @param n
	 * @return a random combination of SequenceCharacter
	 * @throws SequenceTooShortException
	 */
	public static String generate(int n) throws SequenceTooShortException{
		if(n < 1){
			throw new SequenceTooShortException(n);
		}
		StringBuilder s = new StringBuilder();
		for(int i = 0; i <n; i++){
			s.append(SequenceCharacter.getRandomChar());
		}
		return s.toString();
	}
	
	/**
	 * @param s sequence to compare to
	 * @param i index
	 * @return true if the own sequence suffix equals the full other sequence starting from the given index, false otherwise
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
