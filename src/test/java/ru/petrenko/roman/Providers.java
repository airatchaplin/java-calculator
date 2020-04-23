package ru.petrenko.roman;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Providers {

    public static Stream<Arguments> testCalculatorCalculateArithmeticOperationProvider() {
        return Stream.of(
                arguments(Arrays.asList("5.2", "+", "6.6"), 11.8),
                arguments(Arrays.asList("12.8", "-", "25.6"), -12.8),
                arguments(Arrays.asList("4", "*", "6.5"), 26),
                arguments(Arrays.asList("25", "/", "4"), 6.25)
        );
    }

    public static Stream<Arguments> testCalculatorCalculatePriorityOperationProvider() {
        return Stream.of(
                arguments(Arrays.asList("(", "2", "+", "2", ")", "*", "2"), 8),
                arguments(Arrays.asList("2", "-", "2", "/", "2"), 1)
        );
    }

    public static Stream<Arguments> testParserParseSizeListProvider() {
        return Stream.of(
                arguments("(2+2)*2", 7),
                arguments("(6/2.8)", 5),
                arguments("4.2+2*3/3-6.1", 9)
        );
    }

    public static Stream<Arguments> testParserParse() {
        return Stream.of(
                arguments("(4+2)/2", new String[]{"(", "4", "+", "2", ")", "/", "2"}),
                arguments("(2)", new String[]{"(", "2", ")"}),
                arguments("4.2+2*3/3-6.1", new String[]{"4.2", "+", "2", "*", "3", "/", "3", "-", "6.1"})
        );
    }

    public static Stream<Arguments> testValidatorIsValidIncorrectSymbolsProvider() {
        String expected = "Введены недопустимые значения!" +
                " В выражении могут содержатся только цифры и знаки математических действий! ";
        return Stream.of(
                arguments("2$+2%", expected),
                arguments("Hello world!", expected)
        );
    }

    public static Stream<Arguments> testValidatorIsValidOperatorDuplicationProvider() {
        String expected = "Введены недопустимые значения!" +
                " Провертье правильность расположения операторов! ";
        return Stream.of(
                arguments("2++2", expected),
                arguments("(5-6-)/3", expected),
                arguments("2+2*/5", expected)
        );
    }

    public static Stream<Arguments> testValidatorIsValidCorrectBracketsProvider() {
        String expected = "Введены недопустимые значения!" +
                " Провертье правильность расположения скобок! ";
        return Stream.of(
                arguments("(2+2))", expected),
                arguments("((5-6)-(3+2)*2", expected),
                arguments("(1/(2/1)/(1-2)", expected)
        );
    }

    public static Stream<Arguments> testHandleStringReplacingSpacesAndConsProvider() {
        return Stream.of(
                arguments("2 + 2 -3", "2+2-3"),
                arguments("-6 * (-9/3)", "0-6*(0-9/3)")
        );
    }
}
