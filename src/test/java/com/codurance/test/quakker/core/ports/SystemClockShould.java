package com.codurance.test.quakker.core.ports;

import com.codurance.test.quakker.core.adapters.SystemClock;
import com.codurance.test.quakker.core.domain.DateTime;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SystemClockShould {

	@Test
	public void has_second_precision () {
		final DateTime fromSystem = new DateTime(new SimpleDateFormat("HH:mm:ss").format(Date.from(Instant.now())));
		final DateTime sut = new SystemClock().now();

		assertThat(sut.value().minus(1, ChronoUnit.SECONDS).isBefore(fromSystem.value()), is(true));
		assertThat(sut.value().plus(1, ChronoUnit.SECONDS).isAfter(fromSystem.value()), is(true));
	}


}
