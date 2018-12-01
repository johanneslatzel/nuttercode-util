package de.nuttercode.util.buffer;

import java.net.URI;
import java.nio.ByteBuffer;
import java.time.Instant;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;

/**
 * wrapper for any {@link ReadableBuffer}
 * 
 * @author Johannes B. Latzel
 *
 */
public class ReadableBufferWrapper implements ReadableBuffer {

	private final @NotNull ReadableBuffer buffer;

	public ReadableBufferWrapper(@NotNull ReadableBuffer buffer) {
		Assurance.assureNotNull(buffer);
		this.buffer = buffer;
	}

	public @NotNull Instant getInstant() {
		return Instant.parse(getString());
	}

	public @NotNull URI getUri() {
		return URI.create(getString());
	}

	@Override
	public int getInt() {
		return buffer.getInt();
	}

	@Override
	public long getLong() {
		return buffer.getLong();
	}

	@Override
	public float getFloat() {
		return buffer.getFloat();
	}

	@Override
	public double getDouble() {
		return buffer.getDouble();
	}

	@NotNull
	@Override
	public String getString() {
		return buffer.getString();
	}

	@Override
	public char getChar() {
		return buffer.getChar();
	}

	@Override
	public byte getByte() {
		return buffer.getByte();
	}

	@Override
	public short getShort() {
		return buffer.getShort();
	}

	@NotNull
	@Override
	public byte[] getBytes(@NotNull byte[] bytes, int offset, int length) {
		return buffer.getBytes(bytes, offset, length);
	}

	@Override
	public int transferableData() {
		return buffer.transferableData();
	}

	@NotNull
	@Override
	public ByteBuffer transferDataInto(@NotNull ByteBuffer outerBuffer) {
		return buffer.transferDataInto(outerBuffer);
	}

}
