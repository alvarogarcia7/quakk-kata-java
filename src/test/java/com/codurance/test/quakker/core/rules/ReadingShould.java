package com.codurance.test.quakker.core.rules;

import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.InputOutput;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReadingShould {

    private Mockery context;
    private QuakkRepository repository;
    private InputOutput io;
    private Clock clock;
    private Reading reading;

    @Before
    public void setUp () {
        context = new Mockery();
        repository = context.mock(QuakkRepository.class);
        io = context.mock(InputOutput.class);
        clock = context.mock(Clock.class);
        reading = new Reading(repository, io, clock);
    }

    @Test
    public void do_not_apply_when_there_is_a_another_command () {

        context.checking(new Expectations() {{
        }});

        assertThat(reading.appliesTo("john wall"), is(false));

        context.assertIsSatisfied();
    }

    @Test
    public void apply_when_there_is_a_username_only () {

        context.checking(new Expectations() {{
        }});

        assertThat(reading.appliesTo("john"), is(true));

        context.assertIsSatisfied();
    }

}
