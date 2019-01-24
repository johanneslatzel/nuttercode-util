package de.nuttercode.util.buffer;

import java.nio.ByteBuffer;

import de.nuttercode.util.assurance.NotNull;

/**
 * represents a writeable buffer
 * 
 * @author Johannes B. Latzel
 *
 */
public interface WritableBuffer {

	/**
	 * puts an int into the buffer and forwards its position by
	 * {@link Integer#BYTES}
	 * 
	 * @param i
	 */
	void putInt(int i);

	/**
	 * puts a short into the buffer and forwards its position by {@link Short#BYTES}
	 * 
	 * @param s
	 */
	void putShort(short s);

	/**
	 * puts a long into the buffer and forwards its position by {@link Long#BYTES}
	 * 
	 * @param l
	 */
	void putLong(long l);

	/**
	 * puts a float into the buffer and forwards its position by {@link Float#BYTES}
	 * 
	 * @param f
	 */
	void putFloat(float f);

	/**
	 * puts a double into the buffer and forwards its position by
	 * {@link Double#BYTES}
	 * 
	 * @param d
	 */
	void putDouble(double d);

	/**
	 * puts a string into the buffer by putting the length of the string and its
	 * binary data into the buffer and forwards its position by
	 * {@link Integer#BYTES} + s.length() bytes
	 * 
	 * @param s
	 */
	void putString(@NotNull String s);

	/**
	 * puts a char into the buffer and forwards its position by {@link Char#BYTES}
	 * 
	 * @param c
	 */
	void putChar(char c);

	/**
	 * puts a byte into the buffer and forwards its position by {@link Byte#BYTES}
	 * 
	 * @param b
	 */
	void putByte(byte b);

	/**
	 * puts a byte[] into the buffer by putting the binary data of bytes into the
	 * buffer and forwards its position by bytes.length bytes
	 * 
	 * @param bytes
	 */
	default void putBytes(@NotNull byte[] bytes) {
		putBytes(bytes, 0, bytes.length);
	}

	/**
	 * puts a byte[] into the buffer by putting the binary data starting at offset
	 * until offset + length into the buffer and forwards its position by length
	 * bytes
	 * 
	 * @param bytes
	 */
	void putBytes(byte[] buffer, int offset, int length);

	/**
	 * puts all available bytes (byteBuffer.remaining()) of the byteBuffer into this
	 * buffer and forwards its position by the same amount of bytes
	 * 
	 * @param byteBuffer
	 */
	void putByteBuffer(@NotNull ByteBuffer byteBuffer);

	/**
	 * puts all available bytes (readableBuffer.transferableData()) of the
	 * readableBuffer into this buffer and forwards its position by the same amount
	 * of bytes
	 * 
	 * @param readableBuffer
	 */
	void putBuffer(ReadableBuffer readableBuffer);

	/**
	 * clears the content of this buffer
	 */
	void clear();

	/**
	 * creates a writable view of this buffer
	 * 
	 * @return a writable view of this buffer
	 */
	@NotNull
	default WritableBuffer writableView() {
		return new WritableBufferWrapper(this);
	}

}
