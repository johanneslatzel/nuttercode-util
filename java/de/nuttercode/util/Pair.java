package de.nuttercode.util;

/**
 * utility-class to create pairs like {@link IntPair} and {@link LongPair}
 * 
 * @author Johannes B. Latzel
 *
 */
public class Pair {

	/**
	 * creates a new {@link IntPair#IntPair(int, int)} (i, j)
	 * 
	 * @param i
	 * @param j
	 * @return new {@link IntPair#IntPair(int, int)} (i, j)
	 */
	public static IntPair of(int i, int j) {
		return new IntPair(i, j);
	}

	/**
	 * creates a new {@link LongPair#LongPair(long, long)} (i, j)
	 * 
	 * @param i
	 * @param j
	 * @return new {@link LongPair#LongPair(long, long)} (i, j)
	 */
	public static LongPair of(long i, long j) {
		return new LongPair(i, j);
	}

	/**
	 * creates a new {@link StringPair#StringPair(String, String)} (i, j)
	 * 
	 * @param i
	 * @param j
	 * @return new {@link StringPair#StringPair(String, String)} (i, j)
	 */
	public static StringPair of(String i, String j) {
		return new StringPair(i, j);
	}

	/**
	 * creates a new {@link StringPair#StringPair(String, String)} (i, j)
	 * 
	 * @param i
	 * @param j
	 * @return new {@link StringPair#StringPair(String, String)} (i, j)
	 */
	public static StringPair of(String i, long j) {
		return new StringPair(i, Long.toString(j));
	}

}
