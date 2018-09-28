package de.nuttercode.util.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Combines a {@link StrongCache}, {@link SoftCache}, and a {@link WeakCache}.
 * Choose via {@link #cache(Object, Object, CacheType)} which cache should be
 * used for the specific key/value pair. Supported {@link CacheType}s are
 * {@link CacheType#STRONG}, {@link CacheType#SOFT}, and {@link CacheType#WEAK}.
 * The default {@link CacheType} is {@link CacheType#SOFT}.
 * 
 * @author Johannes B. Latzel
 *
 * @param <K>
 *            key type
 * @param <V>
 *            value type
 */
public class HybridCache<K, V> implements Cache<K, V> {

	private final StrongCache<K, V> strongCache;
	private final SoftCache<K, V> softCache;
	private final WeakCache<K, V> weakCache;

	public HybridCache() {
		strongCache = new StrongCache<>();
		softCache = new SoftCache<>();
		weakCache = new WeakCache<>();
	}

	/**
	 * @param cacheType
	 * @return appropriate {@link Cache} instance
	 * @throws IllegalArgumentException
	 *             if the cacheType is not supported.
	 */
	private Cache<K, V> getCache(CacheType cacheType) {
		switch (cacheType) {
		case SOFT:
			return softCache;
		case STRONG:
			return strongCache;
		case WEAK:
			return weakCache;
		default:
			throw new IllegalArgumentException("cacheType " + cacheType + " is not supported.");
		}
	}

	/**
	 * Caches the key/value pair in the {@link Cache} specified by cacheType.
	 * Removes the key/value pair if it is already cached in another {@link Cache}.
	 * 
	 * @param key
	 * @param value
	 * @param cacheType
	 * @throws IllegalArgumentException
	 *             if the cacheType is not supported.
	 */
	public void cache(K key, V value, CacheType cacheType) {
		if (contains(key))
			remove(key);
		getCache(cacheType).cache(key, value);
	}

	/**
	 * Utility-method. Moves a key/value pair from its current {@link Cache} to
	 * another {@link Cache} given by cacheType. Same as if calling
	 * {@link #cache(Object, Object, CacheType) cache(key, get(key), cacheType)}
	 * directly.
	 * 
	 * @param key
	 * @param cacheType
	 * @throws IllegalArgumentException
	 *             if the cacheType is not supported.
	 */
	public void move(K key, CacheType cacheType) {
		cache(key, get(key), cacheType);
	}

	/**
	 * 
	 * @param cacheType
	 * @return size of the corresponding {@link Cache}.
	 * @throws IllegalArgumentException
	 *             if the cacheType is not supported.
	 */
	public int size(CacheType cacheType) {
		return getCache(cacheType).size();
	}

	/**
	 * implements {@link Cache#cache(Object, Object)} but uses
	 * {@link #cache(Object, Object, CacheType)} with the default {@link CacheType}.
	 * 
	 * @see Cache#cache(Object, Object)
	 */
	@Override
	public void cache(K key, V value) {
		cache(key, value, CacheType.SOFT);
	}

	@Override
	public V get(K key) {
		if (strongCache.contains(key))
			return strongCache.get(key);
		else if (softCache.contains(key))
			return softCache.get(key);
		return weakCache.get(key);
	}

	@Override
	public boolean contains(K key) {
		return strongCache.contains(key) || softCache.contains(key) || weakCache.contains(key);
	}

	@Override
	public void remove(K key) {
		if (strongCache.contains(key))
			strongCache.remove(key);
		else if (softCache.contains(key))
			softCache.remove(key);
		else
			weakCache.remove(key);
	}

	@Override
	public int size() {
		return strongCache.size() + softCache.size() + weakCache.size();
	}

	@Override
	public String toString() {
		return "HybridCache [strongCache=" + strongCache + ", softCache=" + softCache + ", weakCache=" + weakCache
				+ ", size()=" + size() + "]";
	}

	@Override
	public void clear() {
		strongCache.clear();
		softCache.clear();
		weakCache.clear();
	}

	@Override
	public void clean() {
		softCache.clean();
		weakCache.clean();
	}

	@Override
	public Set<K> getKeySet() {
		Set<K> keySet = new HashSet<>(size());
		keySet.addAll(strongCache.getKeySet());
		keySet.addAll(softCache.getKeySet());
		keySet.addAll(weakCache.getKeySet());
		return keySet;
	}

	@Override
	public Collection<V> getValueCollection() {
		ArrayList<V> valueList = new ArrayList<>(size());
		valueList.addAll(strongCache.getValueCollection());
		valueList.addAll(softCache.getValueCollection());
		valueList.addAll(weakCache.getValueCollection());
		return valueList;
	}

}
