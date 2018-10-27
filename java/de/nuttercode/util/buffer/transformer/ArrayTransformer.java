package de.nuttercode.util.buffer.transformer;

import java.util.Arrays;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;
import de.nuttercode.util.buffer.ReadableBuffer;
import de.nuttercode.util.buffer.WritableBuffer;

/**
 * {@link ObjectTransformer} implementation for generic arrays
 * 
 * @author Johannes B. Latzel
 *
 * @param <T>
 *            some type
 */
public class ArrayTransformer<T> implements ObjectTransformer<T[]> {

	/**
	 * some T[] to create new T[]
	 */
	private final T[] hint;

	/**
	 * transforms elements of any T[] array
	 */
	private final ObjectTransformer<T> objectTransformer;

	public ArrayTransformer(@NotNull T[] hint, @NotNull ObjectTransformer<T> objectTransformer) {
		Assurance.assureNotNull(hint);
		Assurance.assureNotNull(objectTransformer);
		this.hint = hint;
		this.objectTransformer = objectTransformer;
	}

	@Override
	public void putInto(@NotNull T[] value, @NotNull WritableBuffer writableBuffer) {
		Assurance.assureNotNull(value);
		Assurance.assureNotNull(writableBuffer);
		writableBuffer.putInt(value.length);
		for (T t : value)
			objectTransformer.putInto(t, writableBuffer);
	}

	@Override
	public @NotNull T[] getFrom(@NotNull ReadableBuffer readableBuffer) {
		Assurance.assureNotNull(readableBuffer);
		T[] array = Arrays.copyOf(hint, readableBuffer.getInt());
		for (int a = 0; a < array.length; a++)
			array[a] = objectTransformer.getFrom(readableBuffer);
		return array;
	}

}
