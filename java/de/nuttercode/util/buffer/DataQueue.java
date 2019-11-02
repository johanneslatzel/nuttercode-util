package de.nuttercode.util.buffer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import de.nuttercode.util.ArrayUtil;
import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;
import de.nuttercode.util.assurance.Positive;

/**
 * implementation of a {@link WritableBuffer} and {@link ReadableBuffer} that
 * behaves like a Queue. The capacity of this structure will increase if data
 * needs to be stored, but {@link #free()} yields 0.
 * 
 * @author Johannes B. Latzel
 *
 */
public class DataQueue implements WritableBuffer, ReadableBuffer {

	private final static String CHARSET = "UTF-8";

	private byte[] data;

	/**
	 * current position from which data will be read. always smaller than or equal
	 * to {@link #writePosition}.
	 */
	private int readPosition;

	/**
	 * current position at which new data will be written. always greater than or
	 * equal to {@link #readPosition}.
	 */
	private int writePosition;

	/**
	 * sets the maximum size of the internal buffer used in this data structure. if
	 * maxSize <= 0 then the size is unlimited.
	 */
	private int maxSize;

	/**
	 * DataQueue with initialCapacity of 0
	 */
	public DataQueue() {
		this(0);
	}

	/**
	 * DataQueue with initialCapacity
	 * 
	 * @param initialCapacity
	 */
	public DataQueue(int initialCapacity) {
		data = new byte[initialCapacity];
		maxSize = Integer.MAX_VALUE;
		clear();
	}

	/**
	 * assures that at least additionalCapacity is available in the buffer
	 * 
	 * @param additionalCapacity
	 * @throws IllegalStateException if an overflow has occured or max size has been
	 *                               reached
	 */
	private void assureCapacity(int additionalCapacity) {
		if (free() >= additionalCapacity)
			return;
		int capacity = capacity();
		if (capacity == maxSize)
			throw new IllegalStateException("max size reached");
		int newCapacity = (capacity * 3) / 2 + additionalCapacity;
		if (maxSize > 0 && newCapacity > maxSize)
			newCapacity = maxSize;
		if (newCapacity < 0)
			throw new IllegalStateException("data has overflown");
		byte[] newData = new byte[newCapacity];
		int available = available();
		getBytes(newData, 0, available);
		readPosition = 0;
		writePosition = available;
		data = newData;
	}

	/**
	 * increases {@link #readPosition} by length and sets {@link #readPosition} and
	 * {@link #writePosition} to 0 if both are equal
	 * 
	 * @param length
	 */
	private void increaseRead(int length) {
		readPosition += length;
		if (readPosition < 0) {
			throw new IllegalStateException("too much data");
		}
		if (readPosition == writePosition)
			readPosition = writePosition = 0;
	}

	/**
	 * increases {@link #writePosition} by length
	 * 
	 * @param length
	 */
	private void increaseWrite(int length) {
		writePosition += length;
		if (writePosition < 0) {
			throw new IllegalStateException("too much data");
		}
	}

	/**
	 * @param length
	 * @throws IllegalArgumentException if length is not in [0, transferableData()]
	 */
	private void assureHasEnoughData(int length) {
		Assurance.assureBoundaries(length, 0, available());
	}

	/**
	 * current capacity
	 * 
	 * @return current capacity
	 */
	public int capacity() {
		return data.length;
	}

	/**
	 * number free bytes until reallocation
	 * 
	 * @return number free bytes until reallocation
	 */
	public int free() {
		return capacity() - writePosition;
	}

	@Override
	public int available() {
		return writePosition - readPosition;
	}

	@NotNull
	@Override
	public ByteBuffer transferDataInto(@NotNull ByteBuffer outerBuffer) {
		Assurance.assureNotNull(outerBuffer);
		int dataLength = Math.min(available(), outerBuffer.remaining());
		outerBuffer.put(data, readPosition, dataLength);
		increaseRead(dataLength);
		return outerBuffer;
	}

	@Override
	public void putShort(short s) {
		assureCapacity(Short.BYTES);
		ArrayUtil.putShort(data, s, writePosition);
		increaseWrite(Short.BYTES);
	}

	@Override
	public void putInt(int i) {
		assureCapacity(Integer.BYTES);
		ArrayUtil.putInt(data, i, writePosition);
		increaseWrite(Integer.BYTES);
	}

	@Override
	public void putLong(long l) {
		assureCapacity(Long.BYTES);
		ArrayUtil.putLong(data, l, writePosition);
		increaseWrite(Long.BYTES);
	}

	@Override
	public void putFloat(float f) {
		assureCapacity(Float.BYTES);
		ArrayUtil.putFloat(data, f, writePosition);
		increaseWrite(Float.BYTES);
	}

	@Override
	public void putDouble(double d) {
		assureCapacity(Double.BYTES);
		ArrayUtil.putDouble(data, d, writePosition);
		increaseWrite(Double.BYTES);
	}

	@Override
	public void putString(@NotNull String s) {
		Assurance.assureNotNull(s);
		byte[] bytes;
		try {
			bytes = s.getBytes(CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		putInt(bytes.length);
		putBytes(bytes);
	}

	@Override
	public void putChar(char c) {
		assureCapacity(Character.BYTES);
		ArrayUtil.putChar(data, c, writePosition);
		increaseWrite(Character.BYTES);
	}

	@Override
	public int getInt() {
		assureHasEnoughData(Integer.BYTES);
		int i = ArrayUtil.getInt(data, readPosition);
		increaseRead(Integer.BYTES);
		return i;
	}

	@Override
	public long getLong() {
		assureHasEnoughData(Long.BYTES);
		long l = ArrayUtil.getLong(data, readPosition);
		increaseRead(Long.BYTES);
		return l;
	}

	@Override
	public float getFloat() {
		assureHasEnoughData(Float.BYTES);
		float f = ArrayUtil.getFloat(data, readPosition);
		increaseRead(Float.BYTES);
		return f;
	}

	@Override
	public double getDouble() {
		assureHasEnoughData(Double.BYTES);
		double d = ArrayUtil.getDouble(data, readPosition);
		increaseRead(Double.BYTES);
		return d;
	}

	@NotNull
	@Override
	public String getString() {
		try {
			return new String(getBytes(new byte[getInt()]), CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public char getChar() {
		assureHasEnoughData(Character.BYTES);
		char c = ArrayUtil.getChar(data, readPosition);
		increaseRead(Character.BYTES);
		return c;
	}

	@Override
	public short getShort() {
		assureHasEnoughData(Short.BYTES);
		short s = ArrayUtil.getShort(data, readPosition);
		increaseRead(Short.BYTES);
		return s;
	}

	@Override
	public void putByte(byte b) {
		assureCapacity(Byte.BYTES);
		data[writePosition] = b;
		increaseWrite(Byte.BYTES);
	}

	@Override
	public void putBytes(@NotNull byte[] bytes, int offset, int length) {
		Assurance.assureNotNull(bytes);
		assureCapacity(length);
		ArrayUtil.putBytes(data, bytes, offset, length, writePosition);
		increaseWrite(length);
	}

	@Override
	public byte getByte() {
		assureHasEnoughData(Byte.BYTES);
		byte b = data[readPosition];
		increaseRead(Byte.BYTES);
		return b;
	}

	@NotNull
	@Override
	public byte[] getBytes(@NotNull byte[] bytes, int offset, @Positive int length) {
		Assurance.assureNotNull(bytes);
		assureHasEnoughData(length);
		ArrayUtil.putBytes(bytes, data, readPosition, length, offset);
		increaseRead(length);
		return bytes;
	}

	@Override
	public void putByteBuffer(@NotNull ByteBuffer byteBuffer) {
		Assurance.assureNotNull(byteBuffer);
		int length = byteBuffer.remaining();
		assureCapacity(length);
		byteBuffer.get(data, writePosition, length);
		increaseWrite(length);
	}

	@Override
	public void putBuffer(@NotNull ReadableBuffer someBuffer) {
		Assurance.assureNotNull(someBuffer);
		int length = someBuffer.available();
		assureCapacity(length);
		someBuffer.getBytes(data, writePosition, length);
		increaseWrite(length);
	}

	@Override
	public void clear() {
		readPosition = writePosition = 0;
	}

	@Override
	public String toString() {
		return "DataQueue [readPosition=" + readPosition + ", writePosition=" + writePosition + ", capacity()="
				+ capacity() + ", free()=" + free() + ", available()=" + available() + "]";
	}

	@Override
	public void flushToStream(@NotNull OutputStream outputStream) throws IOException {
		Assurance.assureNotNull(outputStream);
		outputStream.write(data, readPosition, available());
		outputStream.flush();
		clear();
	}

	@Override
	public void fillWithStream(@NotNull InputStream inputStream) throws IOException {
		Assurance.assureNotNull(inputStream);
		int bytesRead;
		int available;
		do {
			if ((available = inputStream.available()) == 0)
				return;
			assureCapacity(available);
			bytesRead = inputStream.read(data, writePosition, data.length - writePosition);
			if (bytesRead > 0)
				increaseWrite(bytesRead);
		} while (bytesRead >= 0);
	}

	@Override
	public void retain(int length) {
		Assurance.assureBoundaries(length, 0, Integer.MAX_VALUE);
		int available = available();
		if (available > length) {
			increaseWrite(length - available);
		}
	}

	/**
	 * sets the maximum size of the internal buffer used in this data structure. if
	 * maxSize <= 0 then the size is unlimited.
	 * 
	 * @param maxSize
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * @return array of all available bytes
	 */
	public byte[] getBytes() {
		byte[] bytes = new byte[available()];
		getBytes(bytes);
		return bytes;
	}

}
