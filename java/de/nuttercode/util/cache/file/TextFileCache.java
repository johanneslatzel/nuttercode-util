package de.nuttercode.util.cache.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.nuttercode.util.cache.WeakCache;

/**
 * caches the content of text {@link File files} as {@link String Strings} in a
 * {@link WeakCache}. use {@link #get(File)} to get the content of a
 * {@link File}. note that the content of a {@link File} will be re-read when
 * {@link #get(File)} is called and the {@link File#lastModified()} is never
 * than the cached lastModified timestamp.
 * 
 * @author Johannes B. Latzel
 *
 */
public class TextFileCache extends FileCache<TextFileCacheElement> {

	private boolean ignoreNewLines;
	private boolean trimLines;

	public TextFileCache() {
		super();
		setIgnoreNewLines(false);
		setTrimLines(false);
	}

	public boolean isIgnoreNewLines() {
		return ignoreNewLines;
	}

	public boolean isTrimLines() {
		return trimLines;
	}

	public void setIgnoreNewLines(boolean ignoreNewLines) {
		this.ignoreNewLines = ignoreNewLines;
	}

	public void setTrimLines(boolean trimLines) {
		this.trimLines = trimLines;
	}

	@Override
	protected TextFileCacheElement createFileCacheElement(File file) throws FileNotFoundException, IOException {
		return new TextFileCacheElement(file, ignoreNewLines, trimLines);
	}

}
