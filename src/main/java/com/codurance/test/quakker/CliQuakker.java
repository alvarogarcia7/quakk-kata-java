package com.codurance.test.quakker;

public class CliQuakker {
	private final Commands commands;

	public CliQuakker (final QuakkRepository repository) {
		commands = new Commands(repository);
	}

	public void execute (final String commandRepresentation) {
		commands.applyFrom(commandRepresentation);
	}

}
