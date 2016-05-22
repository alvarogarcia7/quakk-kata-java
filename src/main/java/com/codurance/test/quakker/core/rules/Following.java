package com.codurance.test.quakker.core.rules;

import com.codurance.test.quakker.core.ports.QuakkRepository;
import com.codurance.test.quakker.core.domain.User;

public class Following implements Rule {
	public static final String TOKEN = " follows ";
	private final QuakkRepository repository;

	public Following (final QuakkRepository repository) {
		this.repository = repository;
	}

	@Override
	public void apply (final String representation) {
		final String[] values = representation.split(TOKEN);
		final User whoFollows = new User(values[0]);
		final User followingTo = new User(values[1]);
		repository.follow(whoFollows, followingTo);
	}

	@Override
	public boolean appliesTo (final String representation) {
		return representation.contains(TOKEN);
	}
}
