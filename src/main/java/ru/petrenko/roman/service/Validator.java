package ru.petrenko.roman.service;

import ru.petrenko.roman.service.Calculate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isValid(String expression) {

        Pattern patternOperatorDuplication = Pattern.compile("[" + getOperatorsRegEx() + "]" +
                "[" + getOperatorsRegEx() + ")]");
        Matcher matcherOperatorDuplication = patternOperatorDuplication.matcher(expression);
        Pattern patternOperatorStartOrEndExpression = Pattern.compile("^[" + getOperatorsRegEx() + "]" +
                "|[" + getOperatorsRegEx() + "]$");
        Matcher matcherOperatorEndExpression = patternOperatorStartOrEndExpression.matcher(expression);
        Pattern patternOperatorOperand = Pattern.compile("[^\\d" + getOperatorsRegEx() + ".()]");
        Matcher matcherOperatorOperand = patternOperatorOperand.matcher(expression);

        if (matcherOperatorOperand.find()) {
            throw new IllegalArgumentException("Введены недопустимые значения!" +
                    " В выражении могут содержатся только цифры и знаки математических действий! ");
        }
        if (matcherOperatorEndExpression.find() ||
                matcherOperatorDuplication.find()) {
            throw new IllegalArgumentException("Введены недопустимые значения!" +
                    " Провертье правильность расположения операторов! ");
        }
        if (!checkBrackets(expression)) {
            throw new IllegalArgumentException("Введены недопустимые значения!" +
                    " Провертье правильность расположения скобок! ");
        }

        return true;
    }

    private static boolean checkBrackets(String expression) {
        expression = expression.replaceAll("[^()]", "");

        while (expression.contains("(") && expression.contains(")")) {
            expression = expression.replace("()", "");
        }

        return expression.isEmpty();
    }

    private static String getOperatorsRegEx() {
        StringBuilder operators = new StringBuilder();

        for (String value : Calculate.OPERATION_PRIORITY.keySet()) {
            if (!value.equals("(") && !value.equals(")")) {
                operators.append("\\").append(value);
            }
        }

        return operators.toString();
    }
}
