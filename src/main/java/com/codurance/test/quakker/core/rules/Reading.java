package com.codurance.test.quakker.core.rules;

import com.codurance.test.quakker.core.domain.User;
import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.Output;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import com.codurance.test.quakker.infrastructure.format.ReadingFormat;

public class Reading implements Rule {

	private final Output output;
	private final QuakkRepository repository;
	private Clock clock;

	public Reading (final QuakkRepository repository, final Output output, final Clock clock) {
		this.output = output;
		this.repository = repository;
		this.clock = clock;
	}

	@Override
	public void apply (final String representation) {
		repository.wall(new User(representation)).printAt(output, new ReadingFormat(clock));
	}

	@Override
	public boolean appliesTo (final String representation) {
		return true;
	}

}
