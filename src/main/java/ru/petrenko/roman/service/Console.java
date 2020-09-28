package ru.petrenko.roman.service;

import java.util.Scanner;

public class Console {

    public String readExpression() {
        System.out.println("Введите выражение, которое нужно высчитать: ");
        return new Scanner(System.in).nextLine();
    }

    public String handleString(String expression) {
        if (expression.isEmpty()) {
            throw new IllegalArgumentException("Передано пустое выражение!");
        }
        expression = expression.replaceAll("\\s", "").
                replaceAll("^-", "0-").
                replace("(-", "(0-");
        return expression;
    }

    public void printResult(double result) {
        System.out.println("Результат выражения: " + result);
    }
}
