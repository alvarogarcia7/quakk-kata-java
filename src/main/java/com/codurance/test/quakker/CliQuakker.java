package com.codurance.test.quakker;

public class CliQuakker {
	private final QuakkRepository repository;

	public CliQuakker (final QuakkRepository repository) {
		this.repository = repository;
	}

	public void execute (final String commandRepresentation) {
		commandQuakk(commandRepresentation);
	}

	private void commandQuakk (final String commandRepresentation) {
		new Commands(repository).applyFrom(commandRepresentation);
	}
}
