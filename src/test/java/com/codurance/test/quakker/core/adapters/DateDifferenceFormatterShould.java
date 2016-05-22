package com.codurance.test.quakker.core.adapters;

import com.codurance.test.quakker.core.domain.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DateDifferenceFormatterShould {


	private DateDifferenceFormatter dateDifferenceFormatter;

	@Before
	public void setUp () throws Exception {
		dateDifferenceFormatter = new DateDifferenceFormatter();
	}

	@Test
	public void calculate_a_single_second_difference () {
		final DateTime start = new DateTime("00:00:00");
		final DateTime end = new DateTime("00:00:01");

		assertThat(dateDifferenceFormatter.difference(start, end), is("1 second ago"));
	}
}
