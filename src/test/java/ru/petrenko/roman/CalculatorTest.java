package ru.petrenko.roman;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {
    private static Calculator calculator;

    @BeforeAll
    static void setup() {
        calculator = new Calculator();
    }

    @Nested
    class PositiveTest {
        @ParameterizedTest
        @MethodSource("ru.petrenko.roman.Providers#testCalculatorCalculateArithmeticOperationProvider")
        @DisplayName("Тест метода testCalculateArithmeticOperation")
        void testCalculateArithmeticOperation(List<String> expression, double expected) {
            assertThat(calculator.calculate(expression), is(expected));
        }

        @ParameterizedTest
        @MethodSource("ru.petrenko.roman.Providers#testCalculatorCalculatePriorityOperationProvider")
        @DisplayName("Тест метода testCalculatePriorityOperation")
        void testCalculatePriorityOperation(List<String> expression, double expected) {
            assertThat(calculator.calculate(expression), is(expected));
        }
    }

    @Nested
    class NegativeTest {
        @Test
        @DisplayName("Тест метода testDivisionByZero")
        void testDivisionByZero() {
            Exception exception = assertThrows(ArithmeticException.class,
                    () -> calculator.calculate(Arrays.asList("5", "/", "0")));
            assertThat(exception.getMessage(), is("Ошибка! Попытка деления на ноль!"));
        }
    }
}