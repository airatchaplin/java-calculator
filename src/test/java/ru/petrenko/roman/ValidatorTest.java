package ru.petrenko.roman;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTest {

    @Nested
    class PositiveTest {

        @Test
        @DisplayName("Тест метода testIsValidNumber")
        void testIsValidNumber() {
            assertThat(Validator.isValid("6+2.5+3.5555555"), is(true));
        }

        @Test
        @DisplayName("Тест метода testIsValidOperation")
        void testIsValidOperation() {
            assertThat(Validator.isValid("2+(1-3)*5/2"), is(true));
        }

    }

    @Nested
    class NegativeTest {

        @ParameterizedTest
        @MethodSource("ru.petrenko.roman.Providers#testValidatorIsValidIncorrectSymbolsProvider")
        @DisplayName("Тест метода testIsValidIncorrectSymbols")
        void testIsValidIncorrectSymbols(String expression, String expected) {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> Validator.isValid(expression));
            assertThat(exception.getMessage(), is(expected));
        }

        @ParameterizedTest
        @MethodSource("ru.petrenko.roman.Providers#testValidatorIsValidOperatorDuplicationProvider")
        @DisplayName("Тест метода testIsValidOperatorDuplication")
        void testIsValidOperatorDuplication(String expression, String expected) {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> Validator.isValid(expression));
            assertThat(exception.getMessage(), is(expected));
        }

        @ParameterizedTest
        @MethodSource("ru.petrenko.roman.Providers#testValidatorIsValidCorrectBracketsProvider")
        @DisplayName("Тест метода testIsValidCorrectBrackets")
        void testIsValidCorrectBrackets(String expression, String expected) {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> Validator.isValid(expression));
            assertThat(exception.getMessage(), is(expected));
        }
    }
}