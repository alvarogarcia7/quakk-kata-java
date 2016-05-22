package com.codurance.test.quakker;

import com.codurance.test.quakker.application.QuakkerClient;
import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.Output;
import com.codurance.test.quakker.core.ports.QuakkRepository;

public class CLIQuakkerClient {

    private final QuakkerClient client;
    private final Input input;

    public CLIQuakkerClient (final QuakkRepository repository, final Output output, final Clock clock, final Input input) {
        this.input = input;
        this.client = new QuakkerClient(repository, output, clock);
    }

    private void execute (final String commandRepresentation) {
        this.client.execute(commandRepresentation);
    }

    public void run () {
        while (true) {
            final String command = input.read();
            if (command.equals("Stop!")) {
                break;
            }
            execute(command);
        }
    }
}
