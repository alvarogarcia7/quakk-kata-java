package com.codurance.test.quakker.core.domain;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
	private final LocalTime value;

	public DateTime (String representation) {
		LocalTime value1;
		try {
			representation = obtainDateWithSeconds(representation);
			value1 = LocalTime.parse(representation, DateTimeFormatter.ofPattern("H:m:ss"));
		} catch (DateTimeParseException e) {
			throw new RuntimeException(e);
		}
		value = value1;
	}

	private String obtainDateWithSeconds (String representation) {
		if (representation.length() < 7) {
			representation = representation + ":00";
		}
		return representation;
	}

	public int compare (final DateTime other) {
		return this.value.compareTo(other.value);
	}

	public LocalTime value () {
		return value;
	}

	public Duration minus (final DateTime start) {
		final DateTime end = this;

		return Duration.between(start.value, end.value);
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
