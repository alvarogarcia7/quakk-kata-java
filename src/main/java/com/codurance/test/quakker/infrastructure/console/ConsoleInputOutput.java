package com.codurance.test.quakker.infrastructure.console;

import com.codurance.test.quakker.core.ports.InputOutput;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleInputOutput implements InputOutput {
    private final PrintStream printStream;
    private final Scanner scanner;

    public ConsoleInputOutput (final InputStream inputStream, final PrintStream printStream) {
        scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    @Override
    public void print (final String line) {
        printStream.println(line);
    }

    @Override
    public String read () {
        return scanner.nextLine();
    }
}
