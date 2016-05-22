package com.codurance.test.quakker.test.acceptance;

import com.codurance.test.quakker.CLIQuakkerClient;
import com.codurance.test.quakker.core.adapters.InMemoryQuakkRepository;
import com.codurance.test.quakker.core.domain.DateTime;
import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.Output;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class ApplicationTest {

    private Mockery context;
    QuakkRepository repository;
    Output output;
    Clock clock;

    @Before
    public void setUp () {
        context = new Mockery();
        repository = new InMemoryQuakkRepository();
        output = context.mock(Output.class);
        clock = context.mock(Clock.class);
    }

    @Test
    public void should_execute_a_command() {

        context.checking(new Expectations() {{
            allowing(clock).now(); will(onConsecutiveCalls(
                    returnValue(new DateTime("21:28")),
                    returnValue(new DateTime("21:30"))));
            oneOf(output).print("John - first quakk! (2 minutes ago)");
        }});

        final CLIQuakkerClient cli = new CLIQuakkerClient(repository, output, clock);
        cli.execute("John -> first quakk!");
        cli.execute("John wall");

        context.assertIsSatisfied();
    }
}
