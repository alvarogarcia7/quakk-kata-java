package com.codurance.test.quakker.core;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TimelineShould {

	private static final User ANY_USER = new User("ANY");

	@Test
	public void merging_two_timelines_sorts_by_date () {

		final Quakk quakk1 = Quakk.QuakkBuilder.aNew("quakk1").from(ANY_USER).at(new DateTime("21:00")).build();
		final Quakk quakk2 = Quakk.QuakkBuilder.aNew("quakk2").from(ANY_USER).at(new DateTime("22:00")).build();
		final Quakk quakk3 = Quakk.QuakkBuilder.aNew("quakk3").from(ANY_USER).at(new DateTime("22:00")).build();

		assertThat(new Timeline(quakk1, quakk3).merge(new Timeline(quakk2)), is(new Timeline(quakk1, quakk2, quakk3)));
	}

}
