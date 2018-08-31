package de.nuttercode.util;

public interface Initializable {

	boolean isInitialized();

	default void assureInitialized() {
		if (!isInitialized())
			throw new IllegalStateException();
	}

	default void assureUninitialized() {
		if (isInitialized())
			throw new IllegalStateException();
	}

}
