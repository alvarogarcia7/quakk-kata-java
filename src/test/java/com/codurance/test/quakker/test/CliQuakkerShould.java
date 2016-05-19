package com.codurance.test.quakker.test;

import com.codurance.test.quakker.CliQuakker;
import com.codurance.test.quakker.Output;
import com.codurance.test.quakker.Quakk;
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

	@Before
	public void setUp () {
		context = new Mockery();
		repository = context.mock(QuakkRepository.class);
		output = context.mock(Output.class);
		cli = new CliQuakker(repository, output);
	}

	@Test
	public void i_quakk_to_my_timeline () {

		context.checking(new Expectations() {{
			oneOf(repository).save(new Quakk("I love the weather today", new User("Alice")));
		}});

		cli.execute("Alice -> I love the weather today");

		context.assertIsSatisfied();
	}

	@Test
	public void review_someone_elses_timeline () {

		final User user = new User("Bob");
		final Timeline userTimeline = new Timeline(
				new Quakk("Good game though.", user),
				new Quakk("Damn! We lost!", user)
		);

		context.checking(new Expectations() {{
			oneOf(repository).list(user);
			will(returnValue(userTimeline));

			oneOf(output).show(userTimeline);
		}});

		cli.execute("Bob");

		context.assertIsSatisfied();
	}
}
