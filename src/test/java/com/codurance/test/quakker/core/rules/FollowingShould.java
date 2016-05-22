package com.codurance.test.quakker.core.rules;

import com.codurance.test.quakker.core.domain.DateTime;
import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.domain.User;
import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FollowingShould {

	private Mockery context;

	@Before
	public void setUp () {
		context = new Mockery();
	}

	/**
	 * When the user does not exist
	 */
	@Test
	@Ignore
	public void allow_its_token_as_message_body () {

		final QuakkRepository repository = context.mock(QuakkRepository.class);
		final Clock clock = context.mock(Clock.class);
		final Rule rule = new Following(repository);
		final DateTime time = new DateTime("22:30");

		context.checking(new Expectations() {{
			oneOf(repository).save(Quakk.QuakkBuilder
					.aNew("A child follows the parents' steps")
					.from(new User("user"))
					.at(time)
					.build());
			oneOf(clock).now();
			will(returnValue(time));
		}});

		assertThat(rule.appliesTo("A child follows the parents' steps"), is(false)); //but this cannot happen as the
		// user does not exist

		context.assertIsSatisfied();
	}

	@Test
	@Ignore("Pending answer by product owner")
	public void allow_its_token_as_message_body_when_the_user_exists () {

		final QuakkRepository repository = context.mock(QuakkRepository.class);
		final Clock clock = context.mock(Clock.class);
		final Rule rule = new Following(repository);
		final DateTime time = new DateTime("22:30");

		context.checking(new Expectations() {{
			// need to quakk from a user called "A child"

			oneOf(repository).save(Quakk.QuakkBuilder
					.aNew("A child follows the parents' steps")
					.from(new User("user"))
					.at(time)
					.build());
			oneOf(clock).now();
			will(returnValue(time));
		}});

		assertThat(rule.appliesTo("A child follows the parents' steps"), is(true));
		//but this cannot happen as the user does not exist

		context.assertIsSatisfied();
	}


}
