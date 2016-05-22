package com.codurance.test.quakker.core.rules;

import com.codurance.test.quakker.core.ports.QuakkRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FollowingShould {

	private Mockery context;
	private QuakkRepository repository;

	@Before
	public void setUp () {
		context = new Mockery();
		repository = context.mock(QuakkRepository.class);
	}


	@Test
	public void do_not_detect_the_keyword_in_the_middle () {

		context.checking(new Expectations() {{

		}});

		assertThat(new Following(repository).appliesTo("John -> Your shadow is your best mate: it follows you " +
				"wherever you go."), is(false));

		context.assertIsSatisfied();
	}
}
