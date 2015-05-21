package com.bio;

public class Sequence {

	public String generate(long n){
		StringBuilder s = new StringBuilder();
		for(int i = 0; i <n; i++){
			s.append(Alphabet.getRandomChar());
		}
		return s.toString();
	}
}
