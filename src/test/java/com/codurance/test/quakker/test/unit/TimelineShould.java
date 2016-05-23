package com.codurance.test.quakker.test.unit;

import com.codurance.test.quakker.core.domain.DateTime;
import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.domain.Timeline;
import com.codurance.test.quakker.core.domain.User;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

public class TimelineShould {

	private static final User ANY_USER = new User("ANY");
	private final Quakk quakk1 = aQuakk("quakk1", "21:00");
	private final Quakk quakk2 = aQuakk("quakk2", "22:00");
	private final Quakk quakk3 = aQuakk("quakk3", "23:00");

	@Test
	public void merging_two_timelines_sorts_by_date () {
		assertThat(new Timeline(quakk1, quakk3).merge(new Timeline(quakk2)), is(new Timeline(quakk3, quakk2, quakk1)));
	}

	@Test
	public void the_timeline_is_sorted_by_date () {
		assertThat(new Timeline(quakk2, quakk1, quakk3), is(new Timeline(quakk3, quakk2, quakk1)));
	}

	@Test
	public void two_timelines_are_not_equal_based_on_the_quakks () {
		final Quakk a = aQuakk("message1", "22:00");
		final Quakk b = aQuakk("message2", "22:00");
		assertThat(new Timeline(a), is(not(new Timeline(a,b))));
	}


	private Quakk aQuakk (final String message, final String time) {
		return Quakk.QuakkBuilder
				.aNew(message)
				.from(ANY_USER)
				.at(new DateTime(time))
				.build();
	}

}
