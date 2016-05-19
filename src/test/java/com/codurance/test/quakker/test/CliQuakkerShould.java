package com.codurance.test.quakker.test;

import com.codurance.test.quakker.CliQuakker;
import com.codurance.test.quakker.Clock;
import com.codurance.test.quakker.DateTime;
import com.codurance.test.quakker.Output;
import com.codurance.test.quakker.QuakkBuilder;
import com.codurance.test.quakker.QuakkRepository;
import com.codurance.test.quakker.Timeline;
import com.codurance.test.quakker.User;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class CliQuakkerShould {

	private Mockery context;
	private CliQuakker cli;
	private QuakkRepository repository;
	private Output output;
	private Clock clock;

	@Before
	public void setUp () {
		context = new Mockery();
		repository = context.mock(QuakkRepository.class);
		output = context.mock(Output.class);
		clock = context.mock(Clock.class);
		cli = new CliQuakker(repository, output, clock);
	}

	@Test
	public void i_quakk_to_my_timeline () {

		context.checking(new Expectations() {{
			oneOf(clock).now(); will(returnValue(new DateTime("22:30")));
			oneOf(repository).save(QuakkBuilder
					.aNew("I love the weather today")
					.from(new User("Alice"))
					.at(new DateTime("22:30"))
					.build());
		}});

		cli.execute("Alice -> I love the weather today");

		context.assertIsSatisfied();
	}

	@Test
	public void review_someone_elses_timeline () {

		final User user = new User("Bob");
		final Timeline userTimeline = new Timeline(
				QuakkBuilder.aNew("Good game though.").from(user).build(),
				QuakkBuilder.aNew("Damn! We lost!").from(user).build()
		);

		context.checking(new Expectations() {{
			oneOf(repository).list(user);
			will(returnValue(userTimeline));

			oneOf(output).show(userTimeline);
		}});

		cli.execute("Bob");

		context.assertIsSatisfied();
	}

	@Test
	public void review_my_timeline () {

		final User user = new User("Charlie");
		final Timeline userTimeline = new Timeline(
				QuakkBuilder.aNew("Good game though.").from(user).build(),
				QuakkBuilder.aNew("Damn! We lost!").from(user).build()
		);

		context.checking(new Expectations() {{
			oneOf(repository).list(user); will(returnValue(userTimeline));

			oneOf(output).show(userTimeline);
		}});

		cli.execute("Charlie wall");

		context.assertIsSatisfied();
	}
}
