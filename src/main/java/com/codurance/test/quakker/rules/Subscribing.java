package com.codurance.test.quakker.rules;

import com.codurance.test.quakker.domain.QuakkRepository;
import com.codurance.test.quakker.domain.User;

public class Subscribing implements Rule {
	private final QuakkRepository repository;

	public Subscribing (final QuakkRepository repository) {
		this.repository = repository;
	}

	@Override
	public void apply (final String representation) {
		final String[] values = representation.split(" follows ");
		final User whoSubscribes = new User(values[0]);
		final User subscriptionTo = new User(values[1]);
		repository.follow(whoSubscribes, subscriptionTo);
	}

	@Override
	public boolean appliesTo (final String representation) {
		return representation.contains(" follows ");
	}
}
