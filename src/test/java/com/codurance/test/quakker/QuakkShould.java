package com.codurance.test.quakker;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class QuakkShould {

	private User ANY_USER = new User("any");

	@Test
	public void contain_the_message () {
		assertThat(QuakkBuilder.aNew("message").from(ANY_USER).createQuakk(), is(QuakkBuilder.aNew("message").from(ANY_USER).createQuakk()));
	}

	@Test
	public void contain_the_date () {
		assertThat(QuakkBuilder.aNew("message").from(ANY_USER).at(new DateTime("16:30")).createQuakk(), is(QuakkBuilder.aNew("message").from(ANY_USER).at(new DateTime("16:30")).createQuakk()));
	}

}