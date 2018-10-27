package de.nuttercode.util.buffer.transformer;

import de.nuttercode.util.buffer.ReadableBuffer;
import de.nuttercode.util.buffer.WritableBuffer;

/**
 * Trivial {@link ObjectTransformer} implementation for {@link Long} as a
 * singleton.
 * 
 * @author Johannes B. Latzel
 *
 */
public class LongTransformer implements ObjectTransformer<Long> {

	private static LongTransformer instance = null;

	/**
	 * singleton implementation
	 * 
	 * @return the {@link LongTransformer}
	 */
	public static LongTransformer get() {
		synchronized (LongTransformer.class) {
			if (instance == null)
				instance = new LongTransformer();
		}
		return instance;
	}

	private LongTransformer() {
	}

	@Override
	public void putInto(Long value, WritableBuffer writableBuffer) {
		writableBuffer.putLong(value);
	}

	@Override
	public Long getFrom(ReadableBuffer readableBuffer) {
		return readableBuffer.getLong();
	}

}
