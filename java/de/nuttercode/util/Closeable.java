package de.nuttercode.util;

public interface Closeable extends java.io.Closeable {

	boolean isClosed();

	default void assureNotClosed() {
		if (isClosed())
			throw new IllegalStateException();
	}

	default void assureClosed() {
		if (!isClosed())
			throw new IllegalStateException();
	}

}
