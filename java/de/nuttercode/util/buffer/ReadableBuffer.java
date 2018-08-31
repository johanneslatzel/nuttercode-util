package de.nuttercode.util.buffer;

import java.nio.ByteBuffer;

import de.nuttercode.util.Closeable;

public interface ReadableBuffer extends Closeable {

	int getInt();

	long getLong();

	float getFloat();

	double getDouble();

	String getString();

	char getChar();

	byte getByte();

	short getShort();

	byte[] getBytes(byte[] bytes);

	int transferableData();

	default boolean hasTransferableData() {
		assureNotClosed();
		return transferableData() > 0;
	}

	default void assureConsumableData(int size) {
		assureNotClosed();
		if (transferableData() < size)
			throw new IllegalStateException();
	}

	ByteBuffer transferDataInto(ByteBuffer outerBuffer);

}
