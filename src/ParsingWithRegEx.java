import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingWithRegEx {

    List<String> parse(String expression) {
        List<String> expressionList = new ArrayList<>();
        Matcher operandMatcher = Pattern.compile("\\d+\\.?\\d*").matcher(expression);
        Matcher operationMatcher = Pattern.compile("[" + getOperationsRegEx() + "]").matcher(expression);

        while (operandMatcher.find()) {
            if (operationMatcher.find()) {
                while (operandMatcher.start() > operationMatcher.start()) {
                    expressionList.add(operationMatcher.group());
                    if (!operationMatcher.find()) break;
                }
                expressionList.add(operandMatcher.group());

                if (operandMatcher.hitEnd()) {
                    while (operationMatcher.find()) {
                        expressionList.add(operationMatcher.group());
                    }
                } else expressionList.add(operationMatcher.group());

            } else expressionList.add(operandMatcher.group());
        }

        return expressionList;
    }

    private String getOperationsRegEx() {
        StringBuilder operators = new StringBuilder();

        for (String value : Calculator.OPERATION_PRIORITY.keySet()) {
            operators.append("\\").append(value);
        }

        return operators.toString();
    }
}
