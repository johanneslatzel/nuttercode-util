package de.nuttercode.util.buffer;

import java.nio.ByteBuffer;

import de.nuttercode.util.Closeable;

public interface WriteableBuffer extends Closeable {

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

}
