package de.nuttercode.util;

import java.util.Random;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.InLongRange;
import de.nuttercode.util.assurance.NotNegative;
import de.nuttercode.util.assurance.NotNull;

/**
 * 
 * utility class
 * 
 * @author Johannes B. Latzel
 *
 */
public class StringUtil {

	/**
	 * creates a random string of length minLength + random * (maxLength -
	 * minLength) of letters from a given alphabet or if the alphabet is null or
	 * empty will choose random char
	 * 
	 * @param random
	 * @param minLength value >= 0
	 * @param maxLength value >= minLength
	 * @param alphabet  may be null
	 * @return random string of length minLength + random * (maxLength - minLength)
	 * @throws NullPointerException     if random or alphabet is null
	 * @throws IllegalArgumentException if minLength < 0 or minLength > maxLength
	 */
	public static String randomString(@NotNull Random random, @NotNegative int minLength,
			@InLongRange(begin = 0, end = Integer.MAX_VALUE) int maxLength, char[] alphabet) {
		Assurance.assureNotNull(random);
		Assurance.assureNotNegative(minLength);
		Assurance.assureBoundaries(maxLength, minLength, Integer.MAX_VALUE);
		char[] name = new char[minLength + random.nextInt(maxLength - minLength)];
		for (int a = 0; a < name.length; a++)
			name[a] = alphabet == null || alphabet.length == 0 ? (char) random.nextInt()
					: alphabet[random.nextInt(alphabet.length)];
		return new String(name);
	}

}
