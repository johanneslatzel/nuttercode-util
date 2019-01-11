package de.nuttercode.util.transformer;

import de.nuttercode.util.buffer.ReadableBuffer;
import de.nuttercode.util.buffer.WritableBuffer;

/**
 * Trivial {@link ObjectTransformer} implementation for {@link Integer} as a
 * singleton.
 * 
 * @author Johannes B. Latzel
 *
 */
public class IntegerTransformer implements ObjectTransformer<Integer> {

	private static IntegerTransformer instance = null;

	/**
	 * singleton implementation
	 * 
	 * @return the {@link IntegerTransformer}
	 */
	public static IntegerTransformer get() {
		synchronized (IntegerTransformer.class) {
			if (instance == null)
				instance = new IntegerTransformer();
		}
		return instance;
	}

	private IntegerTransformer() {
	}

	@Override
	public void putInto(Integer value, WritableBuffer writableBuffer) {
		writableBuffer.putInt(value);
	}

	@Override
	public Integer getFrom(ReadableBuffer readableBuffer) {
		return readableBuffer.getInt();
	}

}
