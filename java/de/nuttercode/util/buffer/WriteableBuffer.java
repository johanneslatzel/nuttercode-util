package de.nuttercode.util.buffer;

import java.nio.ByteBuffer;

public interface WriteableBuffer {

	void putInt(int i);

	void putShort(short s);

	void putLong(long l);

	void putFloat(float f);

	void putDouble(double d);

	void putString(String s);

	void putChar(char c);

	void putByte(byte b);

	void putBytes(byte[] bytes);

	void putByteBuffer(ByteBuffer byteBuffer);

	void putBuffer(ReadableBuffer someBuffer);

	default WriteableBuffer writeableView() {
		return new WriteableBufferWrapper(this);
	}

}
