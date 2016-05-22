package com.codurance.test.quakker.core.ports;

import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.domain.Timeline;
import com.codurance.test.quakker.core.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryQuakkRepository implements QuakkRepository {

	List<Quakk> quakks = new ArrayList<>();

	@Override
	public void save (final Quakk quakk) {
		quakks.add(quakk);
	}

	@Override
	public Timeline wall (final User user) {
		final Predicate<Quakk> sameOwner = x -> x.owner().equals(user);
		final List<Quakk> matchingQuakks = filterBy(sameOwner);
		return toTimeline(matchingQuakks);
	}

	private List<Quakk> filterBy (final Predicate<Quakk> sameOwner) {
		return quakks.stream().filter(sameOwner).collect(Collectors.toList());
	}

	private Timeline toTimeline (final List<Quakk> quakks) {
		return new Timeline(quakks.toArray(new Quakk[0]));
	}

	@Override
	public void follow (final User whoFollows, final User followingTo) {

	}

	@Override
	public List<User> followedBy (final User user) {
		return null;
	}
}
