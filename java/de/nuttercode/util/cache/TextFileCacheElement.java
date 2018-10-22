package de.nuttercode.util.cache;

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
public class TextFileCacheElement {

	/**
	 * {@link File#lastModified()} when cached
	 */
	private final long lastModified;

	/**
	 * content of {@link File} when cached
	 */
	private final String content;

	public TextFileCacheElement(File file, boolean ignoreNewLines, boolean trimLines)
			throws FileNotFoundException, IOException {
		lastModified = file.lastModified();
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

	/**
	 * {@link File#lastModified()} when cached
	 * 
	 * @return {@link File#lastModified()} when cached
	 */
	public long getLastModified() {
		return lastModified;
	}

	/**
	 * content of {@link File} when cached
	 * 
	 * @return content of {@link File} when cached
	 */
	public String getContent() {
		return content;
	}

}
