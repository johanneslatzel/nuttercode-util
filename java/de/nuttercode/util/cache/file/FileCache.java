package de.nuttercode.util.cache.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import de.nuttercode.util.cache.WeakCache;

/**
 * caches the content of {@link File files} in a {@link WeakCache}. use
 * {@link #get(File)} to get a {@link FileCacheElement} and
 * {@link FileCacheElement#writeTo(java.io.OutputStream)} to write the content
 * of the {@link File} to an {@link OutputStream}. Note that the content of a
 * {@link File} will be re-read when {@link #get(File)} is called and the
 * {@link File#lastModified()} is more recent than the cached lastModified
 * timestamp.
 * 
 * @author Johannes B. Latzel
 *
 */
public abstract class FileCache<T extends FileCacheElement> {

	/**
	 * content cache
	 */
	private final WeakCache<File, T> weakCache;

	public FileCache() {
		weakCache = new WeakCache<>();
	}

	protected abstract T createFileCacheElement(File file) throws FileNotFoundException, IOException;

	public T get(File file) throws FileNotFoundException, IOException {
		T element = null;
		if (weakCache.contains(file)) {
			element = weakCache.get(file);
			if (element.getLastModified() < file.lastModified())
				element = null;
		}
		if (element == null) {
			element = createFileCacheElement(file);
			weakCache.cache(file, element);
		}
		return element;
	}

}
