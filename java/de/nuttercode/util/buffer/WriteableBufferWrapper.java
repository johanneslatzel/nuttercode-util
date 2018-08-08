package de.nuttercode.util.buffer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class WriteableBufferWrapper implements WriteableBuffer {

	private final WriteableBuffer buffer;

	public WriteableBufferWrapper(WriteableBuffer buffer) {
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
	public void putString(String s) {
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
	public void putBytes(byte[] bytes) {
		buffer.putBytes(bytes);
	}

	@Override
	public void putByteBuffer(ByteBuffer byteBuffer) {
		buffer.putByteBuffer(byteBuffer);
	}

	@Override
	public void putBuffer(ReadableBuffer someBuffer) {
		buffer.putBuffer(someBuffer);
	}

}
