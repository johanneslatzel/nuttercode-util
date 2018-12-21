package de.nuttercode.util;

import java.util.Arrays;
import java.util.Random;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotEmpty;
import de.nuttercode.util.assurance.NotNull;

/**
 * utility-class to select an item from a collection of items
 * 
 * @author Johannes B. Latzel
 *
 */
public class Selector {

	/**
	 * implementation of <a href=
	 * "https://en.m.wikiedia.org/wiki/Fitness_proportionate_selection">roulette
	 * wheel selection as of 21. Dec. 2018</a>
	 * 
	 * @param pValues array of values from which a random index will be selected
	 * @param random  some Random
	 * @return selected index
	 */
	public static int selectFrom(@NotEmpty double[] pValues, @NotNull Random random) {
		Assurance.assureNotNull(random);
		Assurance.assureNotEmpty(pValues);
		double hit = random.nextDouble() * Arrays.stream(pValues).sum();
		double current = 0;
		for (int i = 0; i < pValues.length; i++) {
			current += pValues[i];
			if (hit <= current)
				return i;
		}
		throw new IllegalStateException();
	}

}
