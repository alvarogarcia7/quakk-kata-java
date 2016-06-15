package com.codurance.test.quakker.test.unit.core.rules;

import com.codurance.test.quakker.core.rules.GatedRule;
import com.codurance.test.quakker.core.rules.Rules;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class RulesShould {

    private Mockery context;

    @Before
    public void setUp () {
        context = new Mockery();
    }

    @Test
    public void apply_rules_in_order () {

        execute_rule_that_applies_then_rule_that_does_not();
    }

    private void execute_rule_that_applies_then_rule_that_does_not () {
        final String representation = "A";
        final GatedRule rule1 = context.mock(GatedRule.class, "rule1");
        final GatedRule rule2 = context.mock(GatedRule.class, "rule2");
        context.checking(new Expectations() {{
            oneOf(rule1).apply(representation);
            oneOf(rule2).apply(representation);
        }});

        new Rules(rule1, rule2).applyTo(representation);


        context.assertIsSatisfied();
    }

}
