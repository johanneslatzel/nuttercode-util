package de.nuttercode.util.cache;

import java.lang.ref.SoftReference;

/**
 * A {@link Cache} build over {@link SoftReference}s. Values will be removed
 * whenever the the Garbage Collector needs to collect the values. See
 * {@link SoftReference} for specific informations.
 * 
 * @author Johannes B. Latzel
 *
 * @param <K>
 *            key type
 * @param <V>
 *            value type
 */
public class SoftCache<K, V> extends ReferenceCache<K, V, SoftReference<V>> {

	@Override
	protected SoftReference<V> createReference(V value) {
		return new SoftReference<>(value);
	}

}
