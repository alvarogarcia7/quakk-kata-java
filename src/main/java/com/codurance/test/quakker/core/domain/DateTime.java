package com.codurance.test.quakker.core.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
	private final LocalTime value;

	public DateTime (final String representation) {
		LocalTime value1;
		try {
			value1 = LocalTime.parse(representation, DateTimeFormatter.ofPattern("H:m:ss"));
		} catch (DateTimeParseException e) {
			value1 = LocalTime.parse(representation, DateTimeFormatter.ofPattern("H:m"));
		}
		value = value1;
	}

	private DateTime (final LocalTime value) {
		this.value = value;
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

	public int compare (final DateTime other) {
		return this.value.compareTo(other.value);
	}

	public LocalTime value () {
		return value;
	}
}
