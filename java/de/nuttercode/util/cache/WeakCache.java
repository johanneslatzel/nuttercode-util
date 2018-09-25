package de.nuttercode.util.cache;

import java.lang.ref.WeakReference;

/**
 * A {@link Cache} build over {@link WeakReference}s. Values will be removed
 * whenever the the Garbage Collector is called.
 * 
 * @author Johannes B. Latzel
 *
 * @param <K>
 *            key type
 * @param <V>
 *            value type
 */
public class WeakCache<K, V> extends ReferenceCache<K, V, WeakReference<V>> {
	
	@Override
	protected WeakReference<V> createReference(V value) {
		return new WeakReference<>(value);
	}

}
