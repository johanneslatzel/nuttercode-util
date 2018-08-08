package util;

public interface Validatable {

	void invalidate();

	boolean isValid();

	default void assureValidity() {
		if (!isValid())
			throw new IllegalStateException();
	}

}
