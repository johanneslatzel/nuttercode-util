package de.nuttercode.util.buffer;

import java.nio.ByteBuffer;

import de.nuttercode.util.assurance.NotNull;

/**
 * represents a readable buffer
 * 
 * @author Johannes B. Latzel
 *
 */
public interface ReadableBuffer {

	/**
	 * returns the next int in the buffer at the current position and forwards the
	 * position by {@link Integer#BYTES} bytes
	 * 
	 * @return next int
	 */
	int getInt();

	/**
	 * returns the next long in the buffer at the current position and forwards the
	 * position by {@link Long#BYTES} bytes
	 * 
	 * @return next long
	 */
	long getLong();

	/**
	 * returns the next float in the buffer at the current position and forwards the
	 * position by {@link Float#BYTES} bytes
	 * 
	 * @return next float
	 */
	float getFloat();

	/**
	 * returns the next double in the buffer at the current position and forwards
	 * the position by {@link Double#BYTES} bytes
	 * 
	 * @return next double
	 */
	double getDouble();

	/**
	 * returns the next String in the buffer at the current position. first getInt()
	 * is called. the returned determines the size (n) of the following string. the
	 * position will be forwarded by n + {@link Integer#BYTES} bytes.
	 * 
	 * @return next String
	 */
	@NotNull
	String getString();

	/**
	 * returns the next char in the buffer at the current position and forwards the
	 * position by {@link Character#BYTES} bytes
	 * 
	 * @return next char
	 */
	char getChar();

	/**
	 * returns the next byte in the buffer at the current position and forwards the
	 * position by {@link Byte#BYTES} bytes
	 * 
	 * @return next byte
	 */
	byte getByte();

	/**
	 * returns the next short in the buffer at the current position and forwards the
	 * position by {@link Short#BYTES} bytes
	 * 
	 * @return next short
	 */
	short getShort();

	/**
	 * reads the next bytes.length bytes from the buffer and writes them into bytes.
	 * 
	 * @return bytes
	 */
	@NotNull
	byte[] getBytes(@NotNull byte[] bytes);

	/**
	 * @return number of bytes that can be read from the buffer
	 */
	int transferableData();

	/**
	 * @return true if {@link #transferableData()} > 0
	 */
	default boolean hasTransferableData() {
		return transferableData() > 0;
	}

	/**
	 * reads the next {@link ByteBuffer#remaining()} bytes from this buffer and puts
	 * them into outerBuffer.
	 * 
	 * @param outerBuffer
	 * @return outerBuffer
	 */
	@NotNull
	ByteBuffer transferDataInto(@NotNull ByteBuffer outerBuffer);

	/**
	 * @return a readable view of this buffer
	 */
	@NotNull
	default ReadableBuffer readableView() {
		return new ReadableBufferWrapper(this);
	}

}
