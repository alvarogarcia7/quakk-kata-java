package com.codurance.test.quakker.core.rules;

import com.codurance.test.quakker.core.Limitations;
import com.codurance.test.quakker.core.domain.User;
import com.codurance.test.quakker.core.ports.QuakkRepository;

import java.util.regex.Pattern;

public class Following implements Rule {
	public static final String TOKEN = " follows ";
	private final QuakkRepository repository;

	public Following (final QuakkRepository repository) {
		this.repository = repository;
	}

	@Override
	public void apply (final String representation) {
		final String[] values = representation.split(TOKEN);
		Limitations.userNamesCannotContainSpaces(representation);
		final User whoFollows = new User(values[0]);
		final User followingTo = new User(values[1]);
		repository.follow(whoFollows, followingTo);
	}

	@Override
	public boolean appliesTo (final String representation) {
		return containsToken(representation);
	}

	private boolean containsToken (final String representation) {
		final Pattern pattern = Pattern.compile("[\\w]+" + TOKEN + ".*");
		return pattern.matcher(representation).matches();
	}
}
