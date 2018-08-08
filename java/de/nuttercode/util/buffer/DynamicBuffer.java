package de.nuttercode.util.buffer;

import java.nio.ByteBuffer;

public class DynamicBuffer implements WriteableBuffer, ReadableBuffer, ModeableBuffer {

	private final static int COPY_BUFFER_SIZE = 64;

	private ByteBuffer buffer;
	private final byte[] copyBuffer;
	private BufferMode mode;
	private boolean isModeLocked;
	private boolean isClosed;

	public DynamicBuffer(int initialCapacity, boolean isDirect) {
		if (isDirect)
			buffer = ByteBuffer.allocateDirect(initialCapacity);
		else
			buffer = ByteBuffer.allocate(initialCapacity);
		copyBuffer = new byte[COPY_BUFFER_SIZE];
		mode = BufferMode.Write;
		isModeLocked = false;
		isClosed = false;
	}

	private void assureCapacity(int additionalCapacity) {
		assureNotClosed();
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

	@Override
	public int transferableData() {
		assureMode(BufferMode.Read);
		return buffer.limit() - buffer.position();
	}

	@Override
	public ByteBuffer transferDataInto(ByteBuffer outerBuffer) {
		assureMode(BufferMode.Read);
		if (outerBuffer.remaining() >= buffer.remaining()) {
			return outerBuffer.put(buffer);
		}
		while (outerBuffer.remaining() >= COPY_BUFFER_SIZE) {
			buffer.get(copyBuffer);
			outerBuffer.put(copyBuffer);
		}
		if (buffer.remaining() > 0) {
			byte[] tempBuffer = new byte[buffer.remaining()];
			buffer.get(tempBuffer);
			outerBuffer.put(tempBuffer);
		}
		return outerBuffer;
	}

	@Override
	public void putInt(int i) {
		assureMode(BufferMode.Write);
		assureCapacity(Integer.BYTES);
		buffer.putInt(i);
	}

	@Override
	public void putLong(long l) {
		assureMode(BufferMode.Write);
		assureCapacity(Long.BYTES);
		buffer.putLong(l);
	}

	@Override
	public void putFloat(float f) {
		assureMode(BufferMode.Write);
		assureCapacity(Float.BYTES);
		buffer.putFloat(f);
	}

	@Override
	public void putDouble(double d) {
		assureMode(BufferMode.Write);
		assureCapacity(Double.BYTES);
		buffer.putDouble(d);
	}

	@Override
	public void putString(String s) {
		assureMode(BufferMode.Write);
		byte[] bytes = s.getBytes();
		putInt(bytes.length);
		putBytes(bytes);
	}

	@Override
	public void putChar(char c) {
		assureMode(BufferMode.Write);
		assureCapacity(Character.BYTES);
		buffer.putChar(c);
	}

	@Override
	public int getInt() {
		assureMode(BufferMode.Read);
		return buffer.getInt();
	}

	@Override
	public long getLong() {
		assureMode(BufferMode.Read);
		return buffer.getLong();
	}

	@Override
	public float getFloat() {
		assureMode(BufferMode.Read);
		return buffer.getFloat();
	}

	@Override
	public double getDouble() {
		assureMode(BufferMode.Read);
		return buffer.getDouble();
	}

	@Override
	public String getString() {
		assureMode(BufferMode.Read);
		if (transferableData() < Integer.BYTES)
			throw new IllegalStateException();
		byte[] bytes = new byte[getInt()];
		if (transferableData() < bytes.length)
			throw new IllegalStateException();
		return new String(getBytes(bytes));
	}

	@Override
	public char getChar() {
		assureMode(BufferMode.Read);
		return buffer.getChar();
	}

	@Override
	public short getShort() {
		assureMode(BufferMode.Read);
		return buffer.getShort();
	}

	public void putClear(int bytes) {
		assureMode(BufferMode.Write);
		putBytes(new byte[bytes]);
	}

	@Override
	public void putByte(byte b) {
		assureMode(BufferMode.Write);
		assureCapacity(Byte.BYTES);
		buffer.put(b);
	}

	@Override
	public void putBytes(byte[] bytes) {
		assureMode(BufferMode.Write);
		assureCapacity(bytes.length);
		buffer.put(bytes);
	}

	@Override
	public byte getByte() {
		assureMode(BufferMode.Read);
		return buffer.get();
	}

	@Override
	public byte[] getBytes(byte[] bytes) {
		assureMode(BufferMode.Read);
		buffer.get(bytes);
		return bytes;
	}

	@Override
	public void putByteBuffer(ByteBuffer byteBuffer) {
		assureMode(BufferMode.Write);
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
	public void putShort(short s) {
		assureMode(BufferMode.Write);
		buffer.putShort(s);
	}

	@Override
	public void setMode(BufferMode mode) {
		assureNotClosed();
		if (isModeLocked())
			throw new IllegalStateException();
		switch (mode) {
		case Read:
			assureMode(BufferMode.Write);
			this.mode = mode;
			buffer.flip();
			this.mode = BufferMode.Read;
			break;
		case Write:
			assureMode(BufferMode.Read);
			buffer.clear();
			this.mode = BufferMode.Write;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public BufferMode getMode() {
		assureNotClosed();
		return mode;
	}

	@Override
	public void lockMode() {
		assureNotClosed();
		isModeLocked = true;
	}

	@Override
	public boolean isModeLocked() {
		assureNotClosed();
		return isModeLocked;
	}

	@Override
	public void close() {
		assureNotClosed();
		isClosed = true;
	}

	@Override
	public boolean isClosed() {
		return isClosed;
	}

	@Override
	public void putBuffer(ReadableBuffer someBuffer) {
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
