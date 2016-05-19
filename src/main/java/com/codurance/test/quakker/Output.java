package com.codurance.test.quakker;

public interface Output {
	void show (Timeline timeline);

	static Output MISSING () {
		return null;
	}
}
