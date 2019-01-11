package de.nuttercode.util;

import java.util.Random;

/**
 * utility class to manage indices
 * 
 * @author Johannes B. Latzel
 *
 */
public class Index {

	/**
	 * @param interval
	 * @param random
	 * @return random index in the interval
	 */
	public static int randomIn(IntInterval interval, Random random) {
		return interval.getBegin() + random.nextInt(interval.getEnd());
	}

	/**
	 * beware that this method will not terminate if the interval only contains i.
	 * 
	 * @param interval
	 * @param random
	 * @param i
	 * @return random index in the interval other than i
	 */
	public static int randomInOtherThan(IntInterval interval, Random random, int i) {
		int j;
		do {
			j = randomIn(interval, random);
		} while (j == i);
		return j;
	}

	/**
	 * beware that this method will not terminate if the interval does not contain 2
	 * different indices
	 * 
	 * @param interval
	 * @param random
	 * @return a pair of two different indices from interval
	 */
	public static IntPair randomDifferentIn(IntInterval interval, Random random) {
		int i = randomIn(interval, random);
		int j = randomInOtherThan(interval, random, i);
		return Pair.of(i, j);

	}

}
