package com.codurance.test.quakker;

import java.util.List;

public class Wall implements Rule {
	private final QuakkRepository repository;
	private final Output output;

	public Wall (final QuakkRepository repository, final Output output) {
		this.repository = repository;
		this.output = output;
	}

	@Override
	public void apply (final String representation) {
		final User user = parseUser(representation);
		Timeline currentWall = repository.wall(user);
		final List<User> currentFollowers = repository.followedBy(user);
		for (User currentFollower : currentFollowers) {
			currentWall = currentWall.merge(repository.wall(currentFollower));
		}

		output.show(currentWall);
	}

	private User parseUser (final String representation) {
		return new User(representation.split(" wall")[0]);
	}

	@Override
	public boolean appliesTo (final String representation) {
		return representation.endsWith(" wall");
	}
}
