package com.codurance.test.quakker.core.rules;

public class Rules {
    private final Rule[] rules;

    public Rules (final Rule... rules) {
        this.rules = rules;
    }

    public void applyTo (final String representation) {
        for (Rule rule : rules) {
            rule.apply(representation);
        }
    }
}
