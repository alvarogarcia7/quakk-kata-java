package com.codurance.test.quakker;

class Posting implements Rule {

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
