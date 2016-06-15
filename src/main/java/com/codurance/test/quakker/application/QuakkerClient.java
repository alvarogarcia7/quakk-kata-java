package com.codurance.test.quakker.application;

import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.InputOutput;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import com.codurance.test.quakker.core.rules.Following;
import com.codurance.test.quakker.core.rules.GatedRule;
import com.codurance.test.quakker.core.rules.Posting;
import com.codurance.test.quakker.core.rules.Reading;
import com.codurance.test.quakker.core.rules.Rules;
import com.codurance.test.quakker.core.rules.Wall;

public class QuakkerClient {

    private final Rules rules;

    public QuakkerClient (final QuakkRepository repository, final InputOutput output, final Clock clock) {
        this.rules = new Rules(
                new GatedRule(new Posting(repository, clock)),
                new GatedRule(new Following(repository)),
                new GatedRule(new Wall(repository, output, clock)),
                new GatedRule(new Reading(repository, output, clock))

        );
    }

    public void execute (final String commandRepresentation) {
        rules.applyTo(commandRepresentation);
    }

}
