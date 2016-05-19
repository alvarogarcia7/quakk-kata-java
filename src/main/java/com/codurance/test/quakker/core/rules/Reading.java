package com.codurance.test.quakker.core.rules;

import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.Output;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import com.codurance.test.quakker.core.User;

public class Reading implements Rule {

	private final Output output;
	private final QuakkRepository repository;
	private final Clock clock;

	public Reading (final Output output, final QuakkRepository repository, final Clock clock) {
		this.output = output;
		this.repository = repository;
		this.clock = clock;
	}

	@Override
	public void apply (final String representation) {
		output.show(repository.wall(new User(representation)));
	}

	@Override
	public boolean appliesTo (final String representation) {
		return true;
	}

}
