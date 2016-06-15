package com.codurance.test.quakker.core.domain;

import com.codurance.test.quakker.core.ports.Clock;

import java.time.Duration;

public class Quakk {
    private final String message;
    private final User owner;
    private final DateTime dateTime;

    private Quakk (final String message, final User owner, final DateTime dateTime) {
        this.message = message;
        this.owner = owner;
        this.dateTime = dateTime;
    }

    public DateTime dateTime () {
        return dateTime;
    }

    public User owner () {
        return owner;
    }

    public String message () {
        return message;
    }

    public Duration since (final Clock clock) {
        return clock.now().minus(dateTime);
    }

    public static QuakkBuilder aNew(){
        return QuakkBuilder.aNew();
    }

    public static class QuakkBuilder {

        private String message;

        private User owner;

        private DateTime dateTime;

        public static QuakkBuilder aNew () {
            return new QuakkBuilder();
        }

        public QuakkBuilder withMessage (final String message) {
            this.message = message;
            return this;
        }

        public QuakkBuilder from (final User owner) {
            this.owner = owner;
            return this;
        }

        public QuakkBuilder at (final DateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Quakk build () {
            return new Quakk(message, owner, dateTime);
        }
    }

    @Override
    public boolean equals (final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Quakk quakk = (Quakk) o;

        if (message != null ? !message.equals(quakk.message) : quakk.message != null) return false;
        if (owner != null ? !owner.equals(quakk.owner) : quakk.owner != null) return false;
        return dateTime != null ? dateTime.equals(quakk.dateTime) : quakk.dateTime == null;

    }
    @Override
    public int hashCode () {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }

}
