package com.codurance.test.quakker.test.unit;

import com.codurance.test.quakker.core.domain.DateTime;
import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DateTimeShould {

    @Test
    public void parse_dates_with_seconds () {
        assertThat(new DateTime("21:30:00").value(), is(LocalTime.of(21, 30, 00)));
    }

    @Test
    public void parse_dates_without_seconds () {
        assertThat(new DateTime("21:31").value(), is(LocalTime.of(21, 31)));
    }

}
