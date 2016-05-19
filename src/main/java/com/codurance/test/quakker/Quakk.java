package com.codurance.test.quakker;

public class Quakk {
	private final String message;
	private final User owner;

	public Quakk (final String message, final User owner) {
		this.message = message;
		this.owner = owner;
	}

	@Override
	public boolean equals (final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Quakk quakk = (Quakk) o;

		if (message != null ? !message.equals(quakk.message) : quakk.message != null) return false;
		return owner != null ? owner.equals(quakk.owner) : quakk.owner == null;

	}

	@Override
	public int hashCode () {
		int result = message != null ? message.hashCode() : 0;
		result = 31 * result + (owner != null ? owner.hashCode() : 0);
		return result;
	}

	@Override
	public String toString () {
		final StringBuffer sb = new StringBuffer("Quakk{");
		sb.append('}');
		return sb.toString();
	}
}
