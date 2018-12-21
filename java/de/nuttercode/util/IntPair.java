package de.nuttercode.util;

/**
 * a pair of two int-values
 * 
 * @author Johannes B. Latzel
 *
 */
public class IntPair {

	/**
	 * the two int-values
	 */
	private final int i, j;

	/**
	 * creates a new pair (i, j)
	 * 
	 * @param i
	 * @param j
	 */
	public IntPair(int i, int j) {
		this.i = i;
		this.j = j;
	}

	/**
	 * @return value i
	 */
	public int getI() {
		return i;
	}

	/**
	 * @return value j
	 */
	public int getJ() {
		return j;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
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
		IntPair other = (IntPair) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IntPair [i=" + i + ", j=" + j + "]";
	}

}
