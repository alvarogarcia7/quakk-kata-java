package com.codurance.test.quakker.infrastructure;

import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.domain.Timeline;
import com.codurance.test.quakker.core.domain.User;
import com.codurance.test.quakker.core.ports.QuakkRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryQuakkRepository implements QuakkRepository {

    List<Quakk> quakks;

    private Map<User, Set<User>> followersOf;

    public InMemoryQuakkRepository () {
        quakks = new ArrayList<>();
        followersOf = new HashMap<>();
    }



    @Override
    public void save (final Quakk quakk) {
        quakks.add(quakk);
    }

    @Override
    public Timeline wall (final User user) {
        final Predicate<Quakk> sameOwner = quakk -> quakk.owner().equals(user);
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
        final Set<User> followers = followersOf.getOrDefault(whoFollows, new HashSet<>());
        followers.add(followingTo);
        followersOf.put(whoFollows, followers);
    }

    @Override
    public List<User> followedBy (final User user) {
        final Set<User> users = followersOf.getOrDefault(user, new HashSet<>());
        final ArrayList<User> list = new ArrayList<>(users);
        return Collections.unmodifiableList(list);
    }
}
