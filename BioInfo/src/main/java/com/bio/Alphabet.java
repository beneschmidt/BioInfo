package com.bio;

import com.bio.utilities.NumberUtils;

public enum Alphabet {
	A,T,C,G;
	
	public static Alphabet getRandomChar(){
		int randomInt = NumberUtils.randInt(0, 3);
		return Alphabet.values()[randomInt];
	}
}
