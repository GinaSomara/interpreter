package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;

public class VariableExpression implements Expression
{
    private String varName;

    public VariableExpression(String variableName)
    {
        this.varName = variableName;
    }

    @Override
    public int evaluate(ProgramState programState)
    {
        return 0;
    }
}
