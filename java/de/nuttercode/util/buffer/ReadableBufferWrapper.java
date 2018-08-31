package de.nuttercode.util.buffer;

import java.io.IOException;
import java.nio.ByteBuffer;

import de.nuttercode.util.Assurance;

public class ReadableBufferWrapper implements ReadableBuffer {

	private final ReadableBuffer buffer;

	public ReadableBufferWrapper(ReadableBuffer buffer) {
		Assurance.assureNotNull(buffer);
		this.buffer = buffer;
	}

	@Override
	public boolean isClosed() {
		return buffer.isClosed();
	}

	@Override
	public void close() throws IOException {
		buffer.close();
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

	@Override
	public byte[] getBytes(byte[] bytes) {
		return buffer.getBytes(bytes);
	}

	@Override
	public int transferableData() {
		return buffer.transferableData();
	}

	@Override
	public ByteBuffer transferDataInto(ByteBuffer outerBuffer) {
		return buffer.transferDataInto(outerBuffer);
	}

}
