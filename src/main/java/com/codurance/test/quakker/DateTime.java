package com.codurance.test.quakker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateTime {
	private final Date value;

	public DateTime (final String representation) {
		try {
			value = new SimpleDateFormat("HH:MM").parse(representation);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private DateTime (final Date value) {
		this.value = value;
	}

	public static DateTime now () {
		return new DateTime(Date.from(Instant.now()));
	}

	public String toString () {
		final StringBuffer sb = new StringBuffer("DateTime{");
		sb.append("value=").append(value);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals (final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final DateTime dateTime = (DateTime) o;

		return value != null ? value.equals(dateTime.value) : dateTime.value == null;

	}

	@Override
	public int hashCode () {
		return value != null ? value.hashCode() : 0;
	}
}
