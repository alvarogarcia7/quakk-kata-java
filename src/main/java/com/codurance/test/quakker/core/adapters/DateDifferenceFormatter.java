package com.codurance.test.quakker.core.adapters;

import com.codurance.test.quakker.core.domain.DateTime;

import java.time.Duration;

public class DateDifferenceFormatter {
	public String difference (final DateTime start, final DateTime end) {
		final Duration duration = end.minus(start);
		final long hours = duration.toHours();
		final long minutes = duration.toMinutes();
		final long seconds = duration.toMillis() / 1_000;
		final Unit hour = new Unit("hour", "hours");
		if (hours > 0) {
			return String.format("%s ago", hour.of(hours));
		}
		if (minutes > 0) {
			if (minutes > 1) {
				return String.format("%d minutes ago", minutes);
			}
			return "1 minute ago";
		}
		if (seconds > 0) {
			if (seconds > 1) {
				return String.format("%d seconds ago", seconds);
			}
			return "1 second ago";
		}
		return "just now";
	}

	private class Unit {
		private final String singular;
		private final String plural;

		public Unit (final String singular, final String plural) {
			this.singular = singular;
			this.plural = plural;
		}

		public String of (final long actualAmount) {
			String unit = singular;
			if (actualAmount > 1) {
				unit = plural;
			}
			return String.format("%d %s", actualAmount, unit);
		}
	}
}
