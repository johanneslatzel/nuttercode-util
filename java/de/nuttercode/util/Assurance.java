package de.nuttercode.util.buffer;

public final class Assurance {

	public static void assureNotNull(Object object) {
		if (object == null)
			throw new IllegalArgumentException("object is null");
	}

	public static void assureNotEmpty(String s) {
		if (s == null || s.isEmpty())
			throw new IllegalArgumentException(s + " empty or null");
	}

	public static void assurePositive(long i) {
		if (i <= 0)
			throw new IllegalArgumentException(i + " <= 0");
	}

}
