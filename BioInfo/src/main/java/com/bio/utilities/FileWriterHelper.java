package com.bio.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileWriterHelper {

	private static final Logger logger = LogManager.getLogger(FileWriterHelper.class);

	public static void writeToFile(String fileName, String content) {
		try {
			Path targetPath = Paths.get("./target/");
			if (!Files.exists(targetPath)) {
				Files.createDirectories(targetPath);
			}
			Files.write(Paths.get("./target/" + fileName), content.getBytes());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
