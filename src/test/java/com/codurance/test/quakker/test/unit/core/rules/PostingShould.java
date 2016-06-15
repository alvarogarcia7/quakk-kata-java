package com.codurance.test.quakker.test.unit.core.rules;

import com.codurance.test.quakker.core.domain.DateTime;
import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.domain.User;
import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import com.codurance.test.quakker.core.rules.Posting;
import com.codurance.test.quakker.core.rules.Rule;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostingShould {

	private Mockery context;

	@Before
	public void setUp () {
		context = new Mockery();
	}

	@Test
	public void allow_arrow_as_message_body () {

		final QuakkRepository repository = context.mock(QuakkRepository.class);
		final Clock clock = context.mock(Clock.class);
		final Rule rule = new Posting(repository, clock);
		final DateTime time = new DateTime("22:30");

		context.checking(new Expectations() {{
			oneOf(repository).save(Quakk.QuakkBuilder.aNew("See link -> google.com").from(new User("user")).at(time)
					.build());
			oneOf(clock).now();
			will(returnValue(time));
		}});

		assertThat(rule.appliesTo("user -> See link -> google.com"), is(true));
		rule.apply("user -> See link -> google.com");

		context.assertIsSatisfied();
	}

}
