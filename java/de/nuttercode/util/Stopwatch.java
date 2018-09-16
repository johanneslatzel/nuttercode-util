package de.nuttercode.util;

/**
 * trivial implementation of a stopwatch. can be used to processes with low
 * precision. use {@link #start()} to start the measurement. use {@link #stop}
 * to increase the total elapsed time with the timespan since the last
 * {@link #start()} / {@link #resume()}. after each {@link #stop()}
 * {@link #resume()} can be used to continue the measurement. every
 * {@link #start()} resets the saved total of elapsed time.
 * 
 * @author Johannes B. Latzel
 *
 */
public final class Stopwatch {

	private long startNanos;
	private long endNanos;
	private long elapsedNanos;
	private boolean isStarted;

	public Stopwatch() {
		startNanos = 0;
		endNanos = 0;
		elapsedNanos = 0;
		isStarted = false;
	}

	/**
	 * starts this stop watch and resets all measured timespans.
	 */
	public void start() {
		elapsedNanos = 0;
		isStarted = true;
		resume();
	}

	/**
	 * resumes this stopwatch
	 * 
	 * @throws IllegalStateException
	 *             if the stopwatch has already been started
	 */
	public void resume() {
		if (isStarted)
			throw new IllegalStateException("already started");
		startNanos = System.nanoTime();
	}

	/**
	 * stops this stopwatch and adds the elapsed time to the total of elapsed time
	 */
	public void stop() {
		if (!isStarted)
			throw new IllegalStateException("not started");
		endNanos = System.nanoTime();
		elapsedNanos += endNanos - startNanos;
		isStarted = false;
	}

	/**
	 * @return elapsed time between the first {@link #start()} and the first
	 *         {@link #stop()} and between all pairs of {@link #resume()} /
	 *         {@link #stop()} calls
	 */
	public long getElapsedNanos() {
		return elapsedNanos;
	}

}
