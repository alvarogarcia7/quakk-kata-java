package com.codurance.test.quakker.infrastructure.format;

import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.Format;

import java.time.Duration;

public class WallFormat implements Format {
    private final Clock clock;

    public WallFormat (final Clock clock) {
        this.clock = clock;
    }

    @Override
    public String format (Quakk quakk) {
        final String timeAgo = formatTime(quakk.since(clock));
        final String quakkMessage = quakk.message();
        final String user = quakk.owner().name();
        return String.format("%s - %s (%s)", user, quakkMessage, timeAgo);
    }

    private String formatTime (final Duration duration) {
        return new DateDifferenceFormatter().difference(duration);
    }
}
