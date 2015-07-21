package com.bio.common;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class FileReaderHelper {

	public static List<String> readFile(String fileName) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(fileName),
					Charset.defaultCharset());
			return lines;
		} catch (Exception e) {
			return new LinkedList<String>();
		}
	}
}
