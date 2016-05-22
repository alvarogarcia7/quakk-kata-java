package com.codurance.test.quakker.core.adapters;

import com.codurance.test.quakker.core.domain.DateTime;

import java.time.Duration;

public class DateDifferenceFormatter {
	public String difference (final DateTime start, final DateTime end) {
		final Duration duration = end.minus(start);
		if (duration.toMillis() > 1000) {
			return String.format("%d seconds ago", duration.toMillis() / 1_000);
		}
		return "1 second ago";
	}
}
