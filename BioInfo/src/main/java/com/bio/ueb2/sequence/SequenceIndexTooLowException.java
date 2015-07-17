package com.bio.ueb2.sequence;

public class SequenceIndexTooLowException extends Exception  {

	public SequenceIndexTooLowException(long n){
		super("The value "+n+" is not valid index for a sequence.");
	}
}
