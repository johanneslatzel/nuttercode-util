package de.nuttercode.util.buffer;

import java.net.URI;
import java.nio.ByteBuffer;
import java.time.Instant;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;

/**
 * wrapper for any {@link WritableBuffer}
 * 
 * @author Johannes B. Latzel
 *
 */
public class WritableBufferWrapper implements WritableBuffer {

	private final @NotNull WritableBuffer buffer;

	public WritableBufferWrapper(@NotNull WritableBuffer buffer) {
		Assurance.assureNotNull(buffer);
		this.buffer = buffer;
	}

	public void putInstant(@NotNull Instant instant) {
		Assurance.assureNotNull(instant);
		buffer.putString(instant.toString());
	}

	public void putUri(URI uri) {
		Assurance.assureNotNull(uri);
		buffer.putString(uri.toString());
	}

	@Override
	public void putInt(int i) {
		buffer.putInt(i);
	}

	@Override
	public void putShort(short s) {
		buffer.putShort(s);
	}

	@Override
	public void putLong(long l) {
		buffer.putLong(l);
	}

	@Override
	public void putFloat(float f) {
		buffer.putFloat(f);
	}

	@Override
	public void putDouble(double d) {
		buffer.putDouble(d);
	}

	@Override
	public void putString(@NotNull String s) {
		buffer.putString(s);
	}

	@Override
	public void putChar(char c) {
		buffer.putChar(c);
	}

	@Override
	public void putByte(byte b) {
		buffer.putByte(b);
	}

	@Override
	public void putBytes(@NotNull byte[] bytes) {
		buffer.putBytes(bytes);
	}

	@Override
	public void putByteBuffer(@NotNull ByteBuffer byteBuffer) {
		buffer.putByteBuffer(byteBuffer);
	}

	@Override
	public void putBuffer(@NotNull ReadableBuffer someBuffer) {
		buffer.putBuffer(someBuffer);
	}

}
