package com.codurance.test.quakker.application;

import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.InputOutput;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import com.codurance.test.quakker.core.rules.DefaultGatedRule;
import com.codurance.test.quakker.core.rules.Following;
import com.codurance.test.quakker.core.rules.Posting;
import com.codurance.test.quakker.core.rules.Reading;
import com.codurance.test.quakker.core.rules.Rules;
import com.codurance.test.quakker.core.rules.Wall;

public class QuakkerClient {

    private final Rules rules;

    public QuakkerClient (final QuakkRepository repository, final InputOutput output, final Clock clock) {
        this.rules = new Rules(
                new DefaultGatedRule(new Posting(repository, clock)),
                new DefaultGatedRule(new Following(repository)),
                new DefaultGatedRule(new Wall(repository, output, clock)),
                new DefaultGatedRule(new Reading(repository, output, clock))

        );
    }

    public void execute (final String commandRepresentation) {
        rules.applyTo(commandRepresentation);
    }

}
