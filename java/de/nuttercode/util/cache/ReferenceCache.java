package de.nuttercode.util.cache;

import java.lang.ref.Reference;
import java.util.NoSuchElementException;

/**
 * Cache values by wrapping them in {@link Reference}s of type R.
 * 
 * @author Johannes B. Latzel
 *
 * @param <K>
 *            key type
 * @param <V>
 *            value type
 * @param <R>
 *            {@link Reference} type
 */
public abstract class ReferenceCache<K, V, R extends Reference<V>> implements Cache<K, V> {

	/**
	 * strongly caches keys to its {@link Reference}s
	 */
	private final StrongCache<K, R> strongCache;

	public ReferenceCache() {
		strongCache = new StrongCache<>();
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

	/**
	 * @param value
	 * @return new Reference which wraps around the value
	 */
	protected abstract R createReference(V value);

	/**
	 * wraps the value and caches the reference
	 */
	@Override
	public void cache(K key, V value) {
		strongCache.cache(key, createReference(value));
	}
	
	@Override
	public V get(K key) {
		assureContains(key);
		return strongCache.get(key).get();
	}

	@Override
	public boolean contains(K key) {
		return strongCache.contains(key) && strongCache.get(key).get() != null;
	}

	@Override
	public void remove(K key) {
		if (strongCache.get(key).get() != null)
			strongCache.get(key).clear();
		strongCache.remove(key);
	}

	@Override
	public int size() {
		return strongCache.size();
	}

	@Override
	public String toString() {
		return "ReferenceCache [strongCache=" + strongCache + "]";
	}

}
