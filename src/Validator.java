import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    static boolean isValid(String expression) {

        Pattern patternOperatorDuplication = Pattern.compile("[" + getOperatorsRegEx() + "]" +
                "[" + getOperatorsRegEx() + ")]");
        Matcher matcherOperatorDuplication = patternOperatorDuplication.matcher(expression);
        Pattern patternOperatorEndExpression = Pattern.compile("[" + getOperatorsRegEx() + "]$");
        Matcher matcherOperatorEndExpression = patternOperatorEndExpression.matcher(expression);
        Pattern patternOperatorOperand = Pattern.compile("[^\\d" + getOperatorsRegEx() + ".()]");
        Matcher matcherOperatorOperand = patternOperatorOperand.matcher(expression);

        if (matcherOperatorOperand.find()) {
            throw new IllegalArgumentException("Введены недопустимые значения!" +
                    " В выражении могу содержатся только цифры и знаки математических действий! ");
        } else if (matcherOperatorEndExpression.find() ||
                matcherOperatorDuplication.find() ||
                !checkBrackets(expression)) {
            throw new IllegalArgumentException("Введены недопустимые значения!" +
                    " Провертье правильность расположения операторов! ");
        }

        return true;
    }

    static private boolean checkBrackets(String expression) {
        expression = expression.replaceAll("[^()]", "");

        while (expression.contains("(") && expression.contains(")")) {
            expression = expression.replace("()", "");
        }

        return expression.isEmpty();
    }

    static private String getOperatorsRegEx() {
        StringBuilder operators = new StringBuilder();

        for (String value : Calculator.OPERATION_PRIORITY.keySet()) {
            if (!value.equals("(") && !value.equals(")")) {
                operators.append("\\").append(value);
            }
        }

        return operators.toString();
    }
}
