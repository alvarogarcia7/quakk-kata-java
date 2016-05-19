package com.codurance.test.quakker;

public class CliQuakker {
	private final QuakkRepository repository;

	public CliQuakker (final QuakkRepository repository) {
		this.repository = repository;
	}

	public void execute (final String command) {
		repository.save(new Quakk("I love the weather today", new User("Alice")));
	}
}
