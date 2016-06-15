package com.codurance.test.quakker.test.unit.core.rules;

import com.codurance.test.quakker.core.rules.Rule;
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
    public void stop_when_the_first_rule_applies () {

        execute_rule_that_applies_then_rule_that_does_not();
    }

    @Test
    public void apply_rules_in_order () {

        execute_rule_that_applies_then_rule_that_does_not();
    }

    private void execute_rule_that_applies_then_rule_that_does_not () {
        final String representation = "A";
        final Rule ruleThatApplies = context.mock(Rule.class, "ruleThatApplies");
        final Rule ruleThatDoesNotApply = context.mock(Rule.class, "ruleThatDoesNotApply");
        context.checking(new Expectations() {{
            oneOf(ruleThatApplies).appliesTo(representation); will(returnValue(true));
            oneOf(ruleThatApplies).apply(representation);
        }});

        new Rules(ruleThatApplies, ruleThatDoesNotApply).applyTo(representation);


        context.assertIsSatisfied();
    }

}
