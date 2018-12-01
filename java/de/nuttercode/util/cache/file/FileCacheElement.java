package de.nuttercode.util.cache.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;

/**
 * an element in a {@link TextFileCache}
 * 
 * @author Johannes B. Latzel
 *
 */
public class FileCacheElement {

	/**
	 * {@link File#lastModified()} when cached
	 */
	private final long lastModified;

	/**
	 * an element in a {@link FileCache}
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws IllegalArgumentException when {@link File#length()} >
	 *                                  {@link Integer#MAX_VALUE}
	 */
	public FileCacheElement(@NotNull File file) throws FileNotFoundException, IOException {
		Assurance.assureNotNull(file);
		lastModified = file.lastModified();
	}

	/**
	 * {@link File#lastModified()} when cached
	 * 
	 * @return {@link File#lastModified()} when cached
	 */
	public long getLastModified() {
		return lastModified;
	}

}
