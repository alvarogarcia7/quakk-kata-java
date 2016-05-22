package com.codurance.test.quakker.test.acceptance;

import com.codurance.test.quakker.CLIQuakkerClient;
import com.codurance.test.quakker.Input;
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

    private Input input;
    private Mockery context;
    QuakkRepository repository;
    Output output;
    Clock clock;

    @Before
    public void setUp () {
        context = new Mockery();
        repository = new InMemoryQuakkRepository();
        output = context.mock(Output.class);
        input = context.mock(Input.class);
        clock = context.mock(Clock.class);
    }

    @Test
    public void should_execute_two_commands_in_a_row() {

        context.checking(new Expectations() {{
            exactly(2).of(input).read(); will(onConsecutiveCalls(
                    returnValue("John -> first quakk!"),
                    returnValue("John wall")
            ));
            allowing(clock).now(); will(onConsecutiveCalls(
                    returnValue(new DateTime("21:28")),
                    returnValue(new DateTime("21:30"))));
            oneOf(output).print("John - first quakk! (2 minutes ago)");
        }});
        final CLIQuakkerClient cli = new CLIQuakkerClient(repository, output, clock, input);

        cli.run();

        context.assertIsSatisfied();
    }

    @Test
    public void should_execute_commands_until_finding_the_keyword() {

        context.checking(new Expectations() {{
            exactly(2).of(input).read(); will(onConsecutiveCalls(
                    returnValue("John -> first quakk!"),
                    returnValue("John wall"),
                    returnValue("John wall"),
                    returnValue("Stop!")
            ));
            allowing(clock).now(); will(onConsecutiveCalls(
                    returnValue(new DateTime("21:28")),
                    returnValue(new DateTime("21:30")),
                    returnValue(new DateTime("21:31"))
                    ));
            oneOf(output).print("John - first quakk! (2 minutes ago)");
            oneOf(output).print("John - first quakk! (3 minutes ago)");
        }});
        final CLIQuakkerClient cli = new CLIQuakkerClient(repository, output, clock, input);

        cli.run();

        context.assertIsSatisfied();
    }
}
