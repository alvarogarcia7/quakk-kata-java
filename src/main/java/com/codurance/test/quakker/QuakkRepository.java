package com.codurance.test.quakker;

public interface QuakkRepository {
	void save (Quakk quakk);

	Timeline list (User user);
}