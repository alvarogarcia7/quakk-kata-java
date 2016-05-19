package com.codurance.test.quakker;

public class Commands {
	private final Rules rules;

	public Commands (final QuakkRepository repository, final Output output) {
		this.rules = new Rules(new Posting(repository), new Reading(output, repository));
	}

	public void applyFrom (final String representation) {
		rules.applyTo(representation);
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

	private interface Rule {
		void apply (final String representation);

		boolean appliesTo (String representation);
	}

	private class Posting implements Rule {

		private final QuakkRepository repository;

		public Posting (final QuakkRepository repository) {

			this.repository = repository;
		}

		@Override
		public void apply (final String representation) {
			repository.save(new QuakkCommandParser(representation).parse());
		}

		@Override
		public boolean appliesTo (final String representation) {
			return representation.contains("->");
		}
	}

	private class Reading implements Rule {

		private final Output output;
		private final QuakkRepository repository;

		public Reading (final Output output, final QuakkRepository repository) {
			this.output = output;
			this.repository = repository;
		}

		@Override
		public void apply (final String representation) {
			output.show(repository.list(new User(representation)));
		}

		@Override
		public boolean appliesTo (final String representation) {
			return true;
		}

	}
}
