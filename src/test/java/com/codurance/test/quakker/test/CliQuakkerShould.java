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

import java.util.Arrays;

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
			oneOf(repository).wall(user);
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
			oneOf(repository).wall(user); will(returnValue(userTimeline));
			ignoring(repository);

			oneOf(output).show(userTimeline);
		}});

		cli.execute("Charlie wall");

		context.assertIsSatisfied();
	}

	@Test
	public void subscribing_to_someone () {

		final User charlie = new User("Charlie");
		final User bob = new User("Bob");

		context.checking(new Expectations() {{
			oneOf(repository).follow(charlie, bob);
		}});

		cli.execute("Charlie follows Bob");

		context.assertIsSatisfied();
	}

	@Test
	public void when_subscribed_to_someone_the_timeline_is_showing_both_timelines () {

		final User charlie = new User("Charlie");
		final Timeline charlieTimeline = new Timeline(
				QuakkBuilder.aNew("First Quakk!").from(charlie).at(new DateTime("21:50")).build()
		);
		final User bob = new User("Bob");
		final Timeline bobTimeline = new Timeline(
				QuakkBuilder.aNew("Hello World").from(bob).at(new DateTime("20:50")).build()
		);

		final Timeline mergedTimeline = new Timeline(
				QuakkBuilder.aNew("Hello World").from(bob).at(new DateTime("20:50")).build(),
				QuakkBuilder.aNew("First Quakk!").from(charlie).at(new DateTime("21:50")).build()
		);

		context.checking(new Expectations() {{
			oneOf(repository).followedBy(charlie); will(returnValue(Arrays.asList(bob)));
			oneOf(repository).wall(bob); will(returnValue(bobTimeline));
			oneOf(repository).wall(charlie); will(returnValue(charlieTimeline));

			oneOf(output).show(mergedTimeline);
		}});

		cli.execute("Charlie wall");

		context.assertIsSatisfied();
	}
}
