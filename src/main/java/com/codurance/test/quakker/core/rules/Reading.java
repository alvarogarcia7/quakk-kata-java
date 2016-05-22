package com.codurance.test.quakker.core.rules;

import com.codurance.test.quakker.core.domain.User;
import com.codurance.test.quakker.core.ports.Output;
import com.codurance.test.quakker.core.ports.QuakkRepository;

public class Reading implements Rule {

	private final Output output;
	private final QuakkRepository repository;

	public Reading (final Output output, final QuakkRepository repository) {
		this.output = output;
		this.repository = repository;
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
