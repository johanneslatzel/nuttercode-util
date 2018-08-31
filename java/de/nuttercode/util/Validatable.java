package de.nuttercode.util;

public interface Validatable {

	void invalidate();

	boolean isValid();

	default void assureValidity() {
		if (!isValid())
			throw new IllegalStateException();
	}

}
