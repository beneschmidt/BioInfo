package com.bio.sequence;

import com.bio.utilities.NumberUtils;

/**
 * the alphabet of a sequence
 */
public enum SequenceCharacter {
	A,T,C,G;
	
	public static SequenceCharacter getRandomChar(){
		int randomInt = NumberUtils.randInt(0, 3);
		return SequenceCharacter.values()[randomInt];
	}
	
	public static boolean isCharSequenceCharacter(char c){
		for(SequenceCharacter s : SequenceCharacter.values()){
			if(c==s.name().charAt(0)){
				return true;
			}
		}
		return false;
	}
}
