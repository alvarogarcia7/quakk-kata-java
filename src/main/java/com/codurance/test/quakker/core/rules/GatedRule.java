package com.codurance.test.quakker.core.rules;

public class GatedRule implements Rule {

    private final Rule rule;

    public GatedRule (Rule rule) {
        this.rule = rule;
    }

    @Override
    public void apply (final String representation) {
        if (rule.appliesTo(representation)) {
            rule.apply(representation);
        }
    }

    @Override
    public boolean appliesTo (final String representation) {
        return false;
    }
}
