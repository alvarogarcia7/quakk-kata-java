package com.codurance.test.quakker;

public class Commands {
	private final QuakkRepository repository;

	public Commands (final QuakkRepository repository) {
		this.repository = repository;
	}

	public void applyFrom (final String representation) {
		repository.save(new QuakkCommandParser(representation).parse());
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
