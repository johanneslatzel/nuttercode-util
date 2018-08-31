package de.nuttercode.util.buffer;

import de.nuttercode.util.Closeable;

public interface ModeableBuffer extends Closeable {
	
	void setMode(BufferMode mode);

	BufferMode getMode();

	default void assureMode(BufferMode mode) {
		assureNotClosed();
		if (!getMode().equals(mode))
			throw new IllegalStateException();
	}

	void lockMode();

	boolean isModeLocked();

}
