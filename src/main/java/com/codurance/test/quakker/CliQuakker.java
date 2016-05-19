package com.codurance.test.quakker;

public class CliQuakker {
	private final Commands commands;

	public CliQuakker (final QuakkRepository repository) {
		this(repository, Output.MISSING());
	}

	public CliQuakker (final QuakkRepository repository, final Output output) {
		commands = new Commands(repository, output);
	}

	public void execute (final String commandRepresentation) {
		commands.applyFrom(commandRepresentation);
	}

}
