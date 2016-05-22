package com.codurance.test.quakker.core.rules;

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

	@Test
	public void apply_rules_in_order () {

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
