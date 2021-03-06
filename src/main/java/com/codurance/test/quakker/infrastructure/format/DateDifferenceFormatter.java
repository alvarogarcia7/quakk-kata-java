package com.codurance.test.quakker.infrastructure.format;

import java.time.Duration;

public class DateDifferenceFormatter {
    public String difference (final Duration duration) {
        final long hours = duration.toHours();
        final long minutes = duration.toMinutes();
        final long seconds = duration.toMillis() / 1_000;

        if (duration.isZero()) {
            return "just now";
        }

        Unit unit;
        if (hours > 0) {
            unit = new Unit("hour", "hours", hours);
        } else if (minutes > 0) {
            unit = new Unit("minute", "minutes", minutes);
        } else if (seconds > 0) {
            unit = new Unit("second", "seconds", seconds);
        } else {
            return "earlier";
        }
        return String.format("%s ago", unit.describe());
    }

    private class Unit {
        private final String singular;
        private final String plural;
        private final long actualAmount;

        public Unit (final String singular, final String plural, final long actualAmount) {
            this.singular = singular;
            this.plural = plural;
            this.actualAmount = actualAmount;
        }

        public String describe () {
            String unit = singular;
            if (actualAmount > 1) {
                unit = plural;
            }
            return String.format("%d %s", actualAmount, unit);
        }
    }
}
