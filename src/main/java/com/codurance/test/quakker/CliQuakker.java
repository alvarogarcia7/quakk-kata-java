package com.codurance.test.quakker;

import com.codurance.test.quakker.rules.Posting;
import com.codurance.test.quakker.rules.Reading;
import com.codurance.test.quakker.rules.Rules;
import com.codurance.test.quakker.rules.Subscribing;
import com.codurance.test.quakker.rules.Wall;

public class CliQuakker {

	private final Rules rules;

	public CliQuakker (final QuakkRepository repository, final Output output, final Clock clock) {
		this.rules = new Rules(
				new Posting(repository, clock),
				new Subscribing(repository),
				new Wall(repository, output),
				new Reading(output, repository, clock)
		);
	}

	public void execute (final String commandRepresentation) {
		rules.applyTo(commandRepresentation);
	}

}
