package de.nuttercode.util;

/**
 * extension of {@link java.io.Closeable} interface. provides a method to
 * determine if an object has been closed or not
 * 
 * @author Johannes B. Latzel
 *
 */
public interface Closeable extends java.io.Closeable {

	/**
	 * @return true if the object has been closed
	 */
	boolean isClosed();

}
