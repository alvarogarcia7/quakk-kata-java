package com.codurance.test.quakker.core.ports;

import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.domain.Timeline;
import com.codurance.test.quakker.core.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryQuakkRepository implements QuakkRepository {

	List<Quakk> quakks = new ArrayList<>();

	@Override
	public void save (final Quakk quakk) {
		quakks.add(quakk);
	}

	@Override
	public Timeline wall (final User user) {
		return new Timeline(quakks.stream().filter(x->x.owner().equals(user)).collect(Collectors.toList()).toArray(new
				Quakk[0]));
	}

	@Override
	public void follow (final User whoFollows, final User followingTo) {

	}

	@Override
	public List<User> followedBy (final User user) {
		return null;
	}
}
