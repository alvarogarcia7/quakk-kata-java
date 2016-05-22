package com.codurance.test.quakker.core.adapters;

import com.codurance.test.quakker.core.domain.DateTime;

import java.time.Duration;

public class DateDifferenceFormatter {
	public String difference (final DateTime start, final DateTime end) {
		final Duration duration = end.minus(start);
		final long hours = duration.toHours();
		if (hours > 0) {
			if (hours > 1) {
				return String.format("%d hours ago", hours);
			}
			return "1 hour ago";
		}
		final long minutes = duration.toMinutes();
		if (minutes > 0) {
			if (minutes > 1) {
				return String.format("%d minutes ago", minutes);
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
