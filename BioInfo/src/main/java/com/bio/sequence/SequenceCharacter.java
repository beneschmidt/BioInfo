package com.bio.sequence;

import com.bio.utilities.NumberUtils;

public enum SequenceCharacter {
	A,T,C,G;
	
	public static SequenceCharacter getRandomChar(){
		int randomInt = NumberUtils.randInt(0, 3);
		return SequenceCharacter.values()[randomInt];
	}
}
