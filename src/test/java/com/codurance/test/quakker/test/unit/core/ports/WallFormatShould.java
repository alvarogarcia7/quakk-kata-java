package com.codurance.test.quakker.test.unit.core.ports;

import com.codurance.test.quakker.infrastructure.format.WallFormat;
import com.codurance.test.quakker.core.domain.DateTime;
import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.domain.User;
import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.Format;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WallFormatShould {

    private Mockery context;
    private Clock clock;
    private Format format;

    @Before
    public void setUp () {
        context = new Mockery();
        clock = context.mock(Clock.class);
    }

    @Test
    public void format_quakks () {

        format = new WallFormat(clock);

        context.checking(new Expectations() {{
            oneOf(clock).now(); will(returnValue(new DateTime("21:10")));
        }});

        final String formatted = format.format(Quakk.aNew().withMessage("message")
                .from(new User("user"))
                .at(new DateTime("21:00"))
                .build());

        assertThat(formatted, is("user - message (10 minutes ago)"));

        context.assertIsSatisfied();
    }
}
