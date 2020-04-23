package ru.petrenko.roman;

import org.apache.commons.math3.util.Precision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Calculator {

    final static Map<String, Integer> OPERATION_PRIORITY;

    static {
        OPERATION_PRIORITY = new HashMap<>();
        OPERATION_PRIORITY.put("(", 0);
        OPERATION_PRIORITY.put(")", 0);
        OPERATION_PRIORITY.put("+", 1);
        OPERATION_PRIORITY.put("-", 1);
        OPERATION_PRIORITY.put("*", 2);
        OPERATION_PRIORITY.put("/", 2);
    }

    double calculate(List<String> expressionList) {
        Stack<Double> operandsStack = new Stack<>();
        Stack<String> operationStack = new Stack<>();

        for (String value : expressionList) {

            if (OPERATION_PRIORITY.containsKey(value)) {

                while (!operationStack.isEmpty() &&
                        OPERATION_PRIORITY.get(value) != 0 &&
                        OPERATION_PRIORITY.get(value) <= OPERATION_PRIORITY.get(operationStack.peek())) {

                    popOperandsPushResultMathOperation(operandsStack, operationStack);
                }

                if (value.equals(")")) {
                    while (!operationStack.peek().equals("(")) {
                        popOperandsPushResultMathOperation(operandsStack, operationStack);
                    }
                    operationStack.pop();

                } else operationStack.push(value);

            } else operandsStack.push(Double.parseDouble(value));
        }

        while (!operationStack.isEmpty()) {
            popOperandsPushResultMathOperation(operandsStack, operationStack);
        }


        return Precision.round(operandsStack.pop(), 2);
    }

    private void popOperandsPushResultMathOperation(Stack<Double> operandsStack, Stack<String> operationStack) {
        double lastOperand = operandsStack.pop();
        double penultimateOperand = operandsStack.pop();
        double resultMathOperation;

        switch (operationStack.pop()) {
            case "+":
                resultMathOperation = lastOperand + penultimateOperand;
                break;
            case "-":
                resultMathOperation = penultimateOperand - lastOperand;
                break;
            case "*":
                resultMathOperation = penultimateOperand * lastOperand;
                break;
            case "/":
                if (lastOperand == 0) {
                    throw new ArithmeticException("Ошибка! Попытка деления на ноль!");
                }
                resultMathOperation = penultimateOperand / lastOperand;
                break;
            default:
                throw new IllegalArgumentException("Ошибка! Неопознаный оператор!");
        }

        operandsStack.push(resultMathOperation);
    }
}
