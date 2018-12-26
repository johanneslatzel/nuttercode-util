package de.nuttercode.util;

import java.util.Arrays;

public class ArrayUtil {

	/**
	 * swaps array[i] with array[j]
	 * 
	 * @param array
	 * @param i
	 * @param j
	 */
	public static void swap(double[] array, int i, int j) {
		double temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * creates a deep-copy of array
	 * 
	 * @param array
	 * @return deep-copy of array
	 */
	public static double[][] copy(double[][] array) {
		double[][] copy = new double[array.length][array[0].length];
		for (int a = 0; a < copy.length; a++) {
			for (int b = 0; b < copy[a].length; b++) {
				copy[a][b] = array[a][b];
			}
		}
		return copy;
	}

	/**
	 * copies source into destination. destination must be at least as long and wide
	 * as source.
	 * 
	 * @param source
	 * @param destination
	 */
	public static void copy(double[][] source, double[][] destination) {
		for (int a = 0; a < source.length; a++) {
			for (int b = 0; b < source[a].length; b++) {
				destination[a][b] = source[a][b];
			}
		}
	}

	/**
	 * copies source into destination. destination must be at least as long and wide
	 * as source.
	 * 
	 * @param source
	 * @param destination
	 */
	public static <T> void copy(T[][] source, T[][] destination) {
		for (int a = 0; a < source.length; a++) {
			for (int b = 0; b < source[a].length; b++) {
				destination[a][b] = source[a][b];
			}
		}
	}

	/**
	 * creates an T[size] and initializes every element with constructor. hint needs
	 * to be some
	 * 
	 * @param size
	 * @param constructor
	 * @param hint
	 * @return
	 */
	public static <T> T[] createArray(int size, ArrayConstructorAction<T> constructor, T[] hint) {
		T[] array = Arrays.copyOf(hint, size);
		Range.of(0, size).forEach(i -> array[i] = constructor.create(i));
		return array;
	}

}
