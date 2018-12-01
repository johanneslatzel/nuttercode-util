package de.nuttercode.util.buffer.transformer;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import de.nuttercode.util.buffer.BufferInputStream;
import de.nuttercode.util.buffer.BufferOutputStream;
import de.nuttercode.util.buffer.ReadableBuffer;
import de.nuttercode.util.buffer.WritableBuffer;

/**
 * implementation of {@link ObjectTransformer} for {@link Serializable} objects
 * 
 * @author Johannes B. Latzel
 *
 * @param <T> any {@link Serializable} object
 */
public class SerializableTransformer<T extends Serializable> implements ObjectTransformer<T>, Closeable {

	/**
	 * wraps around {@link #bos}
	 */
	private ObjectOutputStream oos;

	/**
	 * wraps around {@link #bis}
	 */
	private ObjectInputStream ois;

	/**
	 * wraps around the {@link ReadableBuffer} given by
	 * {@link #getFrom(ReadableBuffer)}
	 */
	private BufferInputStream bis;

	/**
	 * wraps around the {@link WritableBuffer} given by
	 * {@link #putInto(Serializable, WritableBuffer)}
	 */
	private BufferOutputStream bos;

	/**
	 * creates new instance
	 */
	public SerializableTransformer() {
		oos = null;
		ois = null;
		bis = null;
		bos = null;
	}

	@Override
	public void putInto(T value, WritableBuffer writableBuffer) {
		if (bos == null)
			bos = new BufferOutputStream(writableBuffer);
		else
			bos.setBuffer(writableBuffer);
		if (oos == null)
			try {
				oos = new ObjectOutputStream(bos);
			} catch (IOException e) {
				throw new IllegalStateException("can not create ObjectOutputStream", e);
			}
		try {
			oos.writeObject(oos);
		} catch (IOException e) {
			throw new RuntimeException("can not write " + value + " to the ObjectOutputStream", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getFrom(ReadableBuffer readableBuffer) {
		if (bis == null)
			bis = new BufferInputStream(readableBuffer);
		else
			bis.setBuffer(readableBuffer);
		if (ois == null)
			try {
				ois = new ObjectInputStream(bis);
			} catch (IOException e) {
				throw new IllegalStateException("can not create ObjectInputStream", e);
			}
		try {
			return (T) ois.readObject();
		} catch (IOException e) {
			throw new RuntimeException("can not read the next object from the ObjectInputStream", e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("can not the class of the next object of the ObjectInputStream", e);
		}
	}

	@Override
	public void close() throws IOException {
		oos.flush();
		oos.close();
		ois.close();
	}

}
