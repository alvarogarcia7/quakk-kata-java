package com.codurance.test.quakker.test.unit;

import com.codurance.test.quakker.infrastructure.InMemoryQuakkRepository;
import com.codurance.test.quakker.core.ports.QuakkRepository;

public class InMemoryQuakkRepositoryShould extends QuakkRepositoryShould {
	@Override
	protected QuakkRepository implementation () {
		return new InMemoryQuakkRepository();
	}
}
