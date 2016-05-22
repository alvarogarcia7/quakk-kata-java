package com.codurance.test.quakker;

import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.Output;
import com.codurance.test.quakker.core.ports.QuakkRepository;

public class CLIQuakkerClient {
    private final QuakkRepository repository;
    private final Output output;
    private final Clock clock;

    public CLIQuakkerClient (final QuakkRepository repository, final Output output, final Clock clock) {
        this.repository = repository;
        this.output = output;
        this.clock = clock;
    }

    public void execute (final String commandRepresentation) {

    }
}
