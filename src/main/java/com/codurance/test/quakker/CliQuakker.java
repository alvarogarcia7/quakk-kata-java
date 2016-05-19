package com.codurance.test.quakker;

public class CliQuakker {
	private final Commands commands;

	public CliQuakker (final QuakkRepository repository, final Output output, final Clock clock) {
		commands = new Commands(repository, output, clock);
	}

	public void execute (final String commandRepresentation) {
		commands.applyFrom(commandRepresentation);
	}

}
