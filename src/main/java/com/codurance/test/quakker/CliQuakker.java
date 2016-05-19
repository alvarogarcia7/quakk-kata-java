package com.codurance.test.quakker;

public class CliQuakker {

	private final Rules rules;

	public CliQuakker (final QuakkRepository repository, final Output output, final Clock clock) {
		this.rules = new Rules(
				new Posting(repository, clock),
				new Subscribing(repository),
				new Wall(repository, output),
				new Reading(output, repository, clock)
		);
	}

	public void execute (final String commandRepresentation) {
		rules.applyTo(commandRepresentation);
	}

	private class Rules {
		private final Rule[] rules;

		public Rules (final Rule... rules) {
			this.rules = rules;
		}

		private void applyTo (final String representation) {
			for (Rule rule : rules) {
				if (rule.appliesTo(representation)) {
					rule.apply(representation);
					break;
				}
			}
		}
	}

}
