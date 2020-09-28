package ru.petrenko.roman;

import ru.petrenko.roman.service.Calculate;
import ru.petrenko.roman.service.Console;
import ru.petrenko.roman.service.Parser;
import ru.petrenko.roman.service.Validator;

public class Main {

    public static void main(String[] args) {
        Console console = new Console();
        Parser parser = new Parser();
        Calculate calculator = new Calculate();

        String expression = console.handleString(console.readExpression());

        if (Validator.isValid(expression)) {
            console.printResult(calculator.calculate(parser.parse(expression)));
        }

    }
}
