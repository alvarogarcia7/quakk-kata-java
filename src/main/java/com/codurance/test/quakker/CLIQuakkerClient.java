package com.codurance.test.quakker;

import com.codurance.test.quakker.application.QuakkerClient;
import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.Output;
import com.codurance.test.quakker.core.ports.QuakkRepository;

public class CLIQuakkerClient {

    private final QuakkerClient client;

    public CLIQuakkerClient (final QuakkRepository repository, final Output output, final Clock clock) {
        this.client = new QuakkerClient(repository, output, clock);
    }

    public void execute (final String commandRepresentation) {
        this.client.execute(commandRepresentation);
    }
}
