package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.statement.ReturnStatement;
import edu.csc413.interpreter.statement.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FunctionCallExpression implements Expression {
    private String functionName;
    private List<Expression> parameterValues;

    public FunctionCallExpression(String functionName, List<Expression> parameterValues) {
        this.functionName = functionName;
        this.parameterValues = parameterValues;
    }

    @Override
    public int evaluate(ProgramState programState)
    {
        //get Int values of Expression list passed in as a parameter
        List<Integer> parameterValues = getParameterValues(programState);
        //now that any previous scope parameters have been evaluated, we can push a new call stack on
        programState.addCallFrame();
        setVariablesIntoProgramStateHM(programState, parameterValues);

        List<Statement> statements = programState.getFunctionStatements(functionName);
        for (Statement statement : statements) {
            statement.run(programState);

            if (statement instanceof ReturnStatement) {
                if (programState.hasReturnValue()) {
                    int returnValue = programState.getReturnValue();
                    programState.clearReturnValue();
                    programState.removeCallFrame();
                    return returnValue;
                } else throw new RuntimeException("Function " + functionName + "contains no/void Return Value.");
            }
        }

        //return should occur in forEach loop
        throw new RuntimeException("Function " + functionName + " contains no Return Statement.");
    }

    private void setVariablesIntoProgramStateHM(ProgramState programState, List<Integer> parameterValues)
    {
        List<String> parameterNames = programState.getParameterNames(functionName);

        if (parameterValues.size() != parameterNames.size())
            throw new RuntimeException("Function " + functionName + " contains an uneven number of parameter names to parameter variables.");

        Iterator<Integer> itParameterValue = parameterValues.iterator();
        Iterator<String> itParameterName = parameterNames.iterator();

        //add new variables to our current stack call frame
        while (itParameterName.hasNext() && itParameterValue.hasNext())
            programState.setVariable(itParameterName.next(), itParameterValue.next());
    }

    private List<Integer> getParameterValues(ProgramState programState)
    {
        List<Integer> parameterValues = new ArrayList<>();
        for (Expression expression : this.parameterValues)
            parameterValues.add(expression.evaluate(programState));

        return parameterValues;
    }
}

