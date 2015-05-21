package com.bio.sequence;

public class SequenceTooShortException extends Exception {

	public SequenceTooShortException(long n){
		super("The value "+n+" is not valid size for a sequence. It must be at least 1.");
	}

}
