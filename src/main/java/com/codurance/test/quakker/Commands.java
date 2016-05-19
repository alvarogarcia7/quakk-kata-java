package com.codurance.test.quakker;

public class Commands {
	private final QuakkRepository repository;
	private final Output output;

	public Commands (final QuakkRepository repository, final Output output) {
		this.repository = repository;
		this.output = output;
	}

	public void applyFrom (final String representation) {
		if (representation.contains("->")) {
			repository.save(new QuakkCommandParser(representation).parse());
		} else {
			output.show(repository.list(new User(representation)));
		}
	}

	private class QuakkCommandParser {
		private final String representation;

		public QuakkCommandParser (final String representation) {
			this.representation = representation;
		}

		public Quakk parse () {
			final String[] parts = representation.split(" -> ");
			final User user = new User(parts[0]);
			final Quakk quakk = new Quakk(parts[1], user);
			return quakk;
		}

	}
}
