package com.codurance.test.quakker.test.acceptance;

import com.codurance.test.quakker.application.QuakkerClient;
import com.codurance.test.quakker.core.domain.DateTime;
import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.domain.Timeline;
import com.codurance.test.quakker.core.domain.User;
import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.InputOutput;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class QuakkerClientShould {

    private Mockery context;
    private QuakkerClient cli;
    private QuakkRepository repository;
    private InputOutput output;
    private Clock clock;

    @Before
    public void setUp () {
        context = new Mockery();
        repository = context.mock(QuakkRepository.class);
        output = context.mock(InputOutput.class);
        clock = context.mock(Clock.class);
        cli = new QuakkerClient(repository, output, clock);
    }

    @Test
    public void i_quakk_to_my_timeline () {

        context.checking(new Expectations() {{
            oneOf(clock).now(); will(returnValue(new DateTime("22:30")));
            oneOf(repository).save(Quakk.aNew().withMessage("I love the weather today")
                    .from(new User("Alice"))
                    .at(new DateTime("22:30"))
                    .build());
        }});

        cli.execute("Alice -> I love the weather today");

        context.assertIsSatisfied();
    }

    @Test
    public void review_someone_elses_timeline () {

        final User user = new User("Bob");
        final DateTime time = new DateTime("23:00");
        final Timeline userTimeline = new Timeline(
                Quakk.aNew().withMessage("Good game though.").from(user).at(time).build(),
                Quakk.aNew().withMessage("Damn! We lost!").from(user).at(time).build()
        );

        context.checking(new Expectations() {{
            allowing(clock).now(); will(returnValue(new DateTime("23:15")));
            oneOf(repository).wall(user);
            will(returnValue(userTimeline));

            oneOf(output).print("Good game though. (15 minutes ago)");
            oneOf(output).print("Damn! We lost! (15 minutes ago)");
        }});

        cli.execute("Bob");

        context.assertIsSatisfied();
    }

    @Test
    public void review_my_timeline () {

        final User user = new User("Charlie");
        final DateTime time = new DateTime("23:00");
        final Timeline userTimeline = new Timeline(
                Quakk.aNew().withMessage("Good game though.").from(user).at(time).build(),
                Quakk.aNew().withMessage("Damn! We lost!").from(user).at(time).build()
        );

        context.checking(new Expectations() {{
            allowing(clock).now(); will(returnValue(new DateTime("23:15")));
            oneOf(repository).wall(user); will(returnValue(userTimeline));
            ignoring(repository);

            oneOf(output).print("Charlie - Good game though. (15 minutes ago)");
            oneOf(output).print("Charlie - Damn! We lost! (15 minutes ago)");
        }});

        cli.execute("Charlie wall");

        context.assertIsSatisfied();
    }

    @Test
    public void subscribing_to_someone () {

        final User charlie = new User("Charlie");
        final User bob = new User("Bob");

        context.checking(new Expectations() {{
            oneOf(repository).follow(charlie, bob);
        }});

        cli.execute("Charlie follows Bob");

        context.assertIsSatisfied();
    }

    @Test
    public void when_subscribed_to_someone_the_timeline_is_showing_both_timelines () {

        final User charlie = new User("Charlie");
        final Timeline charlieTimeline = new Timeline(
                Quakk.aNew().withMessage("First Quakk!").from(charlie).at(new DateTime("21:50")).build()
        );
        final User bob = new User("Bob");
        final Timeline bobTimeline = new Timeline(
                Quakk.aNew().withMessage("Hello World").from(bob).at(new DateTime("20:50")).build()
        );

        context.checking(new Expectations() {{
            allowing(clock).now(); will(returnValue(new DateTime("22:30")));
            oneOf(repository).followedBy(charlie); will(returnValue(Arrays.asList(bob)));
            oneOf(repository).wall(bob); will(returnValue(bobTimeline));
            oneOf(repository).wall(charlie); will(returnValue(charlieTimeline));

            oneOf(output).print("Bob - Hello World (1 hour ago)");
            oneOf(output).print("Charlie - First Quakk! (40 minutes ago)");
        }});

        cli.execute("Charlie wall");

        context.assertIsSatisfied();
    }
}
