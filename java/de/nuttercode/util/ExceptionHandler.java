package de.nuttercode.util;

/**
 * handles all kinds of {@link Exception}s
 * 
 * @author Johannes B. Latzel
 *
 */
public interface ExceptionHandler {

	/**
	 * called whenever a {@link Exception} has been catched.
	 */
	void handle(Exception exception);

}
