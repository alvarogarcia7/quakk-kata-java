package com.codurance.test.quakker.core.adapters;

import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.Format;

import java.time.Duration;

public class ReadingFormat implements Format {
	private final Clock clock;

	public ReadingFormat (final Clock clock) {
		this.clock = clock;
	}

	@Override
	public String format (Quakk quakk) {
		final String timeAgo = formatTime(quakk.since(clock));
		final String quakkMessage = quakk.message();
		final String user = quakk.owner().name();
		return String.format("%s (%s)", quakkMessage, timeAgo);
	}

	private String formatTime (final Duration duration) {
		final long seconds = duration.toMillis() / 1000;
		final long minutes = seconds / 60;
		return String.format("%d minutes ago", minutes);
	}
}
