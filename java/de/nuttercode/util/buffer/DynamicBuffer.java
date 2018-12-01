package de.nuttercode.util.buffer;

import java.nio.ByteBuffer;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;

/**
 * used when a java.nio.ByteBuffer is needed but the size is unknown. be aware
 * that this buffer will grow as data is put into it. if the size of the buffer
 * is known at compile-time then this class should not be used.
 * 
 * @author Johannes B. Latzel
 *
 */
public class DynamicBuffer implements WritableBuffer, ReadableBuffer {

	/**
	 * default size of the copy buffer in bytes
	 */
	private final static int COPY_BUFFER_SIZE = 64;

	/**
	 * underlying buffer
	 */
	private @NotNull ByteBuffer buffer;

	/**
	 * used to copy data between buffers
	 */
	private final @NotNull byte[] copyBuffer;

	/**
	 * mode of the buffer
	 */
	private @NotNull BufferMode mode;

	/**
	 * true if the mode can not be changed
	 */
	private boolean isModeLocked;

	public DynamicBuffer(int initialCapacity, boolean isDirect) {
		if (isDirect)
			buffer = ByteBuffer.allocateDirect(initialCapacity);
		else
			buffer = ByteBuffer.allocate(initialCapacity);
		copyBuffer = new byte[COPY_BUFFER_SIZE];
		mode = BufferMode.Write;
		isModeLocked = false;
	}

	/**
	 * assures that at least additionalCapacity is available in the buffer
	 * 
	 * @param additionalCapacity
	 */
	private void assureCapacity(int additionalCapacity) {
		if (buffer.remaining() >= additionalCapacity)
			return;
		int newCapacity = (buffer.capacity() * 3) / 2 + additionalCapacity;
		ByteBuffer newBuffer;
		if (buffer.isDirect())
			newBuffer = ByteBuffer.allocateDirect(newCapacity);
		else
			newBuffer = ByteBuffer.allocate(newCapacity);
		buffer.flip();
		newBuffer.put(buffer);
		buffer = newBuffer;
	}

	public void setMode(@NotNull BufferMode mode) {
		Assurance.assureNotNull(mode);
		if (isModeLocked())
			throw new IllegalStateException();
		switch (mode) {
		case Read:
			this.mode = mode;
			buffer.flip();
			this.mode = BufferMode.Read;
			break;
		case Write:
			buffer.clear();
			this.mode = BufferMode.Write;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	public @NotNull BufferMode getMode() {
		return mode;
	}

	public void lockMode() {
		isModeLocked = true;
	}

	public boolean isModeLocked() {
		return isModeLocked;
	}

	@Override
	public int transferableData() {
		Assurance.assureMode(this, BufferMode.Read);
		return buffer.limit() - buffer.position();
	}

	@NotNull
	@Override
	public ByteBuffer transferDataInto(@NotNull ByteBuffer outerBuffer) {
		Assurance.assureNotNull(outerBuffer);
		Assurance.assureMode(this, BufferMode.Read);
		if (outerBuffer.remaining() >= buffer.remaining()) {
			return outerBuffer.put(buffer);
		}
		while (outerBuffer.remaining() >= COPY_BUFFER_SIZE) {
			buffer.get(copyBuffer);
			outerBuffer.put(copyBuffer);
		}
		if (buffer.remaining() > 0) {
			byte[] tempBuffer = new byte[outerBuffer.remaining()];
			buffer.get(tempBuffer);
			outerBuffer.put(tempBuffer);
		}
		return outerBuffer;
	}

	@Override
	public void putShort(short s) {
		Assurance.assureMode(this, BufferMode.Write);
		assureCapacity(Short.BYTES);
		buffer.putShort(s);
	}

	@Override
	public void putInt(int i) {
		Assurance.assureMode(this, BufferMode.Write);
		assureCapacity(Integer.BYTES);
		buffer.putInt(i);
	}

	@Override
	public void putLong(long l) {
		Assurance.assureMode(this, BufferMode.Write);
		assureCapacity(Long.BYTES);
		buffer.putLong(l);
	}

	@Override
	public void putFloat(float f) {
		Assurance.assureMode(this, BufferMode.Write);
		assureCapacity(Float.BYTES);
		buffer.putFloat(f);
	}

	@Override
	public void putDouble(double d) {
		Assurance.assureMode(this, BufferMode.Write);
		assureCapacity(Double.BYTES);
		buffer.putDouble(d);
	}

	@Override
	public void putString(@NotNull String s) {
		Assurance.assureNotNull(s);
		Assurance.assureMode(this, BufferMode.Write);
		byte[] bytes = s.getBytes();
		putInt(bytes.length);
		putBytes(bytes);
	}

	@Override
	public void putChar(char c) {
		Assurance.assureMode(this, BufferMode.Write);
		assureCapacity(Character.BYTES);
		buffer.putChar(c);
	}

	@Override
	public int getInt() {
		Assurance.assureMode(this, BufferMode.Read);
		return buffer.getInt();
	}

	@Override
	public long getLong() {
		Assurance.assureMode(this, BufferMode.Read);
		return buffer.getLong();
	}

	@Override
	public float getFloat() {
		Assurance.assureMode(this, BufferMode.Read);
		return buffer.getFloat();
	}

	@Override
	public double getDouble() {
		Assurance.assureMode(this, BufferMode.Read);
		return buffer.getDouble();
	}

	@NotNull
	@Override
	public String getString() {
		Assurance.assureMode(this, BufferMode.Read);
		if (transferableData() < Integer.BYTES)
			throw new IllegalStateException();
		byte[] bytes = new byte[getInt()];
		if (transferableData() < bytes.length)
			throw new IllegalStateException(
					"transferableData() < bytes.length: " + transferableData() + " < " + bytes.length);
		return new String(getBytes(bytes));
	}

	@Override
	public char getChar() {
		Assurance.assureMode(this, BufferMode.Read);
		return buffer.getChar();
	}

	@Override
	public short getShort() {
		Assurance.assureMode(this, BufferMode.Read);
		return buffer.getShort();
	}

	public void putClear(int bytes) {
		Assurance.assureMode(this, BufferMode.Write);
		putBytes(new byte[bytes]);
	}

	@Override
	public void putByte(byte b) {
		Assurance.assureMode(this, BufferMode.Write);
		assureCapacity(Byte.BYTES);
		buffer.put(b);
	}

	@Override
	public void putBytes(@NotNull byte[] bytes, int offset, int length) {
		Assurance.assureNotNull(bytes);
		Assurance.assureMode(this, BufferMode.Write);
		assureCapacity(length);
		buffer.put(bytes, offset, length);
	}

	@Override
	public byte getByte() {
		Assurance.assureMode(this, BufferMode.Read);
		return buffer.get();
	}

	@NotNull
	@Override
	public byte[] getBytes(@NotNull byte[] bytes, int offset, int length) {
		Assurance.assureNotNull(bytes);
		Assurance.assureMode(this, BufferMode.Read);
		buffer.get(bytes, offset, length);
		return bytes;
	}

	@Override
	public void putByteBuffer(@NotNull ByteBuffer byteBuffer) {
		Assurance.assureNotNull(byteBuffer);
		Assurance.assureMode(this, BufferMode.Write);
		while (byteBuffer.remaining() >= COPY_BUFFER_SIZE) {
			byteBuffer.get(copyBuffer);
			putBytes(copyBuffer);
		}
		if (byteBuffer.remaining() > 0) {
			byte[] tempBuffer = new byte[byteBuffer.remaining()];
			byteBuffer.get(tempBuffer);
			putBytes(tempBuffer);
		}
	}

	@Override
	public void putBuffer(@NotNull ReadableBuffer someBuffer) {
		Assurance.assureNotNull(someBuffer);
		while (someBuffer.transferableData() > copyBuffer.length) {
			someBuffer.getBytes(copyBuffer);
			putBytes(copyBuffer);
		}
		if (someBuffer.transferableData() > 0) {
			byte[] tempBuffer = new byte[someBuffer.transferableData()];
			someBuffer.getBytes(tempBuffer);
			putBytes(tempBuffer);
		}
	}

}
