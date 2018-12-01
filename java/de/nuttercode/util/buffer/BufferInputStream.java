package de.nuttercode.util.buffer;

import java.io.IOException;
import java.io.InputStream;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;

/**
 * 
 * reads data from a {@link ReadableBuffer}
 * 
 * @author Johannes B. Latzel
 *
 */
public class BufferInputStream extends InputStream {

	/**
	 * current buffer
	 */
	private @NotNull ReadableBuffer buffer;

	/**
	 * constructs new stream
	 * 
	 * @param buffer from which data will be read
	 * @throws IllegalArugmentException if {@link #setBuffer(ReadableBuffer)} does
	 */
	public BufferInputStream(@NotNull ReadableBuffer buffer) {
		setBuffer(buffer);
	}

	/**
	 * specifies the buffer from which will be read
	 * 
	 * @param buffer
	 * @throws IllegalArugmentException if buffer is null
	 */
	public void setBuffer(@NotNull ReadableBuffer buffer) {
		Assurance.assureNotNull(buffer);
		this.buffer = buffer;
	}

	@Override
	public int read() throws IOException {
		try {
			return buffer.getByte();
		} catch (RuntimeException e) {
			throw new IOException("can not read the next byte from the buffer", e);
		}
	}

	@Override
	public int read(byte[] buffer, int offset, int length) {
		this.buffer.getBytes(buffer, offset, length);
		return length;
	}

}
