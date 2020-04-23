package ru.petrenko.roman;

public class Main {

    public static void main(String[] args) {
        Console console = new Console();
        Parser parser = new Parser();
        Calculator calculator = new Calculator();

        String expression = console.handleString(console.readExpression());

        if (Validator.isValid(expression)) {
            console.printResult(calculator.calculate(parser.parse(expression)));
        }

    }
}
