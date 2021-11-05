package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.statement.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FunctionCallExpression implements Expression
{
    private String functionName;
    private List<Expression> parameterValues;

    public FunctionCallExpression(String functionName, List<Expression> parameterValues)
    {
        this.functionName = functionName;
        this.parameterValues = parameterValues;
    }

    @Override
    public int evaluate(ProgramState programState)
    {
        /* Gathering Integer values from Expressions passed int */
        List<Integer> parameterValues = getParameterValues(programState);
        /* Previous call stack variables were taken care of above, now can push new scope onto stack */
        programState.addCallFrame();
        /* Add new variables into top hashMap on call stack */
        setVariablesIntoProgramStateHM(programState, parameterValues);

        for (Statement statement : programState.getFunctionStatements(functionName))
        {
            statement.run(programState);

            if (programState.hasReturnValue())
            {
                int returnValue = programState.getReturnValue();
                programState.clearReturnValue();
                programState.removeCallFrame();
                return returnValue;
            }
        }

        throw new RuntimeException("Function " + functionName + " contains no Return Statement.");
    }

    private void setVariablesIntoProgramStateHM(ProgramState programState, List<Integer> parameterValues)
    {
        List<String> parameterNames = programState.getParameterNames(functionName);

        if (parameterValues.size() != parameterNames.size())
            throw new RuntimeException("Function " + functionName + " contains an uneven number of parameter names to parameter variables.");

        Iterator<Integer> itParameterValue = parameterValues.iterator();
        Iterator<String> itParameterName = parameterNames.iterator();

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

