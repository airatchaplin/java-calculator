
public class Main {

    public static void main(String[] args) {
        Console console = new Console();
        ParsingWithRegEx parsingWithRegEx = new ParsingWithRegEx();
        Calculator calculator = new Calculator();

        String expression = console.readExpression();

        if (Validator.isValid(expression)) {
            console.printResult(calculator.calculations(parsingWithRegEx.parse(expression)));
        }

    }
}
