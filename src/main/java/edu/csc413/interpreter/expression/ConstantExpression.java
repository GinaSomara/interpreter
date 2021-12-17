package edu.csc413.interpreter.expression;

public class ConstantExpression implements Expression
{
    private int value;

    public ConstantExpression(int value)
    {
        this.value = value;
    }

    @Override
    public int evaluate()
    {
        return this.value;
    }
}
