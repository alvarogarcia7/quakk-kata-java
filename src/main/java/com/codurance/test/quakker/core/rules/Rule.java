package com.codurance.test.quakker.core.rules;

public interface Rule {
    void apply (final String representation);

    boolean appliesTo (String representation);
}
