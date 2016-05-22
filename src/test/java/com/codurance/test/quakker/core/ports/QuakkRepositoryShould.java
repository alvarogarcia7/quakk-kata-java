package com.codurance.test.quakker.core.ports;

import com.codurance.test.quakker.core.domain.DateTime;
import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.domain.Timeline;
import com.codurance.test.quakker.core.domain.User;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public abstract class QuakkRepositoryShould {

	private QuakkRepository repository;

	protected abstract QuakkRepository implementation ();

	@Test
	public void store_a_quakk () {
		repository = implementation();
		final User user = new User("user");
		final Quakk quakk = Quakk.QuakkBuilder.aNew("message").from(user).at(new DateTime("22:30")).build();

		repository.save(quakk);

		assertThat(repository.wall(user), is(new Timeline(quakk)));
	}


}
