package com.bio;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bio.utilities.FileReaderHelper;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		List<String> lines = FileReaderHelper.readFile("resources/frag.dat");
		for(String nextLine: lines){
			logger.info(nextLine);
		}
	}
}
