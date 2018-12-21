package de.nuttercode.util;

/**
 * creates a new element of type T (or null) to be put into an array at index i.
 * 
 * @author Johannes B. Latzel
 *
 * @param <T> any type
 */
@FunctionalInterface
public interface ArrayConstructorAction<T> {

	/**
	 * creates a new element of type T (or null) to be put into an array at index i.
	 * 
	 * @param i array index at which this item will be put
	 * @return a new object of type T or null
	 */
	T create(int i);

}