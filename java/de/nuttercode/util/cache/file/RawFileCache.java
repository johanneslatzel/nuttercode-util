package de.nuttercode.util.cache.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * implementation for {@link FileCache} for binary file caching
 * 
 * @author Johannes B. Latzel
 *
 */
public class RawFileCache extends FileCache<RawFileCacheElement> {

	@Override
	protected RawFileCacheElement createFileCacheElement(File file) throws FileNotFoundException, IOException {
		return new RawFileCacheElement(file);
	}

}
