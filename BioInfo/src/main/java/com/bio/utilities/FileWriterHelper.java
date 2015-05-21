package com.bio.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriterHelper {

	public static void writeToFile(String fileName, String content) {
		try {
			Files.write(Paths.get("./target/" + fileName), content.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
