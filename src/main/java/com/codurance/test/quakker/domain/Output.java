package com.codurance.test.quakker.domain;

public interface Output {
	void show (Timeline timeline);

	static Output MISSING () {
		return null;
	}
}
