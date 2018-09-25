package de.nuttercode.util.cache;

public class CacheTest {

	public static void main(String[] args) {
		HybridCache<Integer, Integer> cache = new HybridCache<>();

		System.out.println(cache);
		cache.cache(42, 1000);
		System.out.println(cache);
		cache.cache(43, 1000);
		System.out.println(cache);
		cache.cache(44, 1000);
		System.out.println(cache);
		cache.cache(44, 1000);
		System.out.println(cache);

		cache.cache(42, 1000, CacheType.STRONG);
		System.out.println(cache);
		cache.cache(43, 1000, CacheType.SOFT);
		System.out.println(cache);
		cache.cache(44, 1000, CacheType.STRONG);
		System.out.println(cache);
		cache.cache(44, 1000, CacheType.WEAK);
		System.out.println(cache);
		System.out.println("contains 42: " + cache.contains(42));
		System.out.println("contains 43: " + cache.contains(43));
		System.out.println("contains 44: " + cache.contains(44));
		
		System.gc();
		
		System.out.println(cache);
		System.out.println("contains 42: " + cache.contains(42));
		System.out.println("contains 43: " + cache.contains(43));
		System.out.println("contains 44: " + cache.contains(44));
	}

}
