package de.nuttercode.util;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;

/**
 * represents a closed interval [a, b] of long type
 * 
 * @author Johannes B. Latzel
 *
 */
public class LongInterval implements Comparable<LongInterval> {

	private final long begin;
	private final long end;

	/**
	 * creates an interval [value, value]
	 * 
	 * @param value
	 */
	public LongInterval(long value) {
		this(value, value);
	}

	/**
	 * creates an interval [begin, end]
	 * 
	 * @param begin
	 * @param end
	 * @throws IllegalArgumentException if begin > end
	 */
	public LongInterval(long begin, long end) {
		Assurance.assureSmallerEquals(begin, end);
		this.begin = begin;
		this.end = end;
	}

	public long getBegin() {
		return begin;
	}

	public long getEnd() {
		return end;
	}

	/**
	 * @param l
	 * @return true if l element of this interval
	 */
	public boolean contains(long l) {
		return begin <= l && l <= end;
	}

	/**
	 * @param longInterval
	 * @return true if longInterval is completely contained in this interval
	 */
	public boolean contains(@NotNull LongInterval longInterval) {
		Assurance.assureNotNull(longInterval);
		return begin <= longInterval.getBegin() && longInterval.getEnd() <= end;
	}

	/**
	 * @param longInterval
	 * @return true if an element l exists which is contained in this interval and
	 *         in longInterval
	 */
	public boolean intersectsWith(@NotNull LongInterval longInterval) {
		Assurance.assureNotNull(longInterval);
		return longInterval.contains(begin) || longInterval.contains(end);
	}

	/**
	 * @return length of this interval determined by end - begin
	 */
	public long getLength() {
		return end - begin;
	}

	public void forEach(LongAction action) {
		for (long a = begin; a < end; a++)
			action.apply(a);
	}

	/**
	 * unites this {@link LongInterval} with another and returns the result. this
	 * operation creates a completely new object which reflects the operation.
	 * 
	 * @param right
	 * @return united {@link LongInterval} or left and right. left and right are
	 *         contained in this interval.
	 * @throws IllegalArgumentException if this and right do not intersect
	 */
	@NotNull
	public LongInterval unite(@NotNull LongInterval right) {
		Assurance.assureNotNull(right);
		if (!intersectsWith(right))
			throw new IllegalArgumentException(this + " does not intersect with " + right);
		return new LongInterval(Math.min(getBegin(), right.getBegin()), Math.max(getEnd(), right.getEnd()));
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
		LongInterval other = (LongInterval) obj;
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
	public int compareTo(@NotNull LongInterval o) {
		Assurance.assureNotNull(o);
		return Long.compare(getLength(), o.getLength());
	}

	public interface LongAction {
		void apply(long value);
	}

}
