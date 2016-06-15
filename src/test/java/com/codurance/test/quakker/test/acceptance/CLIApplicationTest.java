package com.codurance.test.quakker.test.acceptance;

import com.codurance.test.quakker.application.CLIQuakkerClient;
import com.codurance.test.quakker.core.ports.InputOutput;
import com.codurance.test.quakker.infrastructure.InMemoryQuakkRepository;
import com.codurance.test.quakker.core.domain.DateTime;
import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class CLIApplicationTest {

    private InputOutput inputOutput;
    private Mockery context;
    QuakkRepository repository;
    Clock clock;

    @Before
    public void setUp () {
        context = new Mockery();
        repository = new InMemoryQuakkRepository();
        inputOutput = context.mock(InputOutput.class);
        clock = context.mock(Clock.class);
    }

    @Test
    public void should_execute_two_commands_in_a_row() {

        context.checking(new Expectations() {{
            exactly(3).of(inputOutput).read(); will(onConsecutiveCalls(
                    returnValue("John -> first quakk!"),
                    returnValue("John wall"),
                    returnValue("Stop!")
            ));
            allowing(clock).now(); will(onConsecutiveCalls(
                    returnValue(new DateTime("21:28")),
                    returnValue(new DateTime("21:30"))));
            oneOf(inputOutput).print("John - first quakk! (2 minutes ago)");
        }});
        final CLIQuakkerClient cli = new CLIQuakkerClient(repository, inputOutput, clock, inputOutput);

        cli.run();

        context.assertIsSatisfied();
    }

    @Test
    public void should_execute_commands_until_finding_the_keyword() {

        context.checking(new Expectations() {{
            exactly(4).of(inputOutput).read(); will(onConsecutiveCalls(
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
            oneOf(inputOutput).print("John - first quakk! (2 minutes ago)");
            oneOf(inputOutput).print("John - first quakk! (3 minutes ago)");
        }});
        final CLIQuakkerClient cli = new CLIQuakkerClient(repository, inputOutput, clock, inputOutput);

        cli.run();

        context.assertIsSatisfied();
    }
}
