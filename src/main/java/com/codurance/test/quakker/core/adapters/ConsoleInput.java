package com.codurance.test.quakker.core.adapters;

import com.codurance.test.quakker.core.ports.Input;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleInput implements Input {

    private final Scanner scanner;

    public ConsoleInput (final InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    @Override
    public String read () {
        return scanner.nextLine();
    }
}
