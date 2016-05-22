package com.codurance.test.quakker.core.ports;

import com.codurance.test.quakker.core.adapters.InMemoryQuakkRepository;

public class InMemoryQuakkRepositoryShould extends QuakkRepositoryShould {
	@Override
	protected QuakkRepository implementation () {
		return new InMemoryQuakkRepository();
	}
}
