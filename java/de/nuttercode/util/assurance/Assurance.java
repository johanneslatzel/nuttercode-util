package de.nuttercode.util.assurance;

import java.time.Instant;
import java.util.Collection;

import de.nuttercode.util.Initializable;

/**
 * provides parameter checks and throws IllegalArgumentException or
 * NullPointerException if appropriate
 * 
 * @author Johannes B. Latzel
 *
 */
public final class Assurance {

	/**
	 * @param object
	 * @returns object
	 * @throws NullPointerException if and only if the object is null
	 */
	public static <T> T assureNotNull(T object) {
		if (object == null)
			throw new NullPointerException("object is null");
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
	 * @param s
	 * @param domain
	 * @throws IllegalArgumentException if s is null, domain is empty, or s contains
	 *                                  a char not contained in domain
	 */
	public static void assureDomain(@NotNull String s, @NotEmpty Collection<Character> domain) {
		Assurance.assureNotNull(s);
		Assurance.assureNotEmpty(domain);
		for (char c : s.toCharArray()) {
			if (!domain.contains(c))
				throw new IllegalArgumentException("domain does not contain character \"" + c + "\"");
		}
	}

	/**
	 * @param s
	 * @param domain
	 * @throws IllegalArgumentException if s is null, domain is empty, or s contains
	 *                                  a char not contained in domain
	 */
	public static void assureDomain(@NotNull String s, @NotEmpty char[] domain) {
		Assurance.assureNotNull(s);
		Assurance.assureNotNull(domain);
		Assurance.assureBoundaries(domain.length, 1, Integer.MAX_VALUE);
		for (char c : s.toCharArray()) {
			boolean found = false;
			for (int a = 0; a < domain.length && !found; a++)
				if (c == domain[a])
					found = true;
			if (!found)
				throw new IllegalArgumentException("domain does not contain character \"" + c + "\"");
		}
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
	 * @return value
	 * @throws IllegalArugmentException if score is not element of [leftBoundary,
	 *                                  rightBoundary]
	 */
	public static double assureBoundaries(double value, double leftBoundary, double rightBoundary) {
		if (value < leftBoundary || value > rightBoundary)
			throw new IllegalArgumentException(
					"value " + value + " is not element of [" + leftBoundary + ", " + rightBoundary + "]");
		return value;
	}

	/**
	 * @param value
	 * @param leftBoundary
	 * @param rightBoundary
	 * @return value
	 * @throws IllegalArugmentException if score is not element of [leftBoundary,
	 *                                  rightBoundary]
	 */
	public static long assureBoundaries(long value, long leftBoundary, long rightBoundary) {
		if (value < leftBoundary || value > rightBoundary)
			throw new IllegalArgumentException(
					"value " + value + " is not element of [" + leftBoundary + ", " + rightBoundary + "]");
		return value;
	}

	/**
	 * @param initializable
	 * @throws IllegalArgumentException if initializable is null
	 * @throws {@link                   IllegalStateException} if initializable is
	 *                                  not initialized
	 */
	public static void assureInitialized(Initializable initializable) {
		Assurance.assureNotNull(initializable);
		if (!initializable.isInitialized())
			throw new IllegalStateException("the initializable " + initializable + " is not initialized");
	}

	/**
	 * @param initializable
	 * @throws IllegalArgumentException if initializable is null
	 * @throws {@link                   IllegalStateException} if initializable is
	 *                                  initialized
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
	 * @param scalar
	 * @param i
	 * @throws IllegalArgumentException if and only if scalar == i
	 */
	public static void assureNotEqual(long scalar, long i) {
		if (scalar == i)
			throw new IllegalArgumentException();
	}

	/**
	 * @param array
	 * @throws IllegalArgumentException if array is null or if array.length == 0
	 */
	public static void assureNotEmpty(double[] array) {
		if (Assurance.assureNotNull(array).length == 0)
			throw new IllegalArgumentException();
	}

	/**
	 * @param array
	 * @throws IllegalArgumentException if array is null or if array.length == 0
	 */
	public static void assureNotEmpty(String[] array) {
		if (Assurance.assureNotNull(array).length == 0)
			throw new IllegalArgumentException();
	}

	/**
	 * @param collection
	 * @throws IllegalArgumentException if collection is null or empty
	 */
	public static <T> void assureNotEmpty(Collection<T> collection) {
		if (Assurance.assureNotNull(collection).isEmpty())
			throw new IllegalArgumentException("list is empty");
	}

	/**
	 * @param value
	 * @param equalValue
	 * @throws IllegalArgumentException if and only if value != equalValue
	 */
	public static void assureEquals(int value, int equalValue) {
		Assurance.assureBoundaries(value, equalValue, equalValue);
	}

	/**
	 * @param s1
	 * @param s2
	 * @throws IllegalArgumentException if s1 equals s2
	 */
	public static void assureNotEqual(String s1, String s2) {
		if ((s1 == s2) || (s1 != null && s1.equals(s2)))
			throw new IllegalArgumentException(s1 + " equals " + s2);
	}

	/**
	 * @param hashedPassword
	 * @throws IllegalArgumentException if b is null or b.length == 0
	 */
	public static void assureNotEmpty(byte[] b) {
		Assurance.assureNotNull(b);
		if (b.length == 0)
			throw new IllegalArgumentException("length of " + b + " is 0");
	}

}
