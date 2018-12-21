package de.nuttercode.util;

/**
 * a pair of two long-values
 * 
 * @author Johannes B. Latzel
 *
 */
public class LongPair {

	/**
	 * the two long-values
	 */
	private final long i, j;

	/**
	 * creates a new pair (i, j)
	 * 
	 * @param i
	 * @param j
	 */
	public LongPair(long i, long j) {
		this.i = i;
		this.j = j;
	}

	/**
	 * @return value i
	 */
	public long getI() {
		return i;
	}

	/**
	 * @return value j
	 */
	public long getJ() {
		return j;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (i ^ (i >>> 32));
		result = prime * result + (int) (j ^ (j >>> 32));
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
		LongPair other = (LongPair) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LongPair [i=" + i + ", j=" + j + "]";
	}

}
