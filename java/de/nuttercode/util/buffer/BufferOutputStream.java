package de.nuttercode.util.buffer;

import java.io.IOException;
import java.io.OutputStream;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;

/**
 * 
 * writes data into a {@link WritableBuffer}
 * 
 * @author Johannes B. Latzel
 *
 */
public class BufferOutputStream extends OutputStream {

	/**
	 * current buffer
	 */
	private @NotNull WritableBuffer buffer;

	/**
	 * constructs new stream
	 * 
	 * @param buffer to which data will be written
	 * @throws IllegalArugmentException if {@link #setBuffer(WritableBuffer)} does
	 */
	public BufferOutputStream(@NotNull WritableBuffer buffer) {
		setBuffer(buffer);
	}

	/**
	 * specifies the buffer to which data will be written
	 * 
	 * @param buffer
	 * @throws IllegalArugmentException if buffer is null
	 */
	public void setBuffer(@NotNull WritableBuffer buffer) {
		Assurance.assureNotNull(buffer);
		this.buffer = buffer;
	}

	@Override
	public void write(int b) throws IOException {
		try {
			buffer.putByte((byte) (b & 0xff));
		} catch (RuntimeException e) {
			throw new IOException("can not write to the buffer", e);
		}
	}

	@Override
	public void write(byte[] buffer, int offset, int length) throws IOException {
		this.buffer.putBytes(buffer, offset, length);
	}

}
