package com.codurance.test.quakker.core.ports;

public class InMemoryQuakkRepositoryShould extends QuakkRepositoryShould {
	@Override
	protected QuakkRepository implementation () {
		return new InMemoryQuakkRepository();
	}
}
