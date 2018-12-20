package de.nuttercode.util;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;

/**
 * represents a closed interval [a, b] of int type
 * 
 * @author Johannes B. Latzel
 *
 */
public class IntInterval implements Comparable<IntInterval> {

	private final int begin;
	private final int end;

	/**
	 * creates an interval [value, value]
	 * 
	 * @param value
	 */
	public IntInterval(int value) {
		this(value, value);
	}

	/**
	 * creates an interval [begin, end]
	 * 
	 * @param begin
	 * @param end
	 * @throws IllegalArgumentException if begin > end
	 */
	public IntInterval(int begin, int end) {
		Assurance.assureSmallerEquals(begin, end);
		this.begin = begin;
		this.end = end;
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	/**
	 * @param l
	 * @return true if l element of this interval
	 */
	public boolean contains(int l) {
		return begin <= l && l <= end;
	}

	/**
	 * @param intInterval
	 * @return true if intInterval is completely contained in this interval
	 */
	public boolean contains(@NotNull IntInterval intInterval) {
		Assurance.assureNotNull(intInterval);
		return begin <= intInterval.getBegin() && intInterval.getEnd() <= end;
	}

	/**
	 * @param intInterval
	 * @return true if an element l exists which is contained in this interval and
	 *         in intInterval
	 */
	public boolean intersectsWith(@NotNull IntInterval intInterval) {
		Assurance.assureNotNull(intInterval);
		return intInterval.contains(begin) || intInterval.contains(end);
	}

	/**
	 * @return length of this interval determined by end - begin
	 */
	public int getLength() {
		return end - begin;
	}

	public void forEach(IntAction action) {
		for (int a = begin; a < end; a++)
			action.apply(a);
	}

	/**
	 * unites this {@link IntInterval} with another and returns the result. this
	 * operation creates a completely new object which reflects the operation.
	 * 
	 * @param right
	 * @return united {@link IntInterval} or left and right. left and right are
	 *         contained in this interval.
	 * @throws IllegalArgumentException if this and right do not intersect
	 */
	@NotNull
	public IntInterval unite(@NotNull IntInterval right) {
		Assurance.assureNotNull(right);
		if (!intersectsWith(right))
			throw new IllegalArgumentException(this + " does not intersect with " + right);
		return new IntInterval(Math.min(getBegin(), right.getBegin()), Math.max(getEnd(), right.getEnd()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (begin ^ (begin >>> 32));
		result = prime * result + (int) (end ^ (end >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntInterval other = (IntInterval) obj;
		if (begin != other.begin)
			return false;
		if (end != other.end)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + begin + ", " + end + "]";
	}

	@Override
	public int compareTo(@NotNull IntInterval o) {
		Assurance.assureNotNull(o);
		return Long.compare(getLength(), o.getLength());
	}

	public interface IntAction {
		void apply(int value);
	}

}
