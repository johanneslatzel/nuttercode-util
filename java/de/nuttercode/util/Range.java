package de.nuttercode.util;

/**
 * utility-class for Ranges, like {@link LongInterval} and {@link IntInterval}
 * 
 * @author johannes
 *
 */
public class Range {

	/**
	 * creates a new {@link LongInteval#LongInteval(long, long)} with begin <= end
	 * 
	 * @param begin
	 * @param end
	 * @return new {@link LongInteval#LongInteval(long, long)} with begin <= end
	 */
	public static LongInterval of(long begin, long end) {
		return new LongInterval(begin, end);
	}

	/**
	 * creates a new {@link IntInterval#IntInterval(int, int)} with begin <= end.
	 * 
	 * @param begin
	 * @param end
	 * @return new {@link IntInterval#IntInterval(int, int)} with begin <= end
	 */
	public static IntInterval of(int begin, int end) {
		return new IntInterval(begin, end);
	}

}
