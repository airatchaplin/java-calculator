package ru.petrenko.roman;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.petrenko.roman.service.Console;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {
    private static Console console;

    @BeforeAll
    static void setup() {
        console = new Console();
    }

    @Nested
    class PositiveTest {

        @ParameterizedTest
        @MethodSource("ru.petrenko.roman.Providers#testHandleStringReplacingSpacesAndConsProvider")
        @DisplayName("Тест метода testHandleStringReplacingSpacesAndCons")
        void testHandleStringReplacingSpacesAndCons(String expression, String expected) {
            assertThat(console.handleString(expression), is(expected));
        }
    }

    @Nested
    class NegativeTest {

        @Test
        void testHandleStringIsEmpty() {
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> console.handleString(""));
            assertThat(exception.getMessage(), is("Передано пустое выражение!"));
        }
    }
}