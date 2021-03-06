package com.codurance.test.quakker.test.unit.infrastructure;

import com.codurance.test.quakker.infrastructure.format.DateDifferenceFormatter;
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
    public void calculate_nonexisting_difference() {
        assertThat(differenceBetween("00:00:00", "00:00:00"), is("just now"));
    }

    @Test
    public void calculate_a_single_second_difference () {
        assertThat(differenceBetween("00:00:00", "00:00:01"), is("1 second ago"));
    }


    @Test
    public void calculate_a_difference_of_several_seconds () {
        assertThat(differenceBetween("00:00:00", "00:00:02"), is("2 seconds ago"));
    }


    @Test
    public void calculate_a_single_minute_difference () {
        assertThat(differenceBetween("00:00:00", "00:01:00"), is("1 minute ago"));
    }

    @Test
    public void calculate_a_difference_of_several_minutes () {
        assertThat(differenceBetween("00:00:00", "00:02:00"), is("2 minutes ago"));
    }


    @Test
    public void calculate_a_hour_second_difference () {
        assertThat(differenceBetween("00:00:00", "01:00:00"), is("1 hour ago"));
    }

    @Test
    public void calculate_a_difference_of_several_hours () {
        assertThat(differenceBetween("00:00:00", "02:00:00"), is("2 hours ago"));
    }

    @Test
    public void calculate_an_unknown_difference () {
        assertThat(differenceBetween("23:00:00", "02:00:00"), is("earlier"));
        assertThat(differenceBetween("23:59:59", "00:00:00"), is("earlier"));
        assertThat(differenceBetween("23:58:59", "00:00:00"), is("earlier"));
    }

    private String differenceBetween (final String startRepresentation, final String endRepresentation) {
        final DateTime start = new DateTime(startRepresentation);
        final DateTime end = new DateTime(endRepresentation);
        return dateDifferenceFormatter.difference(end.minus(start));
    }

}
