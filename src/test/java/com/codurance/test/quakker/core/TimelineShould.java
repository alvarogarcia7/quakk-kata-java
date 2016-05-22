package com.codurance.test.quakker.core;

import com.codurance.test.quakker.core.domain.DateTime;
import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.domain.Timeline;
import com.codurance.test.quakker.core.domain.User;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TimelineShould {

	private static final User ANY_USER = new User("ANY");

	@Test
	public void merging_two_timelines_sorts_by_date () {

		final Quakk quakk1 = aQuakk("quakk1", "21:00");
		final Quakk quakk2 = aQuakk("quakk2", "22:00");
		final Quakk quakk3 = aQuakk("quakk3", "23:00");

		assertThat(new Timeline(quakk1, quakk3).merge(new Timeline(quakk2)), is(new Timeline(quakk1, quakk2, quakk3)));
	}

	@Test
	public void the_timeline_is_sorted_by_date () {

		final Quakk quakk1 = aQuakk("quakk1", "21:00");
		final Quakk quakk2 = aQuakk("quakk2", "22:00");
		final Quakk quakk3 = aQuakk("quakk3", "23:00");

		assertThat(new Timeline(quakk2, quakk1, quakk3), is(new Timeline(quakk1, quakk2, quakk3)));
	}


	private Quakk aQuakk (final String message, final String time) {
		return Quakk.QuakkBuilder.aNew(message).from(ANY_USER).at(new DateTime(time)).build();
	}

}
