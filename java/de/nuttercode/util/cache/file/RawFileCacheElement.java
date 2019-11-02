package de.nuttercode.util.cache.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * implementation of {@link FileCacheElement} for binary file caching
 * 
 * @author Johannes B. Latzel
 *
 */
public class RawFileCacheElement extends FileCacheElement {

	/**
	 * data of cached file
	 */
	private final byte[] content;

	public RawFileCacheElement(File file) throws FileNotFoundException, IOException {
		super(file);
		long length = file.length();
		int current;
		int pos = 0;
		if (length > Integer.MAX_VALUE)
			throw new IOException("file length " + length + " too big");
		content = new byte[(int) length];
		try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file))) {
			while ((current = bin.read(content, pos, content.length - pos)) > 0) {
				pos += current;
			}
		}
		if (pos != content.length)
			throw new IOException("file not read completly");
	}

	/**
	 * writes the data of this element to the {@link OutputStream}
	 * 
	 * @param out
	 * @throws IOException
	 */
	public void writeTo(OutputStream out) throws IOException {
		out.write(content);
	}

	/**
	 * @return copy of the backing byte[] of the content of the file
	 */
	public byte[] getContent() {
		return Arrays.copyOf(content, content.length);
	}

}
