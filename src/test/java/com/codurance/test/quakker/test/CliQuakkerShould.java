package com.codurance.test.quakker.test;

import com.codurance.test.quakker.CliQuakker;
import com.codurance.test.quakker.Quakk;
import com.codurance.test.quakker.QuakkRepository;
import com.codurance.test.quakker.User;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class CliQuakkerShould {

	private Mockery context;
	private CliQuakker cli;

	@Before
	public void setUp () {
		context = new Mockery();
	}

	@Test
	public void i_quakk_to_my_timeline () {

		final QuakkRepository repository = context.mock(QuakkRepository.class);
		cli = new CliQuakker(repository);
		context.checking(new Expectations() {{
			oneOf(repository).save(new Quakk("I love the weather today", new User("Alice")));
		}});

		cli.execute("Alice -> I love the weather today");

		context.assertIsSatisfied();
	}
}
