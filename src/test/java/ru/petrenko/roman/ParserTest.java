package ru.petrenko.roman;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.petrenko.roman.service.Parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ParserTest {
    private static Parser parser;

    @BeforeAll
    static void setup() {
        parser = new Parser();
    }

    @Nested
    class PositiveTest {
        @ParameterizedTest
        @MethodSource("ru.petrenko.roman.Providers#testParserParseSizeListProvider")
        @DisplayName("Тест метода testParseSizeList")
        void testParseSizeList(String expression, int sizeList) {
            assertThat(parser.parse(expression), hasSize(sizeList));
        }

        @ParameterizedTest
        @MethodSource("ru.petrenko.roman.Providers#testParserParse")
        @DisplayName("Тест метода testParse")
        void testParse(String expression, String[] expected) {
            assertThat(parser.parse(expression), contains(expected));
        }
    }

    @Nested
    class NegativeTest {

    }
}