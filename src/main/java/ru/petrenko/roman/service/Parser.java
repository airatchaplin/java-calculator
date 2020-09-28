package ru.petrenko.roman.service;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<String> parse(String expression) {
        List<String> expressionList = new ArrayList<>();
        StringBuilder operand = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            if (Calculate.OPERATION_PRIORITY.containsKey(String.valueOf(expression.charAt(i)))) {
                if (operand.length() != 0) {
                    expressionList.add(operand.toString());
                    operand.delete(0, operand.length());
                }
                expressionList.add(String.valueOf(expression.charAt(i)));
            } else {
                operand.append(expression.charAt(i));
                if (i == expression.toCharArray().length - 1) {
                    expressionList.add(operand.toString());
                }
            }
        }

        return expressionList;
    }
}