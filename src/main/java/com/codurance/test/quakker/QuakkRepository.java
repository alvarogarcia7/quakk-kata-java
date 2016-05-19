package com.codurance.test.quakker;

import java.util.List;

public interface QuakkRepository {
	void save (Quakk quakk);

	Timeline wall (User user);

	void follow (User whoSubscribes, User subscriptionTo);

	List<User> followedBy (User user);
}
