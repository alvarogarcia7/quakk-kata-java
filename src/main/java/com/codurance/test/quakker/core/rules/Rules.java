package com.codurance.test.quakker.core.rules;

public class Rules {
    private final GatedRule[] rules;

    public Rules (final GatedRule... rules) {
        this.rules = rules;
    }

    public void applyTo (final String representation) {
        for (GatedRule rule : rules) {
            rule.apply(representation);
        }
    }
}
