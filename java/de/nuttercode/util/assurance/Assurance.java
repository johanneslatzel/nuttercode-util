package de.nuttercode.util.assurance;

import java.time.Instant;

import de.nuttercode.util.Closeable;
import de.nuttercode.util.Initializable;
import de.nuttercode.util.buffer.BufferMode;
import de.nuttercode.util.buffer.DynamicBuffer;

/**
 * provides parameter checks and throws IllegalArgumentException if appropriate
 * 
 * @author Johannes B. Latzel
 *
 */
public final class Assurance {

	/**
	 * @param object
	 * @returns object
	 * @throws IllegalArgumentException if and only if the object is null
	 */
	public static <T> T assureNotNull(T object) {
		if (object == null)
			throw new IllegalArgumentException("object is null");
		return object;
	}

	/**
	 * @param s
	 * @throws IllegalArgumentException if and only if the object is null or the
	 *                                  {@link String} is empty
	 */
	public static void assureNotEmpty(String s) {
		if (s == null || s.isEmpty())
			throw new IllegalArgumentException(s + " empty or null");
	}

	/**
	 * @param i
	 * @throws IllegalArgumentException if and only if the argument is not positive
	 */
	public static void assurePositive(long i) {
		if (i <= 0)
			throw new IllegalArgumentException(i + " <= 0");
	}

	/**
	 * @param i
	 * @throws IllegalArgumentException if and only if the argument is negative
	 */
	public static void assureNotNegative(long i) {
		if (i < 0)
			throw new IllegalArgumentException(i + " < 0");
	}

	/**
	 * @param i
	 * @throws IllegalArgumentException if and only if the argument is negative
	 */
	public static void assureNotNegative(double i) {
		if (i < 0)
			throw new IllegalArgumentException(i + " < 0");
	}

	/**
	 * @param i
	 * @throws IllegalArgumentException if and only if the argument is not positive
	 */
	public static void assurePositive(double i) {
		if (i <= 0)
			throw new IllegalArgumentException(i + " <= 0");
	}

	/**
	 * @param value
	 * @param leftBoundary
	 * @param rightBoundary
	 * @throws IllegalArugmentException if score is not element of [leftBoundary,
	 *                                  rightBoundary]
	 */
	public static void assureBoundaries(double value, double leftBoundary, double rightBoundary) {
		if (value < leftBoundary || value > rightBoundary)
			throw new IllegalArgumentException("score is not element of [" + leftBoundary + ", " + rightBoundary + "]");
	}

	/**
	 * @param value
	 * @param leftBoundary
	 * @param rightBoundary
	 * @throws IllegalArugmentException if score is not element of [leftBoundary,
	 *                                  rightBoundary]
	 */
	public static void assureBoundaries(long value, long leftBoundary, long rightBoundary) {
		if (value < leftBoundary || value > rightBoundary)
			throw new IllegalArgumentException("value is not element of [" + leftBoundary + ", " + rightBoundary + "]");
	}

	/**
	 * 
	 * @param buffer
	 * @param mode
	 * @throws IllegalArgumentException if buffer or mode is null
	 * @throws IllegalStateException    if buffer.getMode() is not equal to mode
	 */
	public static void assureMode(DynamicBuffer buffer, BufferMode mode) {
		assureNotNull(buffer);
		assureNotNull(mode);
		if (!buffer.getMode().equals(mode))
			throw new IllegalStateException("the mode of the buffer is " + buffer.getMode() + " but should be " + mode);
	}

	/**
	 * @param closeable
	 * @throws IllegalArgumentException if closeable is null
	 * @throws                          {@link IllegalStateException} if closeable
	 *                                  is closed
	 */
	public static void assureNotClosed(Closeable closeable) {
		Assurance.assureNotNull(closeable);
		if (closeable.isClosed())
			throw new IllegalStateException("the closeable " + closeable + " is closed");
	}

	/**
	 * @param initializable
	 * @throws IllegalArgumentException if initializable is null
	 * @throws                          {@link IllegalStateException} if
	 *                                  initializable is not initialized
	 */
	public static void assureInitialized(Initializable initializable) {
		Assurance.assureNotNull(initializable);
		if (!initializable.isInitialized())
			throw new IllegalStateException("the initializable " + initializable + " is not initialized");
	}

	/**
	 * @param initializable
	 * @throws IllegalArgumentException if initializable is null
	 * @throws                          {@link IllegalStateException} if
	 *                                  initializable is initialized
	 */
	public static void assureNotInitialized(Initializable initializable) {
		Assurance.assureNotNull(initializable);
		if (initializable.isInitialized())
			throw new IllegalStateException("the initializable " + initializable + " is initialized");
	}

	/**
	 * 
	 * @param marker  some point in time against which instant will be measured
	 * @param instant some instant
	 * @throws IllegalArgumentException when marker is after instant
	 */
	public static void assureInstantIsNotBefore(Instant marker, Instant instant) {
		if (marker.isAfter(instant))
			throw new IllegalArgumentException(marker + " is after " + instant);
	}

	/**
	 * 
	 * @param begin
	 * @param end
	 * @throws IllegalArgumentException if begin > end
	 */
	public static void assureSmallerEquals(long begin, long end) {
		if (begin > end)
			throw new IllegalArgumentException(begin + " > " + end);
	}

	/**
	 * 
	 * @param begin
	 * @param end
	 * @throws IllegalArgumentException if begin > end
	 */
	public static void assureSmallerEquals(double begin, double end) {
		if (begin > end)
			throw new IllegalArgumentException(begin + " > " + end);
	}

	/**
	 * 
	 * @param scalar
	 * @param i
	 * @throws IllegalArgumentException if and only if scalar == i
	 */
	public static void assureNotEqual(double scalar, double i) {
		if (scalar == i)
			throw new IllegalArgumentException();
	}

	/**
	 * 
	 * @param scalar
	 * @param i
	 * @throws IllegalArgumentException if and only if scalar == i
	 */
	public static void assureNotEqual(long scalar, long i) {
		if (scalar == i)
			throw new IllegalArgumentException();
	}

}
