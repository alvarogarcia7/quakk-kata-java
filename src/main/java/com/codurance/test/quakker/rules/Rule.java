package com.codurance.test.quakker.rules;

public interface Rule {
	void apply (final String representation);

	boolean appliesTo (String representation);
}
