package com.codurance.test.quakker.test.unit.infrastructure;

import com.codurance.test.quakker.infrastructure.InMemoryQuakkRepository;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import com.codurance.test.quakker.test.unit.core.ports.QuakkRepositoryShould;

public class InMemoryQuakkRepositoryShould extends QuakkRepositoryShould {
    @Override
    protected QuakkRepository implementation () {
        return new InMemoryQuakkRepository();
    }
}
