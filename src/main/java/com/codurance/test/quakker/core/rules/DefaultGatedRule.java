package com.codurance.test.quakker.core.rules;

public class DefaultGatedRule implements GatedRule {

    private final Rule rule;

    public DefaultGatedRule (Rule rule) {
        this.rule = rule;
    }

    @Override
    public void apply (final String representation) {
        if (rule.appliesTo(representation)) {
            rule.apply(representation);
        }
    }

}
