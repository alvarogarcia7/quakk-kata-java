package com.codurance.test.quakker;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class QuakkShould {

	private User ANY_USER = new User("any");

	@Test
	public void contain_the_message () {
		assertThat(new Quakk("message", ANY_USER), is(new Quakk("message", ANY_USER)));
	}

	@Test
	public void contain_the_date () {
		assertThat(new Quakk("message", ANY_USER, new DateTime("16:30")), is(new Quakk("message", ANY_USER, new DateTime("16:30"))));
	}

}
