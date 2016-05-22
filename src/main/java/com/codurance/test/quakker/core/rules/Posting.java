package com.codurance.test.quakker.core.rules;

import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import com.codurance.test.quakker.core.domain.User;

public class Posting implements Rule {

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
		QuakkMessageSplitter messageSplitter = new QuakkMessageSplitter(representation).invoke();
		final Quakk quakk = Quakk.QuakkBuilder
				.aNew(messageSplitter.getMessage())
				.from(messageSplitter.getUser())
				.at(clock.now()).build();
		return quakk;
	}

	@Override
	public boolean appliesTo (final String representation) {
		return representation.contains(KEYWORD_CREATION);
	}

	private class QuakkMessageSplitter {
		private final String representation;
		private User user;
		private String message;

		public QuakkMessageSplitter (final String representation) {
			this.representation = representation;
		}

		public User getUser () {
			return user;
		}

		public String getMessage () {
			return message;
		}

		public QuakkMessageSplitter invoke () {
			final String[] parts = splitByFirstToken();
			user = new User(parts[0]);
			message = parts[1];
			return this;
		}

		private String[] splitByFirstToken () {
			return representation.split(KEYWORD_CREATION, 2);
		}
	}
}
