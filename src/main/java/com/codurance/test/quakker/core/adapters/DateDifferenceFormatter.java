package com.codurance.test.quakker.core.adapters;

import com.codurance.test.quakker.core.domain.DateTime;

import java.time.Duration;

public class DateDifferenceFormatter {
	public String difference (final DateTime start, final DateTime end) {
		final Duration duration = end.minus(start);
		if (duration.toHours() > 0) {
			return "1 hour ago";
		}
		if (duration.toMinutes() > 0) {
			if (duration.toMinutes() > 1) {
				return String.format("%d minutes ago", duration.toMinutes());
			}
			return "1 minute ago";
		}
		final long seconds = duration.toMillis() / 1_000;
		if (seconds > 1) {
			return String.format("%d seconds ago", seconds);
		}
		return "1 second ago";
	}
}
