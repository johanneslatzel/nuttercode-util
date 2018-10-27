package de.nuttercode.util.buffer;

import java.nio.ByteBuffer;
import java.util.Collection;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;
import de.nuttercode.util.buffer.transformer.ObjectTransformer;

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
	 * puts a byte[] into the buffer by putting bytes.length and the binary data
	 * into the buffer and forwards its position by bytes.length +
	 * {@link Integer#BYTES} bytes
	 * 
	 * @param bytes
	 */
	void putBytes(@NotNull byte[] bytes);

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
	 * creates a writable view of this buffer
	 * 
	 * @return a writable view of this buffer
	 */
	@NotNull
	default WritableBuffer writableView() {
		return new WritableBufferWrapper(this);
	}

	/**
	 * puts the next n elements from the collection and puts them into this buffer.
	 * n is set by a call to {@link Collection#size()} and put into this buffer by
	 * {@link #putInt(int) putInt(collection.size())}.
	 * 
	 * @param transformer
	 * @param collection
	 */
	default <T> void putCollection(@NotNull ObjectTransformer<T> transformer, @NotNull Collection<T> collection) {
		Assurance.assureNotNull(transformer);
		Assurance.assureNotNull(collection);
		putInt(collection.size());
		for (T t : collection)
			transformer.putInto(t, this);
	}

}
