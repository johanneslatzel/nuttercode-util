package de.nuttercode.util.cache;

import java.util.NoSuchElementException;

/**
 * Caches store objects in memory. The time to live is specified by the
 * implementing class. It maps keys to values.
 * 
 * @author Johannes B. Latzel
 *
 * @param <K>
 *            key type
 * @param <V>
 *            value type
 */
public interface Cache<K, V> {

	/**
	 * caches the given value and maps the given key to the given value. the value
	 * is only retrievable by the given key.
	 * 
	 * @param key
	 * @param value
	 */
	void cache(K key, V value);

	/**
	 * @param key
	 * @return the value given by the key
	 * @throws NoSuchElementException
	 *             if the key is not mapped
	 */
	V get(K key);

	/**
	 * @param key
	 * @return true if and only if the key is mapped
	 */
	boolean contains(K key);

	/**
	 * removes the value given by the key.
	 * 
	 * @param key
	 * @throws NoSuchElementException
	 *             if the key is not mapped
	 */
	void remove(K key);

	/**
	 * @return the total number of elements cached.
	 */
	int size();

}
