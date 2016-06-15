package com.codurance.test.quakker.core.ports;

import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.domain.Timeline;
import com.codurance.test.quakker.core.domain.User;

import java.util.List;

public interface QuakkRepository {
    void save (Quakk quakk);

    Timeline wall (User user);

    void follow (User whoFollows, User followingTo);

    List<User> followedBy (User user);
}
