package de.nuttercode.util.cache.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * an element in a {@link TextFileCache}
 * 
 * @author Johannes B. Latzel
 *
 */
public class TextFileCacheElement extends FileCacheElement {

	/**
	 * content of {@link File} when cached
	 */
	private final String content;

	public TextFileCacheElement(File file, boolean ignoreNewLines, boolean trimLines)
			throws FileNotFoundException, IOException {
		super(file);
		StringBuilder builder = new StringBuilder();
		String line;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			while ((line = reader.readLine()) != null) {
				if (trimLines)
					line = line.trim();
				builder.append(line);
				if (!ignoreNewLines)
					builder.append('\n');
			}
		}
		content = builder.toString();
	}

	public String getContent() {
		return content;
	}

}
