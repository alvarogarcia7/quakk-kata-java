package com.codurance.test.quakker.core.rules;

import com.codurance.test.quakker.core.ports.Output;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import com.codurance.test.quakker.core.domain.Timeline;
import com.codurance.test.quakker.core.domain.User;

import java.util.List;

public class Wall implements Rule {
	public static final String TOKEN = " wall";
	private final QuakkRepository repository;
	private final Output output;

	public Wall (final QuakkRepository repository, final Output output) {
		this.repository = repository;
		this.output = output;
	}

	@Override
	public void apply (final String representation) {
		final User currentUser = parseUser(representation);
		Timeline currentWall = repository.wall(currentUser);
		final List<User> currentFollowers = repository.followedBy(currentUser);
		for (User currentFollower : currentFollowers) {
			currentWall = currentWall.merge(repository.wall(currentFollower));
		}

		output.show(currentWall);
	}

	private User parseUser (final String representation) {
		return new User(representation.split(TOKEN)[0]);
	}

	@Override
	public boolean appliesTo (final String representation) {
		return representation.endsWith(TOKEN);
	}
}
