package de.nuttercode.util.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * A {@link Cache} which references all values with strong references. This
 * cache will not clear itself. Use {@link #remove(Object)} to actually remove
 * values.
 * 
 * @author Johannes B. Latzel
 *
 * @param <K>
 *            key type
 * @param <V>
 *            value type
 */
public class StrongCache<K, V> implements Cache<K, V> {

	/**
	 * maps keys to values
	 */
	private final Map<K, V> strongMap;

	public StrongCache() {
		strongMap = new HashMap<>();
	}

	/**
	 * @param key
	 * @throws NoSuchElementException
	 *             if the value corresponding to the key is not contained in this
	 *             {@link Cache}
	 */
	private void assureContains(K key) {
		if (!contains(key))
			throw new NoSuchElementException("No mapping for key " + key);
	}

	@Override
	public void cache(K key, V value) {
		strongMap.put(key, value);
	}

	@Override
	public V get(K key) {
		assureContains(key);
		return strongMap.get(key);
	}

	@Override
	public boolean contains(K key) {
		return strongMap.containsKey(key);
	}

	@Override
	public void remove(K key) {
		assureContains(key);
		strongMap.remove(key);
	}

	@Override
	public int size() {
		return strongMap.size();
	}

	@Override
	public String toString() {
		return "StrongCache [size()=" + size() + "]";
	}

}
