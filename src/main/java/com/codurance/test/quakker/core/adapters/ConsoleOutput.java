package com.codurance.test.quakker.core.adapters;

import com.codurance.test.quakker.core.ports.Output;

import java.io.PrintStream;

public class ConsoleOutput implements Output {
    private final PrintStream printStream;

    public ConsoleOutput (final PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void print (final String line) {
        printStream.println(line);
    }
}
