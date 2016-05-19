package com.codurance.test.quakker;

public class Quakk {
	private final String message;
	private final User owner;
	private final DateTime dateTime;

	public Quakk (final String message, final User owner) {
		this(message, owner, DateTime.now());
	}

	public Quakk (final String message, final User owner, final DateTime dateTime) {
		this.message = message;
		this.owner = owner;
		this.dateTime = dateTime;
	}

	@Override
	public String toString () {
		final StringBuffer sb = new StringBuffer("Quakk{");
		sb.append("message='").append(message).append('\'');
		sb.append(", owner=").append(owner);
		sb.append(", dateTime=").append(dateTime);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals (final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Quakk quakk = (Quakk) o;

		if (message != null ? !message.equals(quakk.message) : quakk.message != null) return false;
		if (owner != null ? !owner.equals(quakk.owner) : quakk.owner != null) return false;
		return dateTime != null ? dateTime.equals(quakk.dateTime) : quakk.dateTime == null;

	}

	@Override
	public int hashCode () {
		int result = message != null ? message.hashCode() : 0;
		result = 31 * result + (owner != null ? owner.hashCode() : 0);
		result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
		return result;
	}
}
