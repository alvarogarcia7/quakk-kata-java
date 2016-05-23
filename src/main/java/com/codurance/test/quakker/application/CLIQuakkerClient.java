package com.codurance.test.quakker.application;

import com.codurance.test.quakker.core.adapters.ConsoleInput;
import com.codurance.test.quakker.core.ports.Input;
import com.codurance.test.quakker.core.adapters.ConsoleOutput;
import com.codurance.test.quakker.core.adapters.InMemoryQuakkRepository;
import com.codurance.test.quakker.core.adapters.SystemClock;
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
            if (shouldStop(command)) {
                break;
            }
            execute(command);
        }
    }

    private boolean shouldStop (final String command) {
        return command.equals("Stop!");
    }

    public static void main (String[] args) {
        new CLIQuakkerClient(
                new InMemoryQuakkRepository(),
                new ConsoleOutput(System.out),
                new SystemClock(),
                new ConsoleInput(System.in)).run();
    }
}
