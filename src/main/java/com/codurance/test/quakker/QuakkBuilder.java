package com.codurance.test.quakker;

public class QuakkBuilder {
	private String message;
	private User owner;
	private DateTime dateTime = DateTime.now();

	private QuakkBuilder (final String message) {
		this.message = message;
	}

	public static QuakkBuilder aNew (final String message) {
		final QuakkBuilder builder = new QuakkBuilder(message);
		return builder;
	}

	public QuakkBuilder from (final User owner) {
		this.owner = owner;
		return this;
	}

	public QuakkBuilder at (final DateTime dateTime) {
		this.dateTime = dateTime;
		return this;
	}

	public Quakk createQuakk () {
		return new Quakk(message, owner, dateTime);
	}
}
