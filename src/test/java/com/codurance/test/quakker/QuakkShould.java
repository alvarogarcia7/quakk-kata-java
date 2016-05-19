package com.codurance.test.quakker;

import com.codurance.test.quakker.domain.DateTime;
import com.codurance.test.quakker.domain.Quakk;
import com.codurance.test.quakker.domain.User;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class QuakkShould {

	private User ANY_USER = new User("any");

	@Test
	public void contain_the_message () {
		assertThat(Quakk.QuakkBuilder.aNew("message").from(ANY_USER).build(), is(Quakk.QuakkBuilder.aNew("message").from(ANY_USER).build()));
	}

	@Test
	public void contain_the_date () {
		assertThat(Quakk.QuakkBuilder.aNew("message").from(ANY_USER).at(new DateTime("16:30")).build(), is(Quakk.QuakkBuilder.aNew("message").from(ANY_USER).at(new DateTime("16:30")).build()));
	}

}
