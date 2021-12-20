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
    public int evaluate()
    {
        /* Gathering Integer values from Expressions passed as ints */
        List<Integer> parameterValues = getParameterValues();
        /* Previous call stack variables were taken care of above, now can push new scope onto stack */
        ProgramState.getProgramState().addCallFrame();
        /* Add new variables into top hashMap on call stack */
        setVariablesIntoProgramStateHM(parameterValues);

        for (Statement statement : ProgramState.getProgramState().getFunctionStatements(functionName))
        {
            statement.run();

            if (ProgramState.getProgramState().hasReturnValue())
            {
                int returnValue = ProgramState.getProgramState().getReturnValue();
                ProgramState.getProgramState().clearReturnValue();
                ProgramState.getProgramState().removeCallFrame();
                return returnValue;
            }
        }
        throw new RuntimeException("Function " + functionName + " contains no Return Statement.");
    }


    private void setVariablesIntoProgramStateHM(List<Integer> parameterValues)
    {
        List<String> parameterNames = ProgramState.getProgramState().getParameterNames(functionName);

        if (parameterValues.size() != parameterNames.size())
            throw new RuntimeException("Function " + functionName + " contains an uneven number of parameter names to parameter variables.");

        Iterator<Integer> itParameterValue = parameterValues.iterator();
        Iterator<String> itParameterName = parameterNames.iterator();

        while (itParameterName.hasNext() && itParameterValue.hasNext())
            ProgramState.getProgramState().setVariable(itParameterName.next(), itParameterValue.next());
    }

    private List<Integer> getParameterValues()
    {
        List<Integer> parameterValues = new ArrayList<>();
        for (Expression expression : this.parameterValues)
            parameterValues.add(expression.evaluate());

        return parameterValues;
    }
}

