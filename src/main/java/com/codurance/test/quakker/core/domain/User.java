package com.codurance.test.quakker.core.domain;

public class User {
    private final String name;

    public User (final String name) {
        this.name = name;
    }

    @Override
    public boolean equals (final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final User user = (User) o;

        return name != null ? name.equals(user.name) : user.name == null;

    }

    @Override
    public int hashCode () {
        return name != null ? name.hashCode() : 0;
    }

    public String name () {
        return name;
    }
}
