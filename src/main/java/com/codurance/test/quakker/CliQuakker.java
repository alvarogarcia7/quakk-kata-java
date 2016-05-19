package com.codurance.test.quakker;

public class CliQuakker {

	private final Rules rules;

	public CliQuakker (final QuakkRepository repository, final Output output, final Clock clock) {
		this.rules = new Rules(
				new Posting(repository, clock),
				new Wall(repository, output),
				new Reading(output, repository, clock)
		);
	}

	public void execute (final String commandRepresentation) {
		applyFrom(commandRepresentation);
	}

	public void applyFrom (final String representation) {
		rules.applyTo(representation);
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

	public interface Rule {
		void apply (final String representation);

		boolean appliesTo (String representation);
	}

	private class Posting implements Rule {

		public static final String KEYWORD_CREATION = " -> ";
		private final QuakkRepository repository;
		private final Clock clock;

		public Posting (final QuakkRepository repository, final Clock clock) {

			this.repository = repository;
			this.clock = clock;
		}

		@Override
		public void apply (final String representation) {
			repository.save(parseQuack(representation));
		}

		private Quakk parseQuack (final String representation) {
			final String[] parts = representation.split(KEYWORD_CREATION);
			final User user = new User(parts[0]);
			final Quakk quakk = QuakkBuilder.aNew(parts[1]).from(user).at(clock.now()).build();
			return quakk;
		}

		@Override
		public boolean appliesTo (final String representation) {
			return representation.contains(KEYWORD_CREATION);
		}
	}

	private class Reading implements Rule {

		private final Output output;
		private final QuakkRepository repository;
		private final Clock clock;

		public Reading (final Output output, final QuakkRepository repository, final Clock clock) {
			this.output = output;
			this.repository = repository;
			this.clock = clock;
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
