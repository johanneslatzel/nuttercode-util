package de.nuttercode.util.test;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;

/**
 * converts stack traces to Strings
 * 
 * @author Johannes B. Latzel
 */
public final class StackTraceConverter {

	/**
	 * converts the trace of the current stack to a {@link java.lang.String String}
	 *
	 * @return the stack trace - every {@link java.lang.StackTraceElement
	 *         StackTraceElement} is printed on its own line
	 * @throws IllegalArgumentExceptin
	 *             if {@link #getStackTrace(Thread)} does
	 */
	public static @NotNull String getStackTrace() {
		return getStackTrace(Thread.currentThread());
	}

	/**
	 * converts the trace of the given stack to a {@link java.lang.String String}
	 * 
	 * @param thread
	 * @return the stack trace - every {@link java.lang.StackTraceElement
	 *         StackTraceElement} is printed on its own line
	 * @throws IllegalArgumentException
	 *             if the thread is equal to null
	 */
	public static @NotNull String getStackTrace(@NotNull Thread thread) {
		Assurance.assureNotNull(thread);
		StackTraceElement[] stackTraceArray = thread.getStackTrace();
		StringBuilder stackTrace = new StringBuilder(stackTraceArray.length * 50);
		for (int a = 0; a < stackTraceArray.length; a++) {
			stackTrace.append(stackTraceArray[a].toString());
			if (a != stackTraceArray.length - 1) {
				stackTrace.append('\n');
			}
		}
		return stackTrace.toString();
	}

}
