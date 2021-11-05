package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;

public class ConstantExpression implements Expression
{
    private int value;

    public ConstantExpression(int value)
    {
        this.value = value;
    }

    @Override
    public int evaluate(ProgramState programState)
    {
        return this.value;
    }
}
