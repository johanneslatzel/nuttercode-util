package de.nuttercode.util.cache;

/**
 * Provides the type of caching used in a {@link HybridCache}.
 * 
 * @author Johannes B. Latzel
 *
 */
public enum CacheType {

	/**
	 * use {@link StrongCache}
	 */
	STRONG,

	/**
	 * use {@link SoftCache}
	 */
	SOFT,

	/**
	 * use {@link WeakCache}
	 */
	WEAK;

}
