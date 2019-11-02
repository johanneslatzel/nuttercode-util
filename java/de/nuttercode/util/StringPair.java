package de.nuttercode.util;

/**
 * a pair of two String-values
 * 
 * @author Johannes B. Latzel
 *
 */
public class StringPair {

	/**
	 * the two int-values
	 */
	private final String i, j;

	/**
	 * creates a new pair (i, j)
	 * 
	 * @param i
	 * @param j
	 */
	public StringPair(String i, String j) {
		this.i = i;
		this.j = j;
	}

	/**
	 * @return value i
	 */
	public String getI() {
		return i;
	}

	/**
	 * @return value j
	 */
	public String getJ() {
		return j;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((i == null) ? 0 : i.hashCode());
		result = prime * result + ((j == null) ? 0 : j.hashCode());
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
		StringPair other = (StringPair) obj;
		if (i == null) {
			if (other.i != null)
				return false;
		} else if (!i.equals(other.i))
			return false;
		if (j == null) {
			if (other.j != null)
				return false;
		} else if (!j.equals(other.j))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StringPair [i=" + i + ", j=" + j + "]";
	}

}
