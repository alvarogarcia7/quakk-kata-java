package com.codurance.test.quakker;

public class Wall implements CliQuakker.Rule {
	private final QuakkRepository repository;
	private final Output output;

	public Wall (final QuakkRepository repository, final Output output) {
		this.repository = repository;
		this.output = output;
	}

	@Override
	public void apply (final String representation) {
		output.show(repository.list(parseUser(representation)));
	}

	private User parseUser (final String representation) {
		return new User(representation.split(" wall")[0]);
	}

	@Override
	public boolean appliesTo (final String representation) {
		return representation.endsWith(" wall");
	}
}
