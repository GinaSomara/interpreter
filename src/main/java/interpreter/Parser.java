package interpreter;

import interpreter.expression.*;
import interpreter.statement.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The Parser class is used to convert information that Interpreter pulls from the program lines into actual Statement,
 * Expression, and Condition objects.
 */

public class Parser
{
    public Expression createConstantExpression(int value) {
        return new ConstantExpression(value);
    }

    public Expression createVariableExpression(String variableName) {
        return new VariableExpression(variableName);
    }

    public Expression createArithmeticExpression(String operator, String lhsAsString, String rhsAsString)
    {
        return switch(operator) {
            case "+" -> new AddExpression(parseExpression(lhsAsString), parseExpression(rhsAsString));
            case "-" -> new SubtractExpression(parseExpression(lhsAsString), parseExpression(rhsAsString));
            case "/" -> new DivideExpression(parseExpression(lhsAsString), parseExpression(rhsAsString));
            case "*" -> new MultipleExpression(parseExpression(lhsAsString), parseExpression(rhsAsString));
            default -> throw new RuntimeException("Unrecognized operator: " + operator);
        };
    }

    public Expression createFunctionCallExpression(String functionName, List<String> parameterValuesAsStrings)
    {
        List<Expression> valuesAsExpressions = new ArrayList<>();
        for(String value : parameterValuesAsStrings)
        {
            Expression expressionTemp = parseExpression(value);

            valuesAsExpressions.add(expressionTemp);
        }

        return new FunctionCallExpression(functionName, valuesAsExpressions);
    }

    public Condition createCondition(String operator, String lhsAsString, String rhsAsString)
    {
        return switch(operator) {
            case "<" -> new LessThanCondition(parseExpression(lhsAsString), parseExpression(rhsAsString));
            case ">" -> new GreaterThanCondition(parseExpression(lhsAsString), parseExpression(rhsAsString));
            case "==" -> new EqualToCondition(parseExpression(lhsAsString), parseExpression(rhsAsString));
            default -> throw new RuntimeException("Unrecognized operator: " + operator);
        };
    }

    public Statement createPrintStatement(String expressionAsString)
    {
        return new PrintStatement(parseExpression(expressionAsString));
    }

    public Statement createAssignStatement(String variableName, String expressionAsString)
    {
        return new AssignStatement(variableName, parseExpression(expressionAsString));
    }

    public Statement createIfStatement(String conditionAsString, List<Statement> bodyStatements)
    {
        return new IfStatement(parseCondition(conditionAsString), bodyStatements);
    }

    public Statement createWhileStatement(String conditionAsString, List<Statement> bodyStatements)
    {
        return new WhileStatement(parseCondition(conditionAsString), bodyStatements);
    }

    public Statement createForStatement(
            String loopVariableName,
            String rangeStartAsString,
            String rangeEndAsString,
            List<Statement> bodyStatements)
    {
        return new ForStatement(loopVariableName, parseExpression(rangeStartAsString), parseExpression(rangeEndAsString), bodyStatements);
    }

    public Statement createDefineFunctionStatement(
            String functionName, List<String> parameterNames, List<Statement> functionStatements)
    {
        return new DefineFunctionStatement(functionName, parameterNames, functionStatements);
    }

    public Statement createReturnStatement(String expressionAsString) {
        return new ReturnStatement(parseExpression(expressionAsString));
    }

    /**
     * Converts a String representing an expression into an Expression object, based on the pattern detected. This method will
     * call the appropriate createConstantExpression, createVariableExpression, etc. method based on the string.
     */
    private Expression parseExpression(String expressionAsString) {
        if (expressionAsString.matches(CONSTANT_PATTERN.pattern())) {
            return createConstantExpression(Integer.parseInt(expressionAsString));
        }
        if (expressionAsString.matches(VARIABLE_NAME_PATTERN.pattern())) {
            return createVariableExpression(expressionAsString);
        }
        Matcher functionCallMatcher = FUNCTION_CALL_PATTERN.matcher(expressionAsString);
        if (functionCallMatcher.matches()) {
            String functionName = functionCallMatcher.group(1).trim();
            List<String> parameterValuesAsStrings =
                    Arrays.stream(functionCallMatcher.group(2).split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
            if (parameterValuesAsStrings.size() == 1 && parameterValuesAsStrings.get(0).isEmpty()) {
                parameterValuesAsStrings.clear();
            }
            return createFunctionCallExpression(functionName, parameterValuesAsStrings);
        }

        int parenthesisCount = 0;
        for (int i = 0; i < expressionAsString.length(); i++) {
            char ch = expressionAsString.charAt(i);
            if (ch == '(') {
                parenthesisCount++;
            } else if (ch == ')') {
                parenthesisCount--;
            } else if (parenthesisCount == 0
                    && Arrays.stream(ARITHMETIC_OPERATORS).anyMatch(op -> op.charAt(0) == ch)) {
                String lhsAsString = expressionAsString.substring(0, i).trim();
                String rhsAsString = expressionAsString.substring(i + 1).trim();
                return createArithmeticExpression(String.valueOf(ch), lhsAsString, rhsAsString);
            }
        }

        throw new RuntimeException("Unrecognized expression: " + expressionAsString);
    }

    /**
     * Converts a String representing a boolean condition into a Condition object, based on the pattern detected.
     * This method will call createCondition.
     * */
    private Condition parseCondition(String conditionAsString)
    {
        for (String operator: CONDITION_OPERATORS) {
            Matcher matcher = Pattern.compile(String.format("^(.+)%s(.+)$", operator)).matcher(conditionAsString);
            if (matcher.matches()) {
                return createCondition(operator, matcher.group(1).trim(), matcher.group(2).trim());
            }
        }

        throw new RuntimeException("Unrecognized condition: " + conditionAsString);
    }

    private static final Pattern CONSTANT_PATTERN = Pattern.compile("^[0-9]*$");
    private static final Pattern VARIABLE_NAME_PATTERN = Pattern.compile("^[A-Za-z][A-Za-z0-9_]*$");
    private static final Pattern FUNCTION_CALL_PATTERN = Pattern.compile("^([A-Za-z][A-Za-z0-9_]*)\\(([^()]*)\\)$");
    private static final String[] ARITHMETIC_OPERATORS = new String[]{"+", "-", "*", "/"};
    private static final String[] CONDITION_OPERATORS = new String[]{"==", "<", ">"};
}
