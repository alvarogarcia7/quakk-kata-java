package com.codurance.test.quakker.test.unit.core.adapters;

import com.codurance.test.quakker.infrastructure.console.ConsoleInput;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConsoleInputShould {


    @Test
    public void read_input () {
        injectSystemInputReading("text");

        assertThat(new ConsoleInput(System.in).read(), is("text"));
    }

    private MockSystemInput injectSystemInputReading (String message) {
        final MockSystemInput mockInput = new MockSystemInput(message);

        System.setIn(new InputStream() {
            @Override
            public int read () throws IOException {
                return mockInput.next();
            }
        });
        return mockInput;
    }


    private class MockSystemInput {
        private static final int END_OF_BUFFER = -1;
        private final char[] message;
        private int current;

        public MockSystemInput (final String message) {
            this.message = message.toCharArray();
            current = 0;
        }

        public int next () {
            if (reachedTheEnd()) {
                return END_OF_BUFFER;
            }
            return nextCharacter();
        }

        private int nextCharacter () {
            final char character = message[current];
            current++;
            return character;
        }

        private boolean reachedTheEnd () {
            return current == message.length;
        }
    }
}
