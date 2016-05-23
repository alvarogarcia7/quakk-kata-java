package com.codurance.test.quakker.test.unit;

import com.codurance.test.quakker.core.KnownLimitations;
import com.codurance.test.quakker.core.domain.User;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import com.codurance.test.quakker.core.rules.Following;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Ignore;
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


		assertThat(new Following(repository).appliesTo("John -> Your shadow is your best mate: it follows you " +
				"wherever you go."), is(false));

		context.assertIsSatisfied();
	}


	@Test
	public void can_follow_a_user_named_as_the_keyword () {
		context.checking(new Expectations() {{
			oneOf(repository).follow(new User("John"), new User("follows"));
		}});

		new Following(repository).apply("John follows follows");

		context.assertIsSatisfied();
	}

	@Test
	@Ignore("the product owner has discarded this feature, therefore becoming a known limitation")
	public void cannot_follow_a_user_with_spaces_in_the_username () {
		final String representation = "John follows follows Michael follows";
		KnownLimitations.userNamesCannotContainSpaces(representation);
		new Following(repository).apply(representation);
	}
}
