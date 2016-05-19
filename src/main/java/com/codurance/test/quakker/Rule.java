package com.codurance.test.quakker;

public interface Rule {
	void apply (final String representation);

	boolean appliesTo (String representation);
}
