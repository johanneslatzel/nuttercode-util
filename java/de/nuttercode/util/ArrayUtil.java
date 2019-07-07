package de.nuttercode.util;

import java.util.Arrays;

public class ArrayUtil {

	private final static int COPY_STEPS = 5;

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
	 * swaps array[i] with array[j]
	 * 
	 * @param array
	 * @param i
	 * @param j
	 */
	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * swaps array[i] with array[j]
	 * 
	 * @param array
	 * @param i
	 * @param j
	 */
	public static <T> void swap(T[] array, int i, int j) {
		T temp = array[i];
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

	/**
	 * puts the short value into data at the given position (big endian)
	 * 
	 * @param data
	 * @param s
	 * @param position
	 */
	public static void putShort(byte[] data, short s, int position) {
		data[position] = (byte) ((s >> 8) & 0xff);
		data[position + 1] = (byte) (s & 0xff);
	}

	/**
	 * puts the int value into data at the given position (big endian)
	 * 
	 * @param data
	 * @param s
	 * @param position
	 */
	public static void putInt(byte[] data, int s, int position) {
		data[position] = (byte) ((s >> 24) & 0xff);
		data[position + 1] = (byte) ((s >> 16) & 0xff);
		data[position + 2] = (byte) ((s >> 8) & 0xff);
		data[position + 3] = (byte) (s & 0xff);
	}

	/**
	 * puts the long value into data at the given position (big endian)
	 * 
	 * @param data
	 * @param s
	 * @param position
	 */
	public static void putLong(byte[] data, long s, int position) {
		data[position] = (byte) ((s >> 56) & 0xff);
		data[position + 1] = (byte) ((s >> 48) & 0xff);
		data[position + 2] = (byte) ((s >> 40) & 0xff);
		data[position + 3] = (byte) ((s >> 32) & 0xff);
		data[position + 4] = (byte) ((s >> 24) & 0xff);
		data[position + 5] = (byte) ((s >> 16) & 0xff);
		data[position + 6] = (byte) ((s >> 8) & 0xff);
		data[position + 7] = (byte) (s & 0xff);
	}

	/**
	 * puts the char value into data at the given position (big endian)
	 * 
	 * @param data
	 * @param s
	 * @param position
	 */
	public static void putChar(byte[] data, char s, int position) {
		data[position] = (byte) ((s >> 8) & 0xff);
		data[position + 1] = (byte) (s & 0xff);
	}

	/**
	 * puts the float value into data at the given position (big endian)
	 * 
	 * @param data
	 * @param f
	 * @param position
	 */
	public static void putFloat(byte[] data, float f, int position) {
		putInt(data, Float.floatToIntBits(f), position);
	}

	/**
	 * puts the double value into data at the given position (big endian)
	 * 
	 * @param data
	 * @param d
	 * @param position
	 */
	public static void putDouble(byte[] data, double d, int position) {
		putLong(data, Double.doubleToLongBits(d), position);
	}

	/**
	 * gets a char from data at the given position (big endian)
	 * 
	 * @param data
	 * @param position
	 * @return
	 */
	public static char getChar(byte[] data, int position) {
		return (char) getShort(data, position);
	}

	/**
	 * gets a short from data at the given position (big endian)
	 * 
	 * @param data
	 * @param position
	 * @return
	 */
	public static short getShort(byte[] data, int position) {
		return (short) (Byte.toUnsignedInt(data[position]) << Byte.BYTES * 8
				| Byte.toUnsignedInt(data[position + Byte.BYTES]));
	}

	/**
	 * gets an int from data at the given position (big endian)
	 * 
	 * @param data
	 * @param position
	 * @return
	 */
	public static int getInt(byte[] data, int position) {
		return Short.toUnsignedInt(getShort(data, position)) << Short.BYTES * 8
				| Short.toUnsignedInt(getShort(data, position + Short.BYTES));
	}

	/**
	 * gets a long from data at the given position (big endian)
	 * 
	 * @param data
	 * @param position
	 * @return
	 */
	public static long getLong(byte[] data, int position) {
		return Integer.toUnsignedLong(getInt(data, position)) << Integer.BYTES * 8
				| Integer.toUnsignedLong(getInt(data, position + Integer.BYTES));
	}

	/**
	 * gets a float from data at the given position (big endian)
	 * 
	 * @param data
	 * @param position
	 * @return
	 */
	public static float getFloat(byte[] data, int position) {
		return Float.intBitsToFloat(getInt(data, position));
	}

	/**
	 * gets a double from data at the given position (big endian)
	 * 
	 * @param data
	 * @param position
	 * @return
	 */
	public static double getDouble(byte[] data, int position) {
		return Double.longBitsToDouble(getLong(data, position));
	}

	/**
	 * puts the data of source from sourceOff until (but not included) sourceOff +
	 * length into destination from destOff until (but not included) destOff +
	 * length
	 * 
	 * @param destination
	 * @param source
	 * @param sourceOff
	 * @param length
	 * @param destOff
	 */
	public static void putBytes(byte[] destination, byte[] source, int sourceOff, int length, int destOff) {
		int sourcePos = sourceOff;
		int destPos = destOff;
		int sourceEnd = sourcePos + length;
		for (; sourcePos + COPY_STEPS < sourceEnd; sourcePos += COPY_STEPS, destPos += COPY_STEPS) {
			destination[destPos] = source[sourcePos];
			destination[destPos + 1] = source[sourcePos + 1];
			destination[destPos + 2] = source[sourcePos + 2];
			destination[destPos + 3] = source[sourcePos + 3];
			destination[destPos + 4] = source[sourcePos + 4];
		}
		for (; sourcePos < sourceEnd; sourcePos++, destPos++) {
			destination[destPos] = source[sourcePos];
		}
	}

	/**
	 * simplification of {@link #putBytes(byte[], byte[], int, int, int)} with
	 * destOff = 0
	 * 
	 * @param destination
	 * @param source
	 * @param offset
	 * @param length
	 */
	public static void putBytes(byte[] destination, byte[] source, int offset, int length) {
		putBytes(destination, source, offset, length, 0);
	}

}
