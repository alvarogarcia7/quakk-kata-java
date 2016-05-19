package com.codurance.test.quakker;

public class Commands {
	private final QuakkRepository repository;

	public Commands (final QuakkRepository repository) {
		this.repository = repository;
	}

	public void applyFrom (final String representation) {
		repository.save(new Quakk("I love the weather today", new User("Alice")));;
	}
}
