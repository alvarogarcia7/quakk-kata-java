package com.codurance.test.quakker.test.unit.core.adapters;

import com.codurance.test.quakker.infrastructure.console.ConsoleInputOutput;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConsoleOutputShould {

    @Test
    public void print_to_console () {

        final MockSystemOutput mockSystemOutput = injectSystemOutput();

        new ConsoleInputOutput(System.in, System.out).print("hello");

        assertThat(mockSystemOutput.toString(), is("hello\n"));

    }

    private MockSystemOutput injectSystemOutput () {
        final MockSystemOutput mockOutput = new MockSystemOutput();

        System.setOut(new PrintStream(new OutputStream() {

            @Override
            public void write (int b) throws IOException {
                mockOutput.write(b);

            }
        }));
        return mockOutput;
    }

    public class MockSystemOutput {
        private final StringBuilder stringBuilder = new StringBuilder();

        public void write (int b) {
            stringBuilder.append((char) b);
        }

        @Override
        public String toString () {
            return stringBuilder.toString();
        }
    }
}
