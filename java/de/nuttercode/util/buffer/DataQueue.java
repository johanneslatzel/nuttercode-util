package de.nuttercode.util.buffer;

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
		readPosition = writePosition = 0;
	}

	/**
	 * assures that at least additionalCapacity is available in the buffer
	 * 
	 * @param additionalCapacity
	 */
	private void assureCapacity(int additionalCapacity) {
		if (free() >= additionalCapacity)
			return;
		int newCapacity = (capacity() * 3) / 2 + additionalCapacity;
		if (newCapacity < 0)
			throw new IllegalStateException("too much data");
		byte[] newData = new byte[newCapacity];
		getBytes(newData, 0, writePosition);
		increaseWrite(-readPosition);
		increaseRead(-readPosition);
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
		byte[] bytes = s.getBytes();
		putInt(bytes.length);
		putBytes(bytes);
	}

	@Override
	public void putChar(char c) {
		assureCapacity(Character.BYTES);
		ArrayUtil.putChar(data, c, writePosition);
		increaseWrite(writePosition);
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
		return new String(getBytes(new byte[getInt()]));
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
		ArrayUtil.putBytes(data, bytes, offset, length);
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
		ArrayUtil.getBytes(bytes, data, offset, length);
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

}