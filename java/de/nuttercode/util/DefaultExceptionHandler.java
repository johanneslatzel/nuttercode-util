package de.nuttercode.util;

/**
 * calls {@link Exception#printStackTrace()} for every {@link Exception}
 * 
 * @author Johannes B. Latzel
 *
 */
public class DefaultExceptionHandler implements ExceptionHandler {

	@Override
	public void handle(Exception exception) {
		exception.printStackTrace();
	}

}
