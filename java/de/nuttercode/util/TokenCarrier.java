package de.nuttercode.util;

public abstract class TokenCarrier implements Validatable {

	private final Object token;
	private boolean isValid;

	protected TokenCarrier(Object token) {
		if (token == null)
			throw new NullPointerException();
		this.token = token;
		isValid = true;
	}

	public final boolean carriesToken(Object token) {
		assureValidity();
		return this.token == token;
	}

	public final boolean isValid() {
		return isValid;
	}

	public final void invalidate() {
		isValid = false;
	}

}
