package com.codurance.test.quakker.application;

import com.codurance.test.quakker.core.ports.InputOutput;
import com.codurance.test.quakker.infrastructure.console.ConsoleInputOutput;
import com.codurance.test.quakker.infrastructure.InMemoryQuakkRepository;
import com.codurance.test.quakker.infrastructure.SystemClock;
import com.codurance.test.quakker.core.ports.Clock;
import com.codurance.test.quakker.core.ports.QuakkRepository;

public class CLIQuakkerClient {

    private final QuakkerClient client;
    private final InputOutput inputOutput;

    public CLIQuakkerClient (final QuakkRepository repository, final InputOutput output, final Clock clock, final InputOutput inputOutput) {
        this.inputOutput = inputOutput;
        this.client = new QuakkerClient(repository, output, clock);
    }

    public void run () {
        while (true) {
            final String command = inputOutput.read();
            if (shouldStop(command)) {
                break;
            }
            execute(command);
        }
    }

    public static void main (String[] args) {
        new CLIQuakkerClient(
                new InMemoryQuakkRepository(),
                new ConsoleInputOutput(System.in, System.out),
                new SystemClock(),
                new ConsoleInputOutput(System.in, System.out)).run();
    }

    private boolean shouldStop (final String command) {
        return command.equals("Stop!");
    }

    private void execute (final String commandRepresentation) {
        this.client.execute(commandRepresentation);
    }
}
