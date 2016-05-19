package com.codurance.test.quakker.core.ports;

import com.codurance.test.quakker.core.Quakk;
import com.codurance.test.quakker.core.Timeline;
import com.codurance.test.quakker.core.User;

import java.util.List;

public interface QuakkRepository {
	void save (Quakk quakk);

	Timeline wall (User user);

	void follow (User whoSubscribes, User subscriptionTo);

	List<User> followedBy (User user);
}